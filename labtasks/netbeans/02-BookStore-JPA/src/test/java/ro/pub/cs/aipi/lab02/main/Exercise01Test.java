package ro.pub.cs.aipi.lab02.main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ro.pub.cs.aipi.lab02.entities.Book;
import ro.pub.cs.aipi.lab02.entities.BookPresentation;
import ro.pub.cs.aipi.lab02.entities.Category;
import ro.pub.cs.aipi.lab02.entities.Collection;
import ro.pub.cs.aipi.lab02.entities.Country;
import ro.pub.cs.aipi.lab02.entities.Format;
import ro.pub.cs.aipi.lab02.entities.InvoiceHeader;
import ro.pub.cs.aipi.lab02.entities.InvoiceLine;
import ro.pub.cs.aipi.lab02.entities.Language;
import ro.pub.cs.aipi.lab02.entities.PersistentEntity;
import ro.pub.cs.aipi.lab02.entities.PublishingHouse;
import ro.pub.cs.aipi.lab02.entities.SupplyOrderHeader;
import ro.pub.cs.aipi.lab02.entities.SupplyOrderLine;
import ro.pub.cs.aipi.lab02.entities.User;
import ro.pub.cs.aipi.lab02.entities.Writer;

@RunWith(Parameterized.class)
public class Exercise01Test {

    final private static HashMap<Class<? extends PersistentEntity>, Integer> persistentEntityNumberOfRecords = new HashMap<Class<? extends PersistentEntity>, Integer>() {
        private static final long serialVersionUID = 20152015L;

        {
            put(Book.class, 2382);
            put(BookPresentation.class, 7028);
            put(Category.class, 156);
            put(Collection.class, 160);
            put(Country.class, 240);
            put(Format.class, 30);
            put(InvoiceHeader.class, 2000);
            put(InvoiceLine.class, 11076);
            put(Language.class, 185);
            put(PublishingHouse.class, 547);
            put(SupplyOrderHeader.class, 2000);
            put(SupplyOrderLine.class, 11124);
            put(User.class, 2000);
            put(Writer.class, 2586);
        }
    };

    private Class<? extends PersistentEntity> persistentEntity;

    public Exercise01Test(Class<? extends PersistentEntity> persistentEntity) {
        this.persistentEntity = persistentEntity;
    }

    @Parameters
    public static java.util.Collection<Object[]> data() {
        ArrayList<Object[]> data = new ArrayList<>();
        Set<Class<? extends PersistentEntity>> persistentEntities = persistentEntityNumberOfRecords.keySet();
        Iterator<Class<? extends PersistentEntity>> persistentEntitiesIterator = persistentEntities.iterator();
        while (persistentEntitiesIterator.hasNext()) {
            Class<? extends PersistentEntity> persistentEntity = persistentEntitiesIterator.next();
            data.add(new Object[]{persistentEntity});
        }
        return data;
    }

    @Test
    public void checkNumberOfRecords() {
        System.out.println("Test01 -SELECT- checkNumberOfRecords - parameter: " + persistentEntity);
        BookStore bookstore = new BookStore();
        int numberOfRecords = persistentEntityNumberOfRecords.get(persistentEntity);
        assertEquals("Table " + persistentEntity + " should have " + numberOfRecords + " records!", numberOfRecords,
                bookstore.exercise01(persistentEntity));
        System.out.println("Test passed!");
    }

}
