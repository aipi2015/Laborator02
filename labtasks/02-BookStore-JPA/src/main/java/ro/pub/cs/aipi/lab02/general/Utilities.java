package ro.pub.cs.aipi.lab02.general;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ro.pub.cs.aipi.lab02.entities.PersistentEntity;

public class Utilities {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
		}
		return entityManagerFactory;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void logResult(String fileName, List<? extends PersistentEntity> persistentEntityList) {
		Charset charset = Charset.forName("UTF-8");
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
				Paths.get(Constants.OUTPUT_DIRECTORY + "/" + fileName), charset, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND)) {
			if (persistentEntityList != null) {
				for (PersistentEntity persistentEntity: persistentEntityList) {
					StringBuilder record = new StringBuilder();
					for (Field field: persistentEntity.getClass().getDeclaredFields()) {
						field.setAccessible(true);
						if (field.getType().isAssignableFrom(Collection.class)) {
							if (field.get(persistentEntity) != null) {
								Iterator<Collection> fieldIterator = ((Collection)field.get(persistentEntity)).iterator();
								while (fieldIterator.hasNext()) {
									record.append(fieldIterator.next().toString() + ", ");
								}
								record.setLength(record.length() - 2);
							} else {
								record.append("null");
							}
						} else {
							record.append(field.get(persistentEntity) != null ? field.get(persistentEntity).toString() : "null");
						}
						record.append("\t");
					}
					record.setLength(record.length() - 1);
					record.append("\n");
					bufferedWriter.write(record.toString(), 0, record.length());
				}
			}
		} catch (IOException ioException) {
			System.out.println("Error opening / writing to file: " + ioException.getMessage());
			if (Constants.DEBUG) {
				ioException.printStackTrace();
			}
		} catch (IllegalAccessException illegalAccessException) {
			System.out.println("Error accessing class property: " + illegalAccessException.getMessage());
			if (Constants.DEBUG) {
				illegalAccessException.printStackTrace();
			}			
		}
	}

}
