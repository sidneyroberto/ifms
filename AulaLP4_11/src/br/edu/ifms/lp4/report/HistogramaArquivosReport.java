package br.edu.ifms.lp4.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import br.edu.ifms.lp4.comparator.HistogramaComparator;
import br.edu.ifms.lp4.model.TipoArquivo;
import br.edu.ifms.lp4.varredura.VarredorArquivos;

public class HistogramaArquivosReport {

	private TreeMap<String, TipoArquivo> histogramaArquivos;

	public HistogramaArquivosReport(
			TreeMap<String, TipoArquivo> histogramaArquivos) {
		this.histogramaArquivos = histogramaArquivos;
	}

	public void construirRelatorio(String caminhoArquivosRelatorio) {
		// Cria o estilo para as colunas
		StyleBuilder boldStyle = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle)
				.setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
				.setBorder(stl.pen1Point())
				.setBackgroundColor(Color.LIGHT_GRAY);

		// Cria o construtor de relatórios
		JasperReportBuilder builder = report();

		// Associa o estilo de colunas
		builder.setColumnTitleStyle(columnTitleStyle);

		// Alterna cores entre as linhas
		builder.highlightDetailEvenRows();

		builder.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE);

		// Cria as colunas
		TextColumnBuilder<String> colunaExtensao = (TextColumnBuilder<String>) col
				.column("Extensão", "extensao", type.stringType());
		TextColumnBuilder<Integer> colunaQuantidade = (TextColumnBuilder<Integer>) col
				.column("Qtd. arquivos", "quantidade", type.integerType());
		TextColumnBuilder<BigDecimal> colunaTamanho = (TextColumnBuilder<BigDecimal>) col
				.column("Tamanho em MB", "tamanhoMB", type.bigDecimalType());

		// Cria o gráfico
		Bar3DChartBuilder grafico = cht.bar3DChart()
				.setDataSource(criaDataSource())
				.setTitle("Qtd. arquivos x Extensão")
				.setCategory(colunaExtensao).setUseSeriesAsCategory(true)
				.addSerie(cht.serie(colunaQuantidade));

		// Adiciona as colunas no relatório
		builder.addColumn(colunaExtensao, colunaQuantidade, colunaTamanho);

		// Adiciona o título do relatório
		builder.title(DynamicReports.cmp.text("Histograma de arquivos")
				.setStyle(boldCenteredStyle));

		// Adiciona o rodapé com número de páginas
		builder.pageFooter(DynamicReports.cmp.pageXofY().setStyle(
				boldCenteredStyle));

		// Adiciona os gráficos
		builder.summary(grafico);

		// Associa o data source no relatório
		builder.setDataSource(criaDataSource());

		// Exibe o relatório
		try {
			builder.toPdf(new FileOutputStream(caminhoArquivosRelatorio
					+ "relatorio.pdf"));
			builder.toXls(new FileOutputStream(caminhoArquivosRelatorio
					+ "relatorio.xls"));
			builder.toDocx(new FileOutputStream(caminhoArquivosRelatorio
					+ "relatorio.docx"));
			System.out.println("Mal feito desfeito.");
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource criaDataSource() {
		DRDataSource dataSource = new DRDataSource("extensao", "quantidade",
				"tamanhoMB");

		List<Entry<String, TipoArquivo>> listaExtensoes = new ArrayList<Entry<String, TipoArquivo>>(
				histogramaArquivos.entrySet());

		if (listaExtensoes != null) {
			Collections.sort(listaExtensoes, new HistogramaComparator());

			if (listaExtensoes.size() <= 10) {
				for (Entry<String, TipoArquivo> entrada : listaExtensoes) {
					String extensao = entrada.getKey();
					TipoArquivo tipo = histogramaArquivos.get(extensao);
					dataSource.add(extensao, tipo.getQuantidadeEncontrada(),
							new BigDecimal(tipo.getTamanhoEmMB()));
				}
			} else {
				for (int i = 0; i < 10; i++) {
					Entry<String, TipoArquivo> entrada = listaExtensoes.get(i);
					String extensao = entrada.getKey();
					TipoArquivo tipo = histogramaArquivos.get(extensao);
					dataSource.add(extensao, tipo.getQuantidadeEncontrada(),
							new BigDecimal(tipo.getTamanhoEmMB()));
				}
			}
		}

		return dataSource;
	}

	public static void main(String[] args) {
		VarredorArquivos varredor = new VarredorArquivos("/home/sidsu/Música");
		varredor.varreDiretorios();
		HistogramaArquivosReport report = new HistogramaArquivosReport(
				varredor.getHistogramaArquivos());
		report.construirRelatorio("/home/sidsu/");
	}
}
