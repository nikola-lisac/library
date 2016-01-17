package org.bildit.library.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BOOKS")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BOOK_ID", nullable = false, unique = true)
	private Long bookId;
	@Column(name = "BOOK_AUTHOR", nullable = false)
	private String bookAuthor;
	@Column(name = "BOOK_NAME", nullable = false, unique = true)
	private String bookName;
	// broj knjiga koje imamo dostupnih (npr imamo 6 primjeraka na drini
	// æuprije)
	@Column(name = "AVAILABLE_BOOKS")
	private int availableBooks;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USERS_BOOKS", joinColumns = {
			@JoinColumn(name = "BOOK_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
	private List<User> usersRented = new ArrayList<>();
	@Column(name = "RENT_DATE")
	@Temporal(TemporalType.DATE)
	private Date rentDate;
	@Column(name = "DUE_DATE")
	private Date dueDate;
	@Column(name = "GENRE")
	private String[] genre = { "Comedy", "Thriller", "Drama", "Horror", "Fantasy", "SciFi" };

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public void setUsersRented(List<User> usersRented) {
		this.usersRented = usersRented;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String bookAuthor, String bookName, int availableBooks) {
		this.bookAuthor = bookAuthor;
		this.bookName = bookName;
		this.availableBooks = availableBooks;
	}

	public Long getBookId() {
		return bookId;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public List<User> getUsersRented() {
		return usersRented;
	}

	public int getAvailableBooks() {
		return availableBooks;
	}

	public void setAvailableBooks(int availableBooks) {
		this.availableBooks = availableBooks;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookAuthor=" + bookAuthor + ", bookName=" + bookName + ", availableBooks="
				+ availableBooks + ", usersRented=" + usersRented + "]";
	}

}
