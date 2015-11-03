package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.Category;

public class CategoryManager extends PersistentEntityManager<Category> {

	public CategoryManager() {
		super(Category.class);
	}

}
