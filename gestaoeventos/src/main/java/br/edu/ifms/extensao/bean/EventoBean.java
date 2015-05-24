package br.edu.ifms.extensao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import br.edu.ifms.extensao.dao.AtividadeDAO;
import br.edu.ifms.extensao.dao.EventoDAO;
import br.edu.ifms.extensao.model.Atividade;
import br.edu.ifms.extensao.model.Evento;
import br.edu.ifms.extensao.util.JSFUtil;

@ManagedBean
@SessionScoped
public class EventoBean {

	private Evento evento;

	private Atividade atividade;

	private List<Evento> eventos;

	private List<Atividade> atividades;

	private EventoDAO eventoDAO;

	private AtividadeDAO atividadeDAO;

	@PostConstruct
	public void init() {
		eventoDAO = new EventoDAO();
		atividadeDAO = new AtividadeDAO();
		atualizaListaEventos();
		atualizaListaAtividades();
	}

	public String salvar() {
		if (eventoDAO.verificaEventoJaExiste(evento) == null) {
			if (eventoDAO.salva(evento)) {
				atualizaListaEventos();
				carregaListaAtividades(evento);
				JSFUtil.addSuccessMessage("mensagens",
						"Evento cadastrado com sucesso!", true);
			} else {
				JSFUtil.addErrorMessage(
						"mensagens",
						"Ocorreu um erro ao tentar cadastrar o evento. Favor, comunicar o administrador do sistema.",
						true);
			}
		} else {
			JSFUtil.addWarnMessage("mensagens",
					"Evento já cadastrado previamente.", true);
		}
		return "evento?faces-redirect=true";
	}

	public void editar() {
		Evento eventoRecuperado = eventoDAO.verificaEventoJaExiste(evento);
		if (eventoRecuperado == null
				|| (evento.getId() == eventoRecuperado.getId())) {
			if (eventoDAO.atualiza(evento)) {
				atualizaListaEventos();
				JSFUtil.addSuccessMessage("mensagens",
						"Dados do evento editados com sucesso!", false);
			} else {
				JSFUtil.addErrorMessage(
						"mensagens",
						"Ocorreu um erro ao tentar editar os dados do evento. Favor, comunicar o administrador do sistema.",
						true);
			}
		} else {
			JSFUtil.addWarnMessage(
					"mensagens",
					"Existe outro estudante previamente cadastrado com este RA.",
					true);
		}
	}

	public void excluir() {
		if (eventoDAO.remove(evento)) {
			atualizaListaEventos();
			carregaListaAtividades(evento);
			JSFUtil.addSuccessMessage("mensagens",
					"Evento excluído com sucesso!", false);
		} else {
			JSFUtil.addErrorMessage(
					"mensagens",
					"Ocorreu um erro ao excluir o evento. Favor, comunicar o administrador do sistema.",
					false);
		}
	}

	public String salvarAtividade() {
		atividade.setEvento(evento);
		if (atividadeDAO.salva(atividade)) {
			atualizaListaAtividades();
			JSFUtil.addSuccessMessage("mensagens",
					"Atividade cadastrada com sucesso!", true);

		} else {
			JSFUtil.addErrorMessage(
					"mensagens",
					"Ocorreu um erro ao tentar cadastrar a atividade. Favor, comunicar o administrador do sistema.",
					true);
		}
		return "evento?faces-redirect=true";
	}

	public boolean eventoPossuiAtividades() {
		boolean possui = evento != null && evento.getId() != null
				&& atividadeDAO.contaAtividades(evento) > 0;
		return possui;
	}

	public void editarAtividade() {
	}

	public void excluirAtividade() {
	}

	public String aoCadastrar() {
		evento = new Evento();
		carregaListaAtividades(evento);
		return "cadastroEvento?faces-redirect=true";
	}

	public String aoEditar(Evento evento) {
		this.evento = evento;
		carregaListaAtividades(this.evento);
		return "edicaoEvento?faces-redirect=true";
	}

	public String aoExcluir() {
		return "Deseja realmente excluir este evento (se existirem participações em eventos/atividades associadas a este evento, elas serão excluídas também)?";
	}

	public String aoCadastrarAtividade(Evento evento) {
		this.evento = evento;
		atividade = new Atividade();
		return "cadastroAtividade?faces-redirect=true";
	}

	public String aoEditarAtividade(Atividade atividade) {
		this.atividade = atividade;
		return "edicaoAtividade?faces-redirect=true";
	}

	public String aoExcluirAtividade() {
		return "Deseja realmente excluir esta atividade?";
	}

	private void atualizaListaEventos() {
		eventos = eventoDAO.recuperaTodosEventos();
	}

	private void atualizaListaAtividades() {
		if (evento != null) {
			atividades = atividadeDAO.recuperaTodasAtividades(evento);
		} else {
			atividades = new ArrayList<Atividade>();
		}
	}

	private void carregaListaAtividades(Evento evento) {
		if (evento != null) {
			atividades = evento.getAtividades();
			if (atividades == null) {
				atividades = new ArrayList<Atividade>();
			}
		}
	}

	public void aoSelecionarEvento(SelectEvent event) {
		atualizaListaAtividades();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

}
