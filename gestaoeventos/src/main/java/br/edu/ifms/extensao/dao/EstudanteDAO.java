package br.edu.ifms.extensao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.extensao.model.Estudante;

public class EstudanteDAO extends JpaDAO<Estudante> {

	@SuppressWarnings("unchecked")
	public List<Estudante> recuperaTodos() {
		List<Estudante> estudantes = new ArrayList<Estudante>();
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Estudante.RECUPERA_TODOS);
			estudantes = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estudantes;
	}

	public Estudante recuperaPorRA(String ra) {
		if (ra != null && !ra.isEmpty()) {
			try {
				em = criaEntityManager();
				Query query = em.createNamedQuery(Estudante.RECUPERA_POR_RA);
				query.setParameter("ra", ra);
				Estudante estudante = (Estudante) query.getSingleResult();
				em.close();
				return estudante;
			} catch (Exception e) {
			}
		}
		return null;
	}
}
