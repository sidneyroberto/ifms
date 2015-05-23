package br.edu.ifms.lp4.comparator;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import br.edu.ifms.lp4.model.TipoArquivo;

public class HistogramaComparator implements
		Comparator<Map.Entry<String, TipoArquivo>> {

	@Override
	public int compare(Entry<String, TipoArquivo> o1,
			Entry<String, TipoArquivo> o2) {
		Integer first = ((TipoArquivo) o1.getValue()).getQuantidadeEncontrada();
		Integer second = ((TipoArquivo) o2.getValue())
				.getQuantidadeEncontrada();
		return second.compareTo(first);
	}

}
