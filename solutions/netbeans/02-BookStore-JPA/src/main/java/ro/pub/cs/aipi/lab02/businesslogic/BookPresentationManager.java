package ro.pub.cs.aipi.lab02.businesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import ro.pub.cs.aipi.lab02.entities.Book;
import ro.pub.cs.aipi.lab02.entities.BookPresentation;
import ro.pub.cs.aipi.lab02.entities.BookPresentation_;
import ro.pub.cs.aipi.lab02.entities.Book_;
import ro.pub.cs.aipi.lab02.entities.Collection;
import ro.pub.cs.aipi.lab02.entities.Collection_;
import ro.pub.cs.aipi.lab02.entities.Format;
import ro.pub.cs.aipi.lab02.entities.Format_;
import ro.pub.cs.aipi.lab02.entities.PublishingHouse;
import ro.pub.cs.aipi.lab02.entities.PublishingHouse_;
import ro.pub.cs.aipi.lab02.entities.SupplyOrderHeader;
import ro.pub.cs.aipi.lab02.entities.SupplyOrderHeader_;
import ro.pub.cs.aipi.lab02.entities.SupplyOrderLine;
import ro.pub.cs.aipi.lab02.entities.SupplyOrderLine_;
import ro.pub.cs.aipi.lab02.general.Constants;
import ro.pub.cs.aipi.lab02.general.Utilities;
import ro.pub.cs.aipi.lab02.result.BookPresentationInformation;
import ro.pub.cs.aipi.lab02.result.SupplyOrderInformation;

public class BookPresentationManager extends PersistentEntityManager<BookPresentation> {

    public BookPresentationManager() {
        super(BookPresentation.class);
    }

    public List<BookPresentationInformation> updateBookPresentationPriceForBooksWithMultipleFormats(int numberOfFormats,
            double amount) {

        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQueryBook = criteriaBuilder.createQuery(Book.class);
            Root<Book> rootBook = criteriaQueryBook.from(Book.class);
            criteriaQueryBook.select(rootBook);

            CriteriaQuery<BookPresentation> criteriaQueryBookPresentation = criteriaBuilder
                    .createQuery(BookPresentation.class);
            Subquery<Long> subqueryBookPresentation = criteriaQueryBookPresentation.subquery(Long.class);
            Root<BookPresentation> rootBookPresentation = subqueryBookPresentation.from(BookPresentation.class);
            subqueryBookPresentation.select(criteriaBuilder.count(rootBookPresentation.get(BookPresentation_.id)));
            subqueryBookPresentation.where(
                    criteriaBuilder.equal(rootBookPresentation.get(BookPresentation_.book), rootBook.get(Book_.id)));

            criteriaQueryBook.where(criteriaBuilder.gt(subqueryBookPresentation, numberOfFormats));
            TypedQuery<Book> typedQueryBook = entityManager.createQuery(criteriaQueryBook);
            List<Book> booksList = typedQueryBook.getResultList();

            entityManager.createNativeQuery(
                    "CREATE TABLE IF NOT EXISTS bookstore.book_tmp ( " + "book_id INT(10) UNSIGNED NOT NULL " + ");",
                    Book.class).executeUpdate();

            for (Book book : booksList) {
                entityManager
                        .createNativeQuery("INSERT INTO bookstore.book_tmp VALUES (" + book.getId() + ");", Book.class)
                        .executeUpdate();
            }

            entityManager.createNativeQuery(
                    "UPDATE bookstore.book_presentation SET price = price * :amount WHERE book_id IN (SELECT book_id FROM bookstore.book_tmp)",
                    Book.class).setParameter("amount", amount).executeUpdate();

            CriteriaQuery<BookPresentationInformation> criteriaQueryBookPresentationInformation = criteriaBuilder
                    .createQuery(BookPresentationInformation.class);
            Root<BookPresentation> root = criteriaQueryBookPresentationInformation.from(BookPresentation.class);
            Join<BookPresentation, Book> joinBookPresentation = root.join(BookPresentation_.book);
            Join<BookPresentation, Format> joinFormat = root.join(BookPresentation_.format);
            criteriaQueryBookPresentationInformation.multiselect(root.get(BookPresentation_.id),
                    joinBookPresentation.get(Book_.id), joinFormat.get(Format_.id), root.get(BookPresentation_.price));
            criteriaQueryBookPresentationInformation.where(root.get(BookPresentation_.book).in(booksList));
            TypedQuery<BookPresentationInformation> typedQueryBookPresentationInformation = entityManager
                    .createQuery(criteriaQueryBookPresentationInformation);
            List<BookPresentationInformation> bookPresentationInformationList = typedQueryBookPresentationInformation
                    .getResultList();

            entityManager.createNativeQuery("DROP TABLE bookstore.book_tmp;", Book.class).executeUpdate();

            entityTransaction.commit();
            return bookPresentationInformationList;
        } catch (Exception exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    public List<SupplyOrderInformation> makeSupplyOrderBasedOnStockpile(int stockpile) {
        List<SupplyOrderInformation> result = new ArrayList<>();

        SupplyOrderHeaderManager supplyOrderHeaderManager = new SupplyOrderHeaderManager();
        SupplyOrderLineManager supplyOrderLineManager = new SupplyOrderLineManager();

        Date currentDate = new Date(System.currentTimeMillis());

        EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookPresentation> criteriaQueryBookPresentation = criteriaBuilder
                .createQuery(BookPresentation.class);
        Root<BookPresentation> rootBookPresentation = criteriaQueryBookPresentation.from(BookPresentation.class);
        Join<Collection, PublishingHouse> joinCollectionPublishingHouse = rootBookPresentation
                .join(BookPresentation_.book).join(Book_.collection).join(Collection_.publishingHouse);
        criteriaQueryBookPresentation.select(rootBookPresentation);
        criteriaQueryBookPresentation
                .where(criteriaBuilder.lt(rootBookPresentation.get(BookPresentation_.stockpile), stockpile));
        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.asc(joinCollectionPublishingHouse.get(PublishingHouse_.id)));
        orderList.add(criteriaBuilder.asc(rootBookPresentation.get(BookPresentation_.id)));
        criteriaQueryBookPresentation.orderBy(orderList);
        TypedQuery<BookPresentation> typedQueryBookPresentation = entityManager
                .createQuery(criteriaQueryBookPresentation);
        List<BookPresentation> bookPresentationList = typedQueryBookPresentation.getResultList();

        PublishingHouse currentPublishingHouse = null;
        SupplyOrderHeader currentSupplyOrderHeader = null;

        for (BookPresentation bookPresentation : bookPresentationList) {
            PublishingHouse publishingHouse = bookPresentation.getBook().getCollection().getPublishingHouse();
            if (currentPublishingHouse == null || !currentPublishingHouse.equals(publishingHouse)) {
                SupplyOrderHeader supplyOrderHeader = new SupplyOrderHeader();
                supplyOrderHeader.setIdentificationNumber(Utilities.generateIdentificationNumber(3, 6));
                supplyOrderHeader.setIssueDate(currentDate);
                supplyOrderHeader.setState(Constants.SENT_STATUS);
                supplyOrderHeader.setPublishingHouse(publishingHouse);
                supplyOrderHeaderManager.create(supplyOrderHeader);
                currentPublishingHouse = publishingHouse;
                currentSupplyOrderHeader = supplyOrderHeader;
            }
            SupplyOrderLine supplyOrderLine = new SupplyOrderLine();
            supplyOrderLine.setBookPresentation(bookPresentation);
            supplyOrderLine.setQuantity(stockpile - bookPresentation.getStockpile());
            supplyOrderLine.setSupplyOrderHeader(currentSupplyOrderHeader);
            supplyOrderLineManager.create(supplyOrderLine);
        }
        entityManager.close();

        entityManager = Utilities.getEntityManagerFactory().createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SupplyOrderLine> criteriaQuerySupplyOrderLine = criteriaBuilder
                .createQuery(SupplyOrderLine.class);
        Root<SupplyOrderLine> rootSupplyOrderLine = criteriaQuerySupplyOrderLine.from(SupplyOrderLine.class);
        Join<SupplyOrderLine, SupplyOrderHeader> joinSupplyOrder = rootSupplyOrderLine
                .join(SupplyOrderLine_.supplyOrderHeader);
        criteriaQuerySupplyOrderLine.select(rootSupplyOrderLine);
        criteriaQuerySupplyOrderLine.where(criteriaBuilder.equal(joinSupplyOrder.get(SupplyOrderHeader_.issueDate),
                criteriaBuilder.currentDate()));
        criteriaQuerySupplyOrderLine.orderBy(criteriaBuilder.asc(joinSupplyOrder.get(SupplyOrderHeader_.id)),
                criteriaBuilder.asc(rootSupplyOrderLine.get(SupplyOrderLine_.id)));
        TypedQuery<SupplyOrderLine> typedQuerySupplyOrderLine = entityManager.createQuery(criteriaQuerySupplyOrderLine);
        List<SupplyOrderLine> supplyOrderLines = typedQuerySupplyOrderLine.getResultList();

        for (SupplyOrderLine supplyOrderLine : supplyOrderLines) {
            SupplyOrderInformation supplyOrderInformation = new SupplyOrderInformation();
            supplyOrderInformation
                    .setPublishingHouseId(supplyOrderLine.getSupplyOrderHeader().getPublishingHouse().getId());
            supplyOrderInformation.setBookPresentationId(supplyOrderLine.getBookPresentation().getId());
            supplyOrderInformation.setQuantity(supplyOrderLine.getQuantity());
            result.add(supplyOrderInformation);
        }
        entityManager.close();

        return result;
    }
}
