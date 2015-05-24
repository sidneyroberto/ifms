package br.edu.ifms.extensao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.extensao.model.Estudante;
import br.edu.ifms.extensao.model.Participacao;

public class ParticipacaoDAO extends JpaDAO<Participacao> {

	@SuppressWarnings("unchecked")
	public List<Participacao> recuperaTodasParticipacoesEstudante(
			Estudante estudante) {
		List<Participacao> participacoes = new ArrayList<Participacao>();
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Participacao.RECUPERA_TODOS);
			query.setParameter("ra", estudante.getRa());
			participacoes = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return participacoes;
	}
}
