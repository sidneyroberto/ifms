package br.edu.ifms.extensao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.ifms.extensao.util.JPAUtil;
import br.edu.ifms.extensao.util.ReflectionUtil;

public class JpaDAO<T> {

	protected EntityManager em;

	protected EntityManager criaEntityManager() {
		return JPAUtil.getEntityManager();
	}

	public boolean salva(T objeto) {
		try {
			em = criaEntityManager();
			EntityTransaction transacao = em.getTransaction();
			transacao.begin();
			em.persist(objeto);
			transacao.commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean atualiza(T objeto) {
		try {
			em = criaEntityManager();
			EntityTransaction transacao = em.getTransaction();
			transacao.begin();
			em.merge(objeto);
			transacao.commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean remove(T objeto) {
		try {
			em = JPAUtil.getEntityManager();
			EntityTransaction transacao = em.getTransaction();
			transacao.begin();
			objeto = em.merge(objeto);
			em.remove(objeto);
			transacao.commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public T recupera(Serializable id) {
		try {
			Class<?> classe = ReflectionUtil.getGenericClass(this, 0);
			EntityManager em = criaEntityManager();
			T objeto = (T) em.find(classe, id);
			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
