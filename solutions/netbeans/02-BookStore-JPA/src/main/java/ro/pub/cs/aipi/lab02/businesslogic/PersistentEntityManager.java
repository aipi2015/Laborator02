package ro.pub.cs.aipi.lab02.businesslogic;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import ro.pub.cs.aipi.lab02.entities.PersistentEntity;
import ro.pub.cs.aipi.lab02.entities.PersistentEntity_;
import ro.pub.cs.aipi.lab02.general.Constants;
import ro.pub.cs.aipi.lab02.general.Utilities;

public class PersistentEntityManager<T extends PersistentEntity> {

    protected Class<T> persistentEntityType;

    public PersistentEntityManager() {
        this(null);
    }

    public PersistentEntityManager(Class<T> persistentEntityType) {
        this.persistentEntityType = persistentEntityType;
    }

    public Class<T> getPersistentEntityType() {
        return persistentEntityType;
    }

    public void setPersistentEntityType(Class<T> persistentEntityType) {
        this.persistentEntityType = persistentEntityType;
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
        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = null;
        Long result = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(persistentEntity);
            entityTransaction.commit();
            result = persistentEntity.getId();
        } catch (Exception exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return result;
    }

    public T read(Long id) {
        T result = null;
        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentEntityType);
        Root<T> root = criteriaQuery.from(persistentEntityType);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(PersistentEntity_.id), id));
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        result = typedQuery.getSingleResult();
        entityManager.close();
        return result;
    }

    public int update(T persistentEntity, Long id) {
        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = null;
        int result = -1;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(persistentEntityType);
            Root<T> root = criteriaUpdate.from(persistentEntityType);
            for (Field field : persistentEntityType.getDeclaredFields()) {
                field.setAccessible(true);
                criteriaUpdate.set(root.get(field.getName()), field.get(persistentEntity));
            }
            Query query = entityManager.createQuery(criteriaUpdate);
            result = query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return result;
    }

    public int delete(Long id) {
        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = null;
        int result = -1;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(persistentEntityType);
            Root<T> root = criteriaDelete.from(persistentEntityType);
            criteriaDelete.where(criteriaBuilder.equal(root.get(PersistentEntity_.id), id));
            Query query = entityManager.createQuery(criteriaDelete);
            result = query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return result;
    }

    public List<T> getCollection(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistentEntityType);
        Root<T> root = criteriaQuery.from(persistentEntityType);
        criteriaQuery.select(root);
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public int getPersistentEntitySize() {
        int result = -1;
        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT COUNT(*) FROM " + persistentEntityType.getSimpleName());
        result = Integer.parseInt(query.getSingleResult().toString());
        entityManager.close();
        return result;
    }

}
