package br.edu.ifms.extensao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private JPAUtil() {
	}

	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("development");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}