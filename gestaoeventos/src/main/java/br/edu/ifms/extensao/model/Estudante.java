package br.edu.ifms.extensao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = Estudante.RECUPERA_TODOS, query = "FROM Estudante ORDER BY nome"),
		@NamedQuery(name = Estudante.RECUPERA_POR_RA, query = "FROM Estudante WHERE ra = :ra") })
public class Estudante {

	public static final String RECUPERA_TODOS = "recuperaTodosEstudantes";

	public static final String RECUPERA_POR_RA = "recuperaPorRA";

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 6, nullable = false, unique = true)
	private String ra;

	@Column(length = 200, nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(length = 11)
	private String cpf;

	@Column(nullable = false)
	private Sexo sexo;

	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

	@OneToMany(mappedBy = "estudante", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Participacao> participacoes;

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome != null ? nome.toUpperCase() : nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf != null ? cpf.replace(".", "").replace("-", "") : cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(List<Participacao> participacoes) {
		this.participacoes = participacoes;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
