package br.edu.ifms.extensao.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = { @NamedQuery(name = Participacao.RECUPERA_TODOS, query = "FROM Participacao WHERE estudante.ra = :ra ORDER BY evento.dataRealizacao DESC") })
public class Participacao {

	public static final String RECUPERA_TODOS = "recuperaTodasParticipacoesEstudante";

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estudante")
	private Estudante estudante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_evento")
	private Evento evento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
