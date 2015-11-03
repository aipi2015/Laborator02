package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.Country;

public class CountryManager extends PersistentEntityManager<Country> {

	public CountryManager() {
		super(Country.class);
	}

}
