package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Item;

public class ItemDao {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("grocerystore");

	public void cleanUp() {
		emfactory.close();
	}
	
	public void deleteItem(Item toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Item> typedQuery = em.createQuery("select i from Item i where i.item = :selectedItem and i.department = :selectedDepartment",Item.class);
		typedQuery.setParameter("selectedItem", toDelete.getItem());
		typedQuery.setParameter("selectedDepartment", toDelete.getDepartment());
		typedQuery.setMaxResults(1);
		Item result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public void insertItem(Item toInsert) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toInsert);
		em.getTransaction().commit();
		em.close();
	}

	public void updatePlayer(Item toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Item> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Item> typedQuery = em.createQuery("select i from Item i", Item.class);
		List<Item> foundTeams = typedQuery.getResultList();
		em.close();
		return foundTeams;
	}
	
	public Item searchForItemByItemID(int itemID) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Item found = em.find(Item.class, itemID);
		em.close();
		return found;
	}
/*
	public List<Item> searchForItemByDepartment(String teamName) {  
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb where fb.team = :selectedTeam", footballPlayer.class);
		typedQuery.setParameter("selectedTeam", teamName);
		List<footballPlayer> foundName = typedQuery.getResultList();
		em.close();
		return foundName;
	}

	public List<footballPlayer> searchForPlayerByName(String name) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb where fb.name = :selectedName", footballPlayer.class);
		typedQuery.setParameter("selectedName", name);
		List<footballPlayer> foundTeams = typedQuery.getResultList();
		em.close();
		return foundTeams;
	}

	public List<footballPlayer> searchForPlayerByOwner(String owner) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<footballPlayer> typedQuery = em.createQuery("select fb from footballPlayer fb where fb.owner = :selectedOwner", footballPlayer.class);
		typedQuery.setParameter("selectedOwner", owner);
		List<footballPlayer> foundOwner = typedQuery.getResultList();
		em.close();
		return foundOwner;
	}
*/	
}