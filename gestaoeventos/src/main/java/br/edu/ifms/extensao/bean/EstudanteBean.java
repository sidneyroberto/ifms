package br.edu.ifms.extensao.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifms.extensao.dao.EstudanteDAO;
import br.edu.ifms.extensao.dao.TurmaDAO;
import br.edu.ifms.extensao.model.Estudante;
import br.edu.ifms.extensao.model.Sexo;
import br.edu.ifms.extensao.model.Turma;
import br.edu.ifms.extensao.util.JSFUtil;

@ManagedBean
@SessionScoped
public class EstudanteBean {

	private Estudante estudante;

	private List<Estudante> estudantes;

	private List<Turma> turmas;

	private EstudanteDAO estudanteDAO;

	private TurmaDAO turmaDAO;

	@PostConstruct
	public void init() {
		estudanteDAO = new EstudanteDAO();
		atualizaListaEstudantes();
		turmaDAO = new TurmaDAO();
		setTurmas(turmaDAO.recuperaTodos());
	}

	public String salvar() {
		if (estudanteDAO.recuperaPorRA(estudante.getRa()) == null) {
			if (estudanteDAO.salva(estudante)) {
				atualizaListaEstudantes();
				JSFUtil.addSuccessMessage("mensagens",
						"Estudante cadastrado com sucesso!", true);
			} else {
				JSFUtil.addErrorMessage(
						"mensagens",
						"Ocorreu um erro ao tentar cadastrar o estudante. Favor, comunicar o administrador do sistema.",
						true);
			}
		} else {
			JSFUtil.addWarnMessage("mensagens",
					"Estudante já cadastrado previamente.", true);
		}
		return "estudante?faces-redirect=true";
	}

	public String editar() {
		Estudante estudanteRecuperado = estudanteDAO.recuperaPorRA(estudante
				.getRa());
		if (estudanteRecuperado == null
				|| (estudante.getId() == estudanteRecuperado.getId())) {
			if (estudanteDAO.atualiza(estudante)) {
				atualizaListaEstudantes();
				JSFUtil.addSuccessMessage("mensagens",
						"Dados do estudante editados com sucesso!", true);
			} else {
				JSFUtil.addErrorMessage(
						"mensagens",
						"Ocorreu um erro ao tentar editar os dados do estudante. Favor, comunicar o administrador do sistema.",
						true);
			}
		} else {
			JSFUtil.addWarnMessage(
					"mensagens",
					"Existe outro estudante previamente cadastrado com este RA.",
					true);
		}
		return "estudante?faces-redirect=true";
	}

	public void excluir() {
		if (estudanteDAO.remove(estudante)) {
			atualizaListaEstudantes();
			JSFUtil.addSuccessMessage("mensagens",
					"Estudante excluído com sucesso!", true);
		} else {
			JSFUtil.addErrorMessage(
					"mensagens",
					"Ocorreu um erro ao excluir o estudante. Favor, comunicar o administrador do sistema.",
					true);
		}
	}

	private void atualizaListaEstudantes() {
		estudantes = estudanteDAO.recuperaTodos();
	}

	public String aoCadastrar() {
		estudante = new Estudante();
		return "cadastroEstudante?faces-redirect=true";
	}

	public String aoEditar(Estudante estudante) {
		this.estudante = estudante;
		return "edicaoEstudante?faces-redirect=true";
	}

	public String aoExcluir() {
		return "Deseja realmente excluir este estudante (se existirem participações em eventos/atividades associadas a este estudante, elas serão excluídas também)?";
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

	public Sexo getMasculino() {
		return Sexo.M;
	}

	public Sexo getFeminino() {
		return Sexo.F;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
}
