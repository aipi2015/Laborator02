package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BookPresentation.class)
public class BookPresentation_ {

    public static volatile SingularAttribute<BookPresentation, Long> id;
    public static volatile SingularAttribute<BookPresentation, Long> isbn;
    public static volatile SingularAttribute<BookPresentation, Book> book;
    public static volatile SingularAttribute<BookPresentation, Format> format;
    public static volatile SingularAttribute<BookPresentation, Language> Language;
    public static volatile SingularAttribute<BookPresentation, Double> price;
    public static volatile SingularAttribute<BookPresentation, Long> stockpile;
}
