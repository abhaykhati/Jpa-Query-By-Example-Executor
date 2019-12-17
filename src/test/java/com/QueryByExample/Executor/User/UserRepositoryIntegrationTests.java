package com.QueryByExample.Executor.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.ExampleMatcher.matching;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import query.by.example.executor.QueryByExampleSpringDataJpaApplication;
import query.by.example.executor.User.User;
import query.by.example.executor.User.UserRepository;

@RunWith(SpringRunner.class)
@Transactional

@SpringBootTest(classes = { QueryByExampleSpringDataJpaApplication.class })
public class UserRepositoryIntegrationTests {
	@Autowired
	UserRepository userRepository;

	User skyler, walter, flynn, marie, hank, madhav;

	@Before
	public void setUp() {

		userRepository.deleteAll();

		this.skyler = userRepository.save(new User("Skyler", "White", 45));
		this.walter = userRepository.save(new User("Walter", "White", 50));
		this.flynn = userRepository.save(new User("Walter Jr. (Flynn)", "White", 17));
		this.marie = userRepository.save(new User("Marie", "Schrader", 38));
		this.hank = userRepository.save(new User("Hank", "Schrader", 43));
		this.madhav = userRepository.save(new User("madhav", "phaltankar", 44));
	}

	/**
	 * @see #153
	 */
	@Test
	public void countBySimpleExample() {

		Example<User> example = Example.of(new User(null, "White", null));

		assertThat(userRepository.count(example)).isEqualTo(3L);
	}

	/**
	 * @see #153
	 */
	@Test
	public void ignorePropertiesAndMatchByAge() {

		Example<User> example = Example.of(flynn, 
										   matching()
										  .withIgnorePaths("firstname", "lastname"));
		Optional<User> findOne = userRepository.findOne(example);
		User user = findOne.get();
		System.out.println(user.getFirstname() + "<<<<<     >>>>>" + user.getLastname());
		assertThat(userRepository.findOne(example)).contains(flynn);
	}

	/**
	 * @see #153
	 */
	@Test
	public void substringMatching() {

		Example<User> example = Example.of(new User("er", null, null),
				matching()
				.withStringMatcher(StringMatcher.ENDING));
		assertThat(userRepository.findAll(example)).containsExactly(skyler, walter, madhav);
	}

	/**
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @see #153
	 */
	@Test
	public void matchStartingStringsIgnoreCase() throws NoSuchFieldException, SecurityException {

		Example<User> example = Example.of(new User("M", null, null),
				 matching()
				.withIgnorePaths("age")
				.withMatcher("firstname", startsWith()));
				//.withMatcher("lastname", ignoreCase()));
		
			

		assertThat(userRepository.findAll(example)).containsExactlyInAnyOrder(madhav, marie);
	}

	/**
	 * @see #153
	 */
	@Test
	public void configuringMatchersUsingLambdas() {

		Example<User> example = Example.of(new User("M", null, null),
				 matching()
				//.withIgnorePaths("age","lastname")
				.withMatcher("firstname", matcher -> matcher.startsWith().ignoreCase()));
				//.withMatcher("lastname", matcher -> matcher.ignoreCase()));

		//assertThat(userRepository.findAll(example)).containsExactlyInAnyOrder(flynn, walter);
		assertThat(userRepository.findAll(example)).containsExactlyInAnyOrder(madhav,marie);
	}

	/**
	 * @see #153
	 */
	@Test
	public void valueTransformer() {

		Example<User> example = Example.of(new User(null, "White", 99), 
				 matching()
				.withMatcher("age", matcher -> matcher.transform(value -> Optional.of(Integer.valueOf(50)))));

		assertThat(userRepository.findAll(example)).containsExactly(walter);
	}
}
