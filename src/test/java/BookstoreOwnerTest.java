import models.Bookstore;
import models.BookstoreOwner;
import org.junit.Before;
import org.junit.Test;


public class BookstoreOwnerTest {
    private String name = "Test BookstoreOwner";
    private String username = "bookstoreUsername";
    private String password = "bookstorePassword";
    private BookstoreOwner bookstoreOwner;

    @Before
    public void setUp() {
        this.bookstoreOwner = new BookstoreOwner(this.name, this.username, this.password);
    }

    /**
     * Test the addBookstore() method in BookstoreOwner.
     *
     * Expected condition: The BookstoreOwner now contains the Bookstore and the Bookstore contains this BookstoreOwner
     */
    @Test
    public void TestAddBookstore(){
        Bookstore bookstore = new Bookstore("Test Bookstore");

        this.bookstoreOwner.addBookstore(bookstore);

        assert(this.bookstoreOwner.getBookstores().contains(bookstore));
        assert(bookstore.getBookstoreOwner().equals(this.bookstoreOwner));
    }

    /**
     * Test the removeBook() method in BookstoreOwner.
     *
     * Expected condition: The BookstoreOwner no longer contains the Bookstore and the Bookstore no longer has a BookstoreOwner
     */
    @Test
    public void TestRemoveBookstore(){
        Bookstore bookstore = new Bookstore("Test Bookstore");
        bookstore.setId(1L);

        this.bookstoreOwner.addBookstore(bookstore);
        this.bookstoreOwner.removeBookstoreById(1L);

        assert(!this.bookstoreOwner.getBookstores().contains(bookstore));
        assert(bookstore.getBookstoreOwner() == null);
    }
}
