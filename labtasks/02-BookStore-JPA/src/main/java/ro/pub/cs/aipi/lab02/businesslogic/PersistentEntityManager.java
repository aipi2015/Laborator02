package ro.pub.cs.aipi.lab02.businesslogic;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ro.pub.cs.aipi.lab02.entities.PersistentEntity;
import ro.pub.cs.aipi.lab02.general.Constants;
import ro.pub.cs.aipi.lab02.general.Utilities;

public class PersistentEntityManager<T extends PersistentEntity> {

	protected Class<T> persistentEntityType;
	protected String persistentEntity;

	public PersistentEntityManager() {
		this(null);
	}

	public PersistentEntityManager(Class<T> persistentEntityType) {
		this(persistentEntityType, null);
	}

	public PersistentEntityManager(Class<T> persistentEntityType, String persistentEntity) {
		this.persistentEntityType = persistentEntityType;
		this.persistentEntity = persistentEntity;
	}

	public void setPersistentEntity(String persistentEntity) {
		this.persistentEntity = persistentEntity;
	}

	public String getPersistentEntity() {
		return persistentEntity;
	}

	public T convert(HashMap<String, String> content) {
		T result = null;
		try {
			result = persistentEntityType.newInstance();
			for (Field field : persistentEntityType.getDeclaredFields()) {
				field.setAccessible(true);
				if (content.containsKey(field.getName())) {
					String value = content.get(field.getName());
					if (Integer.class.isAssignableFrom(field.getType())) {
						field.set(result, Integer.valueOf(value));
					} else if (Long.class.isAssignableFrom(field.getType())) {
						field.set(result, Long.valueOf(value));
					} else if (Float.class.isAssignableFrom(field.getType())) {
						field.set(result, Float.valueOf(value));
					} else if (Double.class.isAssignableFrom(field.getType())) {
						field.set(result, Double.valueOf(value));
					} else if (Boolean.class.isAssignableFrom(field.getType())) {
						field.set(result, Boolean.valueOf(value));
					} else {
						field.set(result, value);
					}
				}
			}
		} catch (IllegalAccessException | InstantiationException exception) {
			System.out.println("An exception has occurred while instantiating the class: " + exception.getMessage());
			if (Constants.DEBUG) {
				exception.printStackTrace();
			}
		}
		return result;
	}

	public Long create(T persistentEntity) {
		// TODO: exercise 3
		return new Long(-1);
	}
	
	public T read(Long id) {
		EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentEntityType);
		Root<T> root = criteriaQuery.from(persistentEntityType);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getSingleResult();
	}

	public int getPersistentEntitySize() {
		// TODO: exercise 1
		return -1;
	}

}
