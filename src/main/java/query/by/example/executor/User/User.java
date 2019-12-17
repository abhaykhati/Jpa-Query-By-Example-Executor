package query.by.example.executor.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id 
	@GeneratedValue 
	private Long id;
	private  String firstname, lastname;
	private  Integer age;
	public User() {
		super();
	}
	
	
	public User( String firstname, String lastname, Integer age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}


	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + "]";
	}
	
	
	
	
}
