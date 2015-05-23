package br.edu.ifms.lp4.report;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.datatype.AbstractDataType;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class GraficoReport {

	public GraficoReport() {
		construirRelatorio();
	}

	@SuppressWarnings("unchecked")
	private void construirRelatorio() {

		// Cria o estilo para as colunas
		StyleBuilder boldStyle = DynamicReports.stl.style().bold();
		StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle)
				.setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle = DynamicReports.stl
				.style(boldCenteredStyle)
				.setBorder(DynamicReports.stl.pen1Point())
				.setBackgroundColor(Color.LIGHT_GRAY);

		// Cria o construtor de relatórios
		JasperReportBuilder builder = DynamicReports.report();

		// Associa o estilo de colunas
		builder.setColumnTitleStyle(columnTitleStyle);

		// Alterna cores entre as linhas
		builder.highlightDetailEvenRows();

		// Cria as colunas
		TextColumnBuilder<String> colunaNome = (TextColumnBuilder<String>) criaColuna(
				"Produto", "produto", DynamicReports.type.stringType());
		TextColumnBuilder<Integer> colunaQuantidade = (TextColumnBuilder<Integer>) criaColuna(
				"Quantidade", "quantidade", DynamicReports.type.integerType());
		TextColumnBuilder<Double> colunaPrecoUnitario = (TextColumnBuilder<Double>) criaColuna(
				"Preço Unitário", "precoUnitario",
				DynamicReports.type.bigDecimalType());
		TextColumnBuilder<BigDecimal> colunaPrecoTotal = colunaPrecoUnitario
				.multiply(colunaQuantidade).setTitle("Preço Total");
		PercentageColumnBuilder colunaPercentualPreco = DynamicReports.col
				.percentageColumn("% Preço", colunaPrecoTotal);
		TextColumnBuilder<Integer> colunaNroLinha = DynamicReports.col
				.reportRowNumberColumn("No.").setFixedColumns(2)
				.setHorizontalAlignment(HorizontalAlignment.CENTER);

		// Cria o primeiro gráfico
		BarChartBuilder grafico1 = DynamicReports.cht
				.barChart()
				.setTitle("Vendas por item")
				.setCategory(colunaNome)
				.addSerie(DynamicReports.cht.serie(colunaPrecoUnitario),
						DynamicReports.cht.serie(colunaPrecoTotal));

		// Cria o segundo gráfico
		Bar3DChartBuilder grafico2 = DynamicReports.cht
				.bar3DChart()
				.setTitle("Vendas por item")
				.setCategory(colunaNome)
				.setUseSeriesAsCategory(true)
				.addSerie(DynamicReports.cht.serie(colunaPrecoUnitario),
						DynamicReports.cht.serie(colunaPrecoTotal));

		// Adiciona as colunas no relatório
		builder.addColumn(colunaNroLinha, colunaNome, colunaQuantidade,
				colunaPrecoUnitario, colunaPrecoTotal, colunaPercentualPreco);

		// Adiciona o título do relatório
		builder.title(DynamicReports.cmp.text("Nota fiscal").setStyle(
				boldCenteredStyle));

		// Agrupa pela coluna 'titulo'
		builder.groupBy(colunaNome);

		// Exibe os subtotais
		builder.setSubtotalStyle(boldStyle);

		builder.subtotalsAtSummary(DynamicReports.sbt.sum(colunaPrecoUnitario),
				DynamicReports.sbt.sum(colunaPrecoTotal));
		builder.subtotalsAtFirstGroupFooter(
				DynamicReports.sbt.sum(colunaPrecoUnitario),
				DynamicReports.sbt.sum(colunaPrecoTotal));

		// Adiciona o rodapé com número de páginas
		builder.pageFooter(DynamicReports.cmp.pageXofY().setStyle(
				boldCenteredStyle));

		// Adiciona os gráficos
		builder.summary(grafico1, grafico2);

		// Associa o data source no relatório
		builder.setDataSource(criaDataSource());

		// Exibe o relatório
		try {
			builder.toPdf(new FileOutputStream("/home/sidsu/relatorio.pdf"));
			builder.toXls(new FileOutputStream("/home/sidsu/relatorio.xls"));
			builder.toDocx(new FileOutputStream("/home/sidsu/relatorio.docx"));
			System.out.println("Mal feito desfeito.");
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private JRDataSource criaDataSource() {
		DRDataSource dataSource = new DRDataSource("produto", "quantidade",
				"precoUnitario");

		dataSource.add("Notebook", 1, new BigDecimal(500));
		dataSource.add("DVD", 5, new BigDecimal(30));
		dataSource.add("DVD", 1, new BigDecimal(28));
		dataSource.add("DVD", 5, new BigDecimal(32));
		dataSource.add("Book", 3, new BigDecimal(11));
		dataSource.add("Book", 1, new BigDecimal(15));
		dataSource.add("Book", 5, new BigDecimal(10));
		dataSource.add("Book", 8, new BigDecimal(9));

		return dataSource;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private TextColumnBuilder<?> criaColuna(String titulo, String nomeCampo,
			AbstractDataType tipo) {
		return DynamicReports.col.column(titulo, nomeCampo, tipo);
	}

	public static void main(String[] args) {
		new GraficoReport();
	}
}
