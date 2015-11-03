package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Book.class)
public class Book_ {
	public static volatile SingularAttribute<Book, Long> id;
	public static volatile SingularAttribute<Book, String> title;
	public static volatile SingularAttribute<Book, String> subtitle;
	public static volatile SetAttribute<Book, Writer> writers;
	public static volatile SingularAttribute<Book, String> description;
	public static volatile SingularAttribute<Book, Long> edition;
	public static volatile SingularAttribute<Book, Long> printingYear;
	public static volatile SingularAttribute<Book, Collection> collection;
	public static volatile SetAttribute<Book, Category> categories;
}
