package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book{
    private Long id;
    private String name;
    private String isbn;
    private String picture;
    private String description;
    private String author;
    private String publisher;

    @JsonIgnore
    private Bookstore bookstore;
    @JsonIgnore
    private ShoppingCart shoppingCart;

    public Book(){	}
    public Book(String name, String isbn, String picture, String description, String author, String publisher){
        this.name = name;
        this.isbn = isbn;
        this.picture = picture;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
    }

    @Id
    @GeneratedValue
    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    @ManyToOne
    public Bookstore getBookstore(){ return this.bookstore; }
    public void setBookstore(Bookstore bookstore){ this.bookstore = bookstore; }
    public void removeBookstore(){this.bookstore = null;}

    @ManyToOne
    public ShoppingCart getShoppingCart(){ return this.shoppingCart; }
    public void setShoppingCart(ShoppingCart shoppingCart){ this.shoppingCart = shoppingCart; }
    public void removeShoppingCart(){this.shoppingCart = null;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIsbn() { return this.isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getPicture() { return this.picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String getAuthor() { return this.author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return this.publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public boolean equals(Book book){
        if (this.name == book.getName() && this.isbn == book.getIsbn() && this.picture == book.getPicture() && this.description == book.getDescription() && this.author == book.getAuthor() && this.publisher == book.getPublisher()){
            return true;
        }
        return false;
    }
}