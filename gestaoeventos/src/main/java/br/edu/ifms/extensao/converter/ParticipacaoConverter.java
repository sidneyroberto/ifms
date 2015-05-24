package br.edu.ifms.extensao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.edu.ifms.extensao.dao.ParticipacaoDAO;
import br.edu.ifms.extensao.model.Participacao;

public class ParticipacaoConverter implements Converter {

	private ParticipacaoDAO participacaoDAO;

	public ParticipacaoConverter() {
		participacaoDAO = new ParticipacaoDAO();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.isEmpty()) {
			try {
				Long id = Long.valueOf(value);
				Participacao participacao = participacaoDAO.recupera(id);
				return participacao;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Participacao) {
			Long id = ((Participacao) value).getId();
			return String.valueOf(id);
		}
		return null;
	}

}
