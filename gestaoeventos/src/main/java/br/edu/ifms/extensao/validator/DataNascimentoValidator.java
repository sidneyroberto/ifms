package br.edu.ifms.extensao.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dataNascimentoValidator")
public class DataNascimentoValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value != null) {
			if (value != null) {
				Date dataNascimento = (Date) value;
				if (dataNascimento.after(new Date())) {
					FacesMessage mensagem = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Data inválida!",
							"Data inválida!");
					throw new ValidatorException(mensagem);
				}
			}
		}
	}

}
