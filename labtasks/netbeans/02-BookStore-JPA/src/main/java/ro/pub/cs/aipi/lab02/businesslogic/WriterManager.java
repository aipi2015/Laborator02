package ro.pub.cs.aipi.lab02.businesslogic;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.aipi.lab02.entities.Writer;
import ro.pub.cs.aipi.lab02.result.WriterInformation;

public class WriterManager extends PersistentEntityManager<Writer> {

    public WriterManager() {
        super(Writer.class);
    }

    public int deleteWritersWithoutBooks() {

        // TODO: exercise 5
        
        return -1;
    }

    public List<WriterInformation> getWritersBibliography(int numberOfBooksTotal, int numberOfBooksAlone,
            int numberOfBooksCollaboration) {
        List<WriterInformation> result = new ArrayList<>();

        // TODO: exercise 6
        
        return result;
    }

}
