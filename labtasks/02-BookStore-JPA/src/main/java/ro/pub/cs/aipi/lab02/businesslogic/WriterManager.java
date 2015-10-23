package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.Writer;

public class WriterManager extends PersistentEntityManager<Writer> {

	public WriterManager() {
		super(Writer.class);
		persistentEntity = "Writer";
	}

	public int deleteWritersWithoutBooks() {

		// TODO: exercise 5

		return -1;
	}

}
