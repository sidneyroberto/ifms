package br.edu.ifms.extensao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = Evento.RECUPERA_TODOS, query = "FROM Evento ORDER BY dataRealizacao DESC"),
		@NamedQuery(name = Evento.VERIFICA_EVENTO_JA_EXISTE, query = "FROM Evento WHERE titulo = :titulo AND descricao = :descricao AND dataRealizacao = :dataRealizacao") })
public class Evento {

	public static final String RECUPERA_TODOS = "recuperaTodosEventos";

	public static final String VERIFICA_EVENTO_JA_EXISTE = "verificaEventoJaExiste";

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 200, nullable = false)
	private String titulo;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String descricao;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataRealizacao;

	@Temporal(TemporalType.TIME)
	private Date horarioInicio;

	@Temporal(TemporalType.TIME)
	private Date horarioFim;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Atividade> atividades;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Participacao> participacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Date getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Date getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(Date horarioFim) {
		this.horarioFim = horarioFim;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(List<Participacao> participacoes) {
		this.participacoes = participacoes;
	}

}
