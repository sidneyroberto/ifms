package br.edu.ifms.lp4.report;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.datatype.AbstractDataType;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class EstilizadoReport {

	public EstilizadoReport() {
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
		TextColumnBuilder<Double> colunaPreco = (TextColumnBuilder<Double>) criaColuna(
				"Preço", "preco", DynamicReports.type.bigDecimalType());

		// Adiciona as colunas no relatório
		builder.addColumn(colunaNome, colunaQuantidade, colunaPreco);

		// Adiciona o título do relatório
		builder.title(DynamicReports.cmp.text("Nota fiscal").setStyle(
				boldCenteredStyle));

		// Adiciona o rodapé com número de páginas
		builder.pageFooter(DynamicReports.cmp.pageXofY().setStyle(
				boldCenteredStyle));

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
				"preco");

		dataSource.add("Notebook Dell Vostro", 1, new BigDecimal(2199));
		dataSource.add("Notebook Dell Inspiron", 1, new BigDecimal(2990));
		dataSource.add("Notebook Lenovo", 1, new BigDecimal(1190));
		dataSource.add("Mouse Microsoft Wireless", 1, new BigDecimal(59.9));
		dataSource.add("Mouse HP Wireless", 1, new BigDecimal(69.9));
		dataSource.add("Teclado Microsoft Wireless", 2, new BigDecimal(138));
		dataSource.add("Teclado HP Wireless", 2, new BigDecimal(158));

		return dataSource;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private TextColumnBuilder<?> criaColuna(String titulo, String nomeCampo,
			AbstractDataType tipo) {
		return DynamicReports.col.column(titulo, nomeCampo, tipo);
	}

	public static void main(String[] args) {
		new EstilizadoReport();
	}

}
