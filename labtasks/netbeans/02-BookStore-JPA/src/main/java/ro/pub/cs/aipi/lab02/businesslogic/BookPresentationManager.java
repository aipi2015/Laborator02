package ro.pub.cs.aipi.lab02.businesslogic;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.aipi.lab02.entities.BookPresentation;
import ro.pub.cs.aipi.lab02.result.BookPresentationInformation;
import ro.pub.cs.aipi.lab02.result.SupplyOrderInformation;

public class BookPresentationManager extends PersistentEntityManager<BookPresentation> {

    public BookPresentationManager() {
        super(BookPresentation.class);
    }

    public List<BookPresentationInformation> updateBookPresentationPriceForBooksWithMultipleFormats(int numberOfFormats,
            double amount) {

        // TODO: exercise 4
        
        return null;
    }

    public List<SupplyOrderInformation> makeSupplyOrderBasedOnStockpile(int stockpile) {
        List<SupplyOrderInformation> result = new ArrayList<>();

	// TODO: exercise 7
        
        return result;
    }
}
