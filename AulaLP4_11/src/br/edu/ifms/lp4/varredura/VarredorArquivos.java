package br.edu.ifms.lp4.varredura;

import java.io.File;
import java.util.TreeMap;

import br.edu.ifms.lp4.model.TipoArquivo;

public class VarredorArquivos {

	private TreeMap<String, TipoArquivo> histogramaArquivos = new TreeMap<String, TipoArquivo>();

	private String caminhoDiretorio;

	public TreeMap<String, TipoArquivo> getHistogramaArquivos() {
		return histogramaArquivos;
	}

	public VarredorArquivos(String caminhoDiretorio) {
		this.caminhoDiretorio = caminhoDiretorio;
	}

	private File abreArquivo(String caminhoArquivo) {
		File arquivo = new File(caminhoArquivo);
		if (arquivo.exists()) {
			return arquivo;
		}
		return null;
	}

	public void varreDiretorios() {
		File arquivo = abreArquivo(caminhoDiretorio);
		if (arquivo != null && arquivo.exists() && arquivo.isDirectory()) {
			varreDiretorios(arquivo);
		}
	}

	private String pegaExtensaoArquivo(File arquivo) {
		if (arquivo != null && arquivo.exists()) {
			String nomeArquivo = arquivo.getName().trim();
			String[] aux = nomeArquivo.split("\\.");
			return aux.length > 0 ? aux[aux.length - 1] : "";
		}
		return "";
	}

	private Double calculaTamanhoArquivoEmMegaBytes(File arquivo) {
		if (arquivo != null && arquivo.exists()) {
			long tamanhoArquivoEmBytes = arquivo.length();
			return tamanhoArquivoEmBytes / 1048576D;
		}
		return -1D;
	}

	private void varreDiretorios(File diretorio) {
		String[] listaDiretorios = diretorio.list();
		if (listaDiretorios != null && listaDiretorios.length > 0) {
			for (String nomeSubDiretorio : diretorio.list()) {
				nomeSubDiretorio = diretorio.getPath() + "/" + nomeSubDiretorio;
				File subDiretorio = abreArquivo(nomeSubDiretorio);
				if (subDiretorio != null && subDiretorio.exists()) {
					if (subDiretorio.isDirectory()) {
						varreDiretorios(subDiretorio);
					} else {
						String extensao = pegaExtensaoArquivo(subDiretorio);
						if (extensao != null) {
							extensao = extensao.toUpperCase();
							System.out.println(subDiretorio.getPath());
							if (histogramaArquivos.get(extensao) == null) {
								TipoArquivo tipo = new TipoArquivo();
								tipo.setExtensao(extensao);
								tipo.setQuantidadeEncontrada(1);
								Double tamanho = calculaTamanhoArquivoEmMegaBytes(subDiretorio);
								tipo.setTamanhoEmMB(tamanho);
								histogramaArquivos.put(extensao, tipo);
							} else {
								TipoArquivo tipo = histogramaArquivos
										.get(extensao);
								Integer quantidade = tipo
										.getQuantidadeEncontrada() + 1;
								Double tamanho = tipo.getTamanhoEmMB()
										+ calculaTamanhoArquivoEmMegaBytes(subDiretorio);
								tipo.setQuantidadeEncontrada(quantidade);
								tipo.setTamanhoEmMB(tamanho);
								histogramaArquivos.put(extensao, tipo);
							}
						}
					}
				}
			}
		}
	}
}
