package br.edu.ifms.extensao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = Atividade.RECUPERA_TODOS, query = "FROM Atividade WHERE evento.id = :id ORDER BY titulo"),
		@NamedQuery(name = Atividade.CONTA_ATIVIDADES, query = "SELECT COUNT(*) FROM Atividade WHERE evento.id = :id") })
public class Atividade {

	public static final String RECUPERA_TODOS = "recuperaTodasAtividades";

	public static final String CONTA_ATIVIDADES = "contaAtividades";

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 200, nullable = false)
	private String titulo;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Double duracaoEmHoras;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evento")
	private Evento evento;

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

	public Double getDuracaoEmHoras() {
		return duracaoEmHoras;
	}

	public void setDuracaoEmHoras(Double duracaoEmHoras) {
		this.duracaoEmHoras = duracaoEmHoras;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
