package br.edu.ifms.extensao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifms.extensao.dao.TurmaDAO;
import br.edu.ifms.extensao.model.Turma;

@FacesConverter("turmaConverter")
public class TurmaConverter implements Converter {

	private TurmaDAO turmaDAO;

	public TurmaConverter() {
		turmaDAO = new TurmaDAO();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.isEmpty()) {
			try {
				Long id = Long.valueOf(value);
				Turma turma = turmaDAO.recupera(id);
				return turma;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Turma) {
			Long id = ((Turma) value).getId();
			return String.valueOf(id);
		}
		return null;
	}
}
