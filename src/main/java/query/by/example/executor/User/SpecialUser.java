package query.by.example.executor.User;

import javax.persistence.Entity;

@Entity
public class SpecialUser extends User {

	public SpecialUser() {
	}
	public SpecialUser(String firstname, String lastname, Integer age) {
		super(firstname, lastname, age);
	}
}
