package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Author.class)
public class Author_ {
	public static volatile SingularAttribute<Author, Long> id;
	public static volatile SingularAttribute<Author, Book> book;
	public static volatile SingularAttribute<Author, Writer> writer;
}
