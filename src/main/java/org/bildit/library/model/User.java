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
 * @author Ognjen Mi�i� Model klase korisnika
 *
 */
// entity anotacija ka�e hibernateu da je ovo entitet odnosno model koji se
// koristi u aplikaciji
// i hibernate �e automatski da napravi tabelu u na�oj bazi koja �e se zvati
// USERS jer sam tako stavio ime
@Entity
@Table(name = "USERS")
public class User {

	// privremena polja, dodati koja jo� treba

	@Id // primarni klju� u bazi
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // automatska generacija
														// vrijednosti primarnog
														// klju�a
	@Column(name = "USER_ID", nullable = false, unique = true) // ime tabele, ne
																// smije biti
																// null, mora
																// biti
																// jedinstveno
																// �to �e biti
																// zahvaljuju�i
																// hibernateovoj
																// generaciji
																// klju�a
	private Long userId;
	@Column(name = "USERNAME", nullable = false, unique = true) // jedinstveno
																// korisni�ko
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

	// jedan korisnik mo�e da iznajmi vi�e knjiga
	// fetchtype.eager ka�e hibernateu da povu�e objekte i da radimo sa �ivim
	// objektima u memoriji
	// fetchtype.lazy ka�e hibernateu da ne vu�e �itave objekte ali hibernate �e
	// uvijek znati gdje da ih na�e
	// i��itajte malo o fetchtypeovima
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
