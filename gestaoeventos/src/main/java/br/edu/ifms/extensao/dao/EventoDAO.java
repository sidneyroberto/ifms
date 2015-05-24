package br.edu.ifms.extensao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifms.extensao.model.Atividade;
import br.edu.ifms.extensao.model.Evento;

public class EventoDAO extends JpaDAO<Evento> {

	@SuppressWarnings("unchecked")
	public List<Evento> recuperaTodosEventos() {
		List<Evento> eventos = new ArrayList<Evento>();
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Evento.RECUPERA_TODOS);
			eventos = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventos;
	}

	@SuppressWarnings("unchecked")
	public List<Atividade> recuperaTodasAtividades() {
		List<Atividade> atividades = new ArrayList<Atividade>();
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Atividade.RECUPERA_TODOS);
			atividades = query.getResultList();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atividades;
	}

	public Evento verificaEventoJaExiste(Evento evento) {
		try {
			em = criaEntityManager();
			Query query = em.createNamedQuery(Evento.VERIFICA_EVENTO_JA_EXISTE);
			query.setParameter("descricao", evento.getDescricao());
			query.setParameter("titulo", evento.getTitulo());
			query.setParameter("dataRealizacao", evento.getDataRealizacao());
			Evento eventoRetornado = (Evento) query.setMaxResults(1)
					.getSingleResult();
			em.close();
			return eventoRetornado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
