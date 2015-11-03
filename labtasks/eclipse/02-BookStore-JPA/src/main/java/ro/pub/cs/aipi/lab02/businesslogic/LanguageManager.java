package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.Language;

public class LanguageManager extends PersistentEntityManager<Language> {
	
	public LanguageManager() {
		super(Language.class);
	}

}
