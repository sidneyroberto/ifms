package br.edu.ifms.extensao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.extensao.model.Atividade;
import br.edu.ifms.extensao.model.Evento;

public class AtividadeDAO extends JpaDAO<Atividade> {

	public Long contaAtividades(Evento evento) {
		Long numeroAtividades = 0L;
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Atividade.CONTA_ATIVIDADES);
			query.setParameter("id", evento.getId());
			numeroAtividades = (Long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numeroAtividades;
	}

	@SuppressWarnings("unchecked")
	public List<Atividade> recuperaTodasAtividades(Evento evento) {
		List<Atividade> atividades = new ArrayList<Atividade>();
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Atividade.RECUPERA_TODOS);
			query.setParameter("id", evento.getId());
			atividades = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atividades;
	}
}
