package controllers;

import models.*;
import repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookstoreRestController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookstoreRepository bookstoreRepository;
    @Autowired
    private BookstoreOwnerRepository bookstoreOwnerRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private SaleRepository saleRepository;


    //BookstoreOwner REST endpoints
    @GetMapping("/api/getBookstoreOwner")
    public BookstoreOwner getBookstoreOwner(@RequestParam(value = "bookstoreOwnerId") long bookstoreOwnerId) {
        return bookstoreOwnerRepository.findById(bookstoreOwnerId);
    }

    @GetMapping("/api/getBookstoreOwners")
    public Iterable<BookstoreOwner> getBookstoreOwner() {
        return bookstoreOwnerRepository.findAll();
    }

    @PostMapping("/api/newBookstoreOwner")
    public BookstoreOwner newBookstoreOwner(@RequestParam(value = "bookstoreOwnerName") String bookstoreOwnerName) {
        BookstoreOwner bookstoreOwner = new BookstoreOwner(bookstoreOwnerName);
        bookstoreOwnerRepository.save(bookstoreOwner);
        return bookstoreOwner;
    }


    //Bookstore REST endpoints
    @GetMapping("/api/getBookstore")
    public Bookstore getBookstore(@RequestParam(value = "bookstoreId") long bookstoreId) {
        return bookstoreRepository.findById(bookstoreId);
    }

    @GetMapping("/api/getBookstores")
    public Iterable<Bookstore> getBookstores() {
        return bookstoreRepository.findAll();
    }

    @PostMapping("/api/newBookstore")
    public Bookstore newBookstore(@RequestParam(value = "bookstoreName") String bookstoreName, @RequestParam(value = "bookstoreOwnerID") long bookstoreOwnerID) {
        BookstoreOwner bookstoreOwner = bookstoreOwnerRepository.findById(bookstoreOwnerID);
        if (bookstoreOwner == null)
            return null;
        Bookstore bookstore = new Bookstore(bookstoreName);
        bookstore.setBookstoreOwner(bookstoreOwner);
        bookstoreOwner.addBookstore(bookstore);
        bookstoreOwnerRepository.save(bookstoreOwner);
        return bookstore;
    }


    //Book REST endpoints
    @GetMapping("/api/getBook")
    public Book getBook(@RequestParam(value = "bookId") long bookId) {
        return bookRepository.findById(bookId);
    }

    @GetMapping("/api/getBooks")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/api/getBooksByBookstore")
    public Iterable<Book> getBooksByBookstore(@RequestParam(value = "bookstoreID") long bookstoreID) {
        Bookstore bookstore = bookstoreRepository.findById(bookstoreID);
        if (bookstore == null)
            return null;
        return bookRepository.findByBookstore(bookstore);
    }

    @PostMapping("/api/newBook")
    public Book newBook(@RequestParam(value = "bookName") String bookName, @RequestParam(value = "isbn") String isbn, @RequestParam(value = "picture") String picture, @RequestParam(value = "description") String description, @RequestParam(value = "author") String author, @RequestParam(value = "publisher") String publisher, @RequestParam(value = "bookstoreID") long bookstoreID) {
        Bookstore bookstore = bookstoreRepository.findById(bookstoreID);
        if (bookstore == null)
            return null;
        Book book = new Book(bookName, isbn, picture, description, author, publisher);
        bookstore.addBook(book);
        book.setBookstore(bookstore);
        bookstoreRepository.save(bookstore);
        return book;
    }


    //Customer REST endpoints
    @GetMapping("/api/getCustomer")
    public Customer getCustomer(@RequestParam(value = "id") long id) {
        return customerRepository.findById(id);
    }

    @GetMapping("/api/getCustomers")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/api/newCustomer")
    public Customer newCustomer(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address, @RequestParam(value = "email") String email, @RequestParam(value = "phoneNumber") String phoneNumber) {
        Customer customer = new Customer(name, address, email, phoneNumber);
        customerRepository.save(customer);
        return customer;
    }


    //Shopping Cart REST endpoints
    @GetMapping("/api/getShoppingCart")
    public ShoppingCart getShoppingCart(@RequestParam(value = "id") long id) {
        return shoppingCartRepository.findById(id);
    }

    @GetMapping("/api/getShoppingCarts")
    public Iterable<ShoppingCart> getShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    @GetMapping("/api/getShoppingCartsByCustomer")
    public Iterable<ShoppingCart> getShoppingCartsByCustomer(@RequestParam(value = "customerID") long customerID) {
        Customer customer = customerRepository.findById(customerID);
        if (customer == null)
            return null;
        return shoppingCartRepository.findByCustomer(customer);
    }

    @PostMapping("/api/newShoppingCart")
    public ShoppingCart newShoppingCart(@RequestParam(value = "customerID") long customerID) {
        Customer customer = customerRepository.findById(customerID);
        if (customer == null)
            return null;
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        shoppingCartRepository.save(shoppingCart);
        customer.setShoppingCart(shoppingCart);
        customerRepository.save(customer);
        return shoppingCart;
    }

    @PostMapping("/api/addBookToShoppingCart")
    public ShoppingCart addBookToShoppingCart(@RequestParam(value = "shoppingCartID") long shoppingCartID, @RequestParam(value = "bookID") long bookID) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartID);
        if (shoppingCart == null)
            return null;
        Book book = bookRepository.findById(bookID);
        if (book == null)
            return null;
        shoppingCart.addBook(book);
        book.addShoppingCart(shoppingCart);
        shoppingCartRepository.save(shoppingCart);
        bookRepository.save(book);
        return shoppingCart;
    }


    //Sale REST endpoints
    @GetMapping("/api/getSale")
    public Sale getSale(@RequestParam(value = "id") long id) {
        return saleRepository.findById(id);
    }

    @GetMapping("/api/getSales")
    public Iterable<Sale> getSales() {
        return saleRepository.findAll();
    }

    @GetMapping("/api/getSalesByCustomer")
    public Iterable<Sale> getSalesByCustomer(@RequestParam(value = "customerID") long customerID) {
        Customer customer = customerRepository.findById(customerID);
        if (customer == null)
            return null;
        return saleRepository.findByCustomer(customer);
    }

    @PostMapping("/api/newSale")
    public Sale newSale(@RequestParam(value = "shoppingCartID") long shoppingCartID) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartID);
        if (shoppingCart == null)
            return null;
        Sale sale = shoppingCart.checkout();
        saleRepository.save(sale);
        for (Book book : sale.getBooks()) {
            bookRepository.save(book);
        }
        shoppingCartRepository.save(shoppingCart);
        return sale;
    }
}