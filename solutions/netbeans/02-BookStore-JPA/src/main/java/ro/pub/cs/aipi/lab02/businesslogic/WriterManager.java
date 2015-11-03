package ro.pub.cs.aipi.lab02.businesslogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import ro.pub.cs.aipi.lab02.entities.Author;
import ro.pub.cs.aipi.lab02.entities.Author_;
import ro.pub.cs.aipi.lab02.entities.Book;
import ro.pub.cs.aipi.lab02.entities.Book_;
import ro.pub.cs.aipi.lab02.entities.Writer;
import ro.pub.cs.aipi.lab02.entities.Writer_;
import ro.pub.cs.aipi.lab02.general.Constants;
import ro.pub.cs.aipi.lab02.general.Utilities;
import ro.pub.cs.aipi.lab02.result.WriterInformation;

public class WriterManager extends PersistentEntityManager<Writer> {

    public WriterManager() {
        super(Writer.class);
    }

    public int deleteWritersWithoutBooks() {
        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Writer> criteriaDeleteWriter = criteriaBuilder.createCriteriaDelete(Writer.class);
            Root<Writer> rootWriter = criteriaDeleteWriter.from(Writer.class);

            Subquery<Long> subqueryAuthor = criteriaDeleteWriter.subquery(Long.class);
            Root<Author> rootAuthor = subqueryAuthor.from(Author.class);
            subqueryAuthor.select(criteriaBuilder.count(rootAuthor.get(Author_.id)));
            subqueryAuthor.where(criteriaBuilder.equal(rootAuthor.get(Author_.writer), rootWriter.get(Writer_.id)));

            criteriaDeleteWriter.where(criteriaBuilder.equal(subqueryAuthor, 0));
            Query queryDeleteWriter = entityManager.createQuery(criteriaDeleteWriter);
            int rowCount = queryDeleteWriter.executeUpdate();
            entityTransaction.commit();
            return rowCount;
        } catch (Exception exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return -1;
    }

    public List<WriterInformation> getWritersBibliography(int numberOfBooksTotal, int numberOfBooksAlone,
            int numberOfBooksCollaboration) {
        List<WriterInformation> result = new ArrayList<>();
        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<Writer> writersList = getCollection(entityManager);
        for (Writer writer : writersList) {
            CriteriaQuery<Book> criteriaQueryBooks = criteriaBuilder.createQuery(Book.class);
            Root<Book> rootBooks = criteriaQueryBooks.from(Book.class);
            criteriaQueryBooks.select(rootBooks);
            Subquery<Book> subqueryBook = criteriaQueryBooks.subquery(Book.class);
            Root<Author> rootAuthor = subqueryBook.from(Author.class);
            subqueryBook.select(rootAuthor.get(Author_.book));
            subqueryBook.where(criteriaBuilder.equal(rootAuthor.get(Author_.writer), writer.getId()));
            criteriaQueryBooks.where(rootBooks.get(Book_.id).in(subqueryBook));
            TypedQuery<Book> typedQueryBook = entityManager.createQuery(criteriaQueryBooks);
            List<Book> books = typedQueryBook.getResultList();

            if (books.size() > numberOfBooksTotal) {
                WriterInformation writerInformation = new WriterInformation();
                writerInformation.setWriter(writer.toString());
                List<String> unsortedBookTitles = new ArrayList<>();
                for (Book book : books) {
                    unsortedBookTitles.add(book.getTitle());
                }
                Collections.sort(unsortedBookTitles);
                StringBuilder sortedBookTitles = new StringBuilder();
                for (String bookTitle : unsortedBookTitles) {
                    sortedBookTitles.append(bookTitle + "; ");
                }
                if (sortedBookTitles.length() > 0) {
                    sortedBookTitles.setLength(sortedBookTitles.length() - 2);
                }
                writerInformation.setBooks(sortedBookTitles.toString());
                writerInformation.setNumberOfBooksTotal((long) books.size());

				// number of books alone
                CriteriaQuery<Long> criteriaQueryWriterNumberOfBooksAlone = criteriaBuilder.createQuery(Long.class);
                Root<Author> rootWriterNumberOfBooksAlone = criteriaQueryWriterNumberOfBooksAlone.from(Author.class);
                rootWriterNumberOfBooksAlone.join(Author_.book);
                criteriaQueryWriterNumberOfBooksAlone.select(criteriaBuilder.count(rootWriterNumberOfBooksAlone));

                CriteriaQuery<Author> criteriaQueryNumberOfWritersPerBookAlone = criteriaBuilder
                        .createQuery(Author.class);
                Subquery<Long> subqueryNumberOfWritersPerBookAlone = criteriaQueryNumberOfWritersPerBookAlone
                        .subquery(Long.class);
                Root<Author> rootNumberOfWritersPerBookAlone = subqueryNumberOfWritersPerBookAlone.from(Author.class);
                rootNumberOfWritersPerBookAlone.join(Author_.writer);
                subqueryNumberOfWritersPerBookAlone.select(criteriaBuilder.count(rootNumberOfWritersPerBookAlone));
                subqueryNumberOfWritersPerBookAlone
                        .where(criteriaBuilder.equal(rootNumberOfWritersPerBookAlone.get(Author_.book),
                                rootWriterNumberOfBooksAlone.get(Author_.book)));

                criteriaQueryWriterNumberOfBooksAlone.where(criteriaBuilder.and(
                        criteriaBuilder.equal(rootWriterNumberOfBooksAlone.get(Author_.writer), writer.getId()),
                        criteriaBuilder.equal(subqueryNumberOfWritersPerBookAlone, 1)));
                TypedQuery<Long> typedQueryNumberOfBooksAlone = entityManager
                        .createQuery(criteriaQueryWriterNumberOfBooksAlone);
                Long writerNumberOfBooksAlone = typedQueryNumberOfBooksAlone.getSingleResult();
                writerInformation.setNumberOfBooksAlone(writerNumberOfBooksAlone);

				// number of books collaboration
                CriteriaQuery<Long> criteriaQueryWriterNumberOfBooksCollaboration = criteriaBuilder
                        .createQuery(Long.class);
                Root<Author> rootWriterNumberOfBooksCollaboration = criteriaQueryWriterNumberOfBooksCollaboration
                        .from(Author.class);
                rootWriterNumberOfBooksCollaboration.join(Author_.book);
                criteriaQueryWriterNumberOfBooksCollaboration
                        .select(criteriaBuilder.count(rootWriterNumberOfBooksCollaboration));

                CriteriaQuery<Author> criteriaQueryNumberOfWritersPerBookCollaboration = criteriaBuilder
                        .createQuery(Author.class);
                Subquery<Long> subqueryNumberOfWritersPerBookCollaboration = criteriaQueryNumberOfWritersPerBookCollaboration
                        .subquery(Long.class);
                Root<Author> rootNumberOfWritersPerBookCollaboration = subqueryNumberOfWritersPerBookCollaboration
                        .from(Author.class);
                rootNumberOfWritersPerBookCollaboration.join(Author_.writer);
                subqueryNumberOfWritersPerBookCollaboration
                        .select(criteriaBuilder.count(rootNumberOfWritersPerBookCollaboration));
                subqueryNumberOfWritersPerBookCollaboration
                        .where(criteriaBuilder.equal(rootNumberOfWritersPerBookCollaboration.get(Author_.book),
                                rootWriterNumberOfBooksCollaboration.get(Author_.book)));

                criteriaQueryWriterNumberOfBooksCollaboration.where(criteriaBuilder.and(
                        criteriaBuilder.equal(rootWriterNumberOfBooksCollaboration.get(Author_.writer), writer.getId()),
                        criteriaBuilder.notEqual(subqueryNumberOfWritersPerBookCollaboration, 1)));
                TypedQuery<Long> typedQueryNumberOfBooksCollaboration = entityManager
                        .createQuery(criteriaQueryWriterNumberOfBooksCollaboration);
                Long writerNumberOfBooksCollaboration = typedQueryNumberOfBooksCollaboration.getSingleResult();
                writerInformation.setNumberOfBooksCollaboration(writerNumberOfBooksCollaboration);

                if (writerNumberOfBooksAlone.longValue() > numberOfBooksAlone
                        && writerNumberOfBooksCollaboration.longValue() > numberOfBooksCollaboration) {
                    result.add(writerInformation);
                }
            }
        }
        entityManager.close();
        return result;
    }

}
