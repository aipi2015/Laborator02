package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.User;

public class UserManager extends PersistentEntityManager<User> {

	public UserManager() {
		super(User.class);
	}

}
