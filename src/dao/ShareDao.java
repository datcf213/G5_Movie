package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Share;;

public class ShareDao {
	EntityManagerFactory emf;
	EntityManager em;
	public ShareDao() {
		emf = Persistence.createEntityManagerFactory("OE-web");
		em = emf.createEntityManager();
	}
	public boolean insert(Share s) { 
		em.getTransaction().begin();
		try {
			em.persist(s);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean update(Share s) {
		em.getTransaction().begin();
		try {
			em.merge(s);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
	public Share delete(String id) {
		em.getTransaction().begin();
		try {
			Share s = this.doGet(id);
			em.remove(s);
			em.getTransaction().commit();
			return s;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	public List<Share> get(){
		em.getTransaction().begin();
		try {
			String jpql = "SELECT o From Share o";
			TypedQuery<Share> query = em.createQuery(jpql, Share.class);
			List<Share> list = query.getResultList();
			em.getTransaction().commit();
			return list;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	public Share get(String id){
		em.getTransaction().begin();
		try {
			/*String jpql = "SELECT u From User u WHERE u.id = '"+id+"'";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			User user  = query.getSingleResult();*/
			Share share = this.doGet(id);
			em.getTransaction().commit();
			return share ;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	private Share doGet(String id) {
		String jpql = "SELECT o From Share o WHERE o.id = '"+id+"'";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		Share share  = query.getSingleResult();
		
		return share;
	}
}
