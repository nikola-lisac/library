package org.bildit.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.bildit.library.model.Book;

/**
 * @author Ognjen Mišiæ Model klase korisnika
 *
 */
// entity anotacija kaže hibernateu da je ovo entitet odnosno model koji se
// koristi u aplikaciji
// i hibernate æe automatski da napravi tabelu u našoj bazi koja æe se zvati
// USERS jer sam tako stavio ime
@Entity
@Table(name = "USERS")
public class User {

	// privremena polja, dodati koja još treba

	@Id // primarni kljuè u bazi
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // automatska generacija
														// vrijednosti primarnog
														// kljuèa
	@Column(name = "USER_ID", nullable = false, unique = true) // ime tabele, ne
																// smije biti
																// null, mora
																// biti
																// jedinstveno
																// što æe biti
																// zahvaljujuæi
																// hibernateovoj
																// generaciji
																// kljuèa
	private Long userId;
	@Column(name = "USERNAME", nullable = false, unique = true) // jedinstveno
																// korisnièko
																// ime
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Transient
	private String confirmPassword;
	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name="LAST_NAME", nullable = false)
	private String lastName;
	
	// jedan korisnik može da iznajmi više knjiga
	// fetchtype.eager kaže hibernateu da povuèe objekte i da radimo sa živim
	// objektima u memoriji
	// fetchtype.lazy kaže hibernateu da ne vuèe èitave objekte ali hibernate æe
	// uvijek znati gdje da ih naðe
	// išèitajte malo o fetchtypeovima
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "usersRented")
	private List<Book> listOfBooksRequested = new ArrayList<>();
	// TO ADD RELATION
	private List<Book> listOfBooksApproved = new ArrayList<>();
	
	public List<Book> getListOfBooksApproved() {
		return listOfBooksApproved;
	}

	public List<Book> getListOfBooksRequested() {
		return listOfBooksRequested;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}

}
