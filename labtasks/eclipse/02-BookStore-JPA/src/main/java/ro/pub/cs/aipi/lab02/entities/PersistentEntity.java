package ro.pub.cs.aipi.lab02.entities;

import java.lang.reflect.Field;

import javax.persistence.Embeddable;

import ro.pub.cs.aipi.lab02.general.Constants;

@Embeddable
public class PersistentEntity {

	protected Long id;

	public PersistentEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				result.append((field.get(this) != null ? field.get(this).toString() : "null") + ", ");
			}
			result.setLength(result.length() - 2);
		} catch (IllegalAccessException illegalAccessException) {
			System.out.println("An exception has occurred: " + illegalAccessException.getMessage());
			if (Constants.DEBUG) {
				illegalAccessException.printStackTrace();
			}
		}
		return result.toString();
	}

}
