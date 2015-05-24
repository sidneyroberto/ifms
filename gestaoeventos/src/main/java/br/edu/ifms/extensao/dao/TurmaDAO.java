package br.edu.ifms.extensao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.extensao.model.Turma;

public class TurmaDAO extends JpaDAO<Turma> {

	@SuppressWarnings("unchecked")
	public List<Turma> recuperaTodos() {
		List<Turma> turmas = new ArrayList<Turma>();
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Turma.RECUPERA_TODOS);
			turmas = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turmas;
	}

}
