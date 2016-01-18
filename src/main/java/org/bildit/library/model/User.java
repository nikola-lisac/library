package org.bildit.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

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
	@NotBlank(message = "Must not be blank.")
	@Size(min = 5, max = 15, message = "Username must be between 5 and 15 chars.")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Username must not contain spaces or special characters.")
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Transient
	private String confirmPassword;
	@Column(name = "FIRST_NAME", nullable = false)
	@NotBlank(message = "Must not be blank.")
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 chars.")
	@Pattern(regexp = "[a-zA-Z]+", message = "First name must contain only letters.")
	private String firstName;
	@Column(name = "LAST_NAME", nullable = false)
	@NotBlank(message = "Must not be blank.")
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 chars.")
	@Pattern(regexp = "[a-zA-Z]+", message = "First name must contain only letters.")
	private String lastName;

	// jedan korisnik može da iznajmi više knjiga
	// fetchtype.eager kaže hibernateu da povuèe objekte i da radimo sa živim
	// objektima u memoriji
	// fetchtype.lazy kaže hibernateu da ne vuèe èitave objekte ali hibernate æe
	// uvijek znati gdje da ih naðe
	// išèitajte malo o fetchtypeovima
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "usersRented")
	private Set<Book> listOfBooksRequested = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "usersRequested")
	private Set<Book> listOfBooksApproved = new HashSet<>();
	@Column(name = "ENABLED", columnDefinition = "TINYINT(1)")
	private boolean enabled = false;
	@Column(name="ROLE")
	private String role = "ROLE_USER";
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Set<Book> getListOfBooksRequested() {
		return listOfBooksRequested;
	}

	public Set<Book> getListOfBooksApproved() {
		return listOfBooksApproved;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}

}
