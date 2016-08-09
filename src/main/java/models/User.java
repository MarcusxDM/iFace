/**
 * 
 */
package models;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.Period;
/**
 * @author Vinicius
 *
 */
@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private int age;
	@Column
	private LocalDate birth;

	public User(String name, String password, String email, LocalDate birth, LocalDate now) {
		this.name = name;
		this.password = password;
		this.email = email; 
		this.birth = birth;
		this.age = calculateAge(birth, now); 
	}
	
	
	public int calculateAge(LocalDate birth, LocalDate now) {
	    if ((birth != null) && (now != null)) {
	        return Period.between(birth, now).getYears();
	    } else {
	        return 0;
	    }
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getBirth() {
		return birth;
	}


	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}


	public int getAge() {
		return age;
	}

}
