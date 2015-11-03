package ro.pub.cs.aipi.lab02.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import ro.pub.cs.aipi.lab02.businesslogic.BookManager;
import ro.pub.cs.aipi.lab02.businesslogic.BookPresentationManager;
import ro.pub.cs.aipi.lab02.businesslogic.PersistentEntityManager;
import ro.pub.cs.aipi.lab02.businesslogic.UserManager;
import ro.pub.cs.aipi.lab02.businesslogic.WriterManager;
import ro.pub.cs.aipi.lab02.entities.PersistentEntity;
import ro.pub.cs.aipi.lab02.general.Constants;
import ro.pub.cs.aipi.lab02.general.Utilities;
import ro.pub.cs.aipi.lab02.result.BookPresentationInformation;

public class BookStore {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public int exercise01(Class<? extends PersistentEntity> persistentEntityType) {
        PersistentEntityManager persistentEntityManager = new PersistentEntityManager(persistentEntityType);
        return persistentEntityManager.getPersistentEntitySize();
    }

    public void exercise02() {
        BookManager bookManager = new BookManager();
        Utilities.logResult("02books.txt", bookManager.getBooksList());
    }

    public Long exercise03() {
        UserManager userManager = new UserManager();
        Path inputFile = Paths.get(Constants.INPUT_DIRECTORY + "/03user.txt");
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader inputFileBufferedReader = Files.newBufferedReader(inputFile, charset)) {
            HashMap<String, String> values = new HashMap<>();
            String inputFileCurrentLine = null;
            do {
                inputFileCurrentLine = inputFileBufferedReader.readLine();
                if (inputFileCurrentLine != null) {
                    String[] inputFileCurrentLineContent = inputFileCurrentLine.split("[ ]");
                    values.put(inputFileCurrentLineContent[0], inputFileCurrentLineContent[1]);
                }
            } while (inputFileCurrentLine != null);
            return userManager.create(userManager.convert(values));
        } catch (IOException ioException) {
            System.out.println("An exception has occurred while handling file: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        }
        return new Long(-1);
    }

    public int exercise04() {
        BookPresentationManager bookPresentationManager = new BookPresentationManager();
        List<BookPresentationInformation> result = bookPresentationManager
                .updateBookPresentationPriceForBooksWithMultipleFormats(3, 1.15);
        Utilities.logResult("04book_presentation_prices.txt", result);
        return (result != null) ? result.size() : -1;
    }

    public int exercise05() {
        WriterManager writerManager = new WriterManager();
        return writerManager.deleteWritersWithoutBooks();
    }

    public void exercise06() {
        WriterManager writerManager = new WriterManager();
        Utilities.logResult("06writers_bibliography.txt", writerManager.getWritersBibliography(4, 2, 0));
    }

    public void exercise07() {
        BookPresentationManager bookPresentationManager = new BookPresentationManager();
        Utilities.logResult("07supply_orders.txt", bookPresentationManager.makeSupplyOrderBasedOnStockpile(200));
    }

    public void exercise08() {
        BookManager bookManager = new BookManager();
        Utilities.logResult("08books.txt", bookManager.getBooksListWithStockpile(1000));
    }

}
