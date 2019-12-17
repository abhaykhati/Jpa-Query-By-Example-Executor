package com.QueryByExample.Executor.User;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import query.by.example.executor.QueryByExampleSpringDataJpaApplication;
import query.by.example.executor.User.SpecialUser;
import query.by.example.executor.User.User;
import query.by.example.executor.User.UserRepository;

/**
 * Integration test showing the usage of JPA Query-by-Example support through Spring Data repositories and entities
 * using inheritance.
 *
 * @author Mark Paluch
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = { QueryByExampleSpringDataJpaApplication.class })
public class UserRepositoryInheritanceIntegrationTests {

	@Autowired UserRepository repository;

	User skyler, walter, flynn;

	@Before
	public void setUp() {

		repository.deleteAll();

		this.skyler = repository.save(new User("Skyler", "White", 45));
		this.walter = repository.save(new SpecialUser("Walter", "White", 50));
		this.flynn = repository.save(new SpecialUser("Walter Jr. (Flynn)", "White", 17));
	}

	/**
	 * @see #153
	 */
	@Test
	public void countByExample() {
		assertThat(repository.count(Example.of(new User(null, "White", null))), is(3L));
	}

	/**
	 * @see #153
	 */
	@Test
	public void countSubtypesByExample() {
		assertThat(repository.count(Example.of(new SpecialUser(null, "White", null))), is(2L));
	}
}
