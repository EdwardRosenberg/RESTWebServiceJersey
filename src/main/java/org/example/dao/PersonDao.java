package org.example.dao;

import java.util.List;

import org.example.domain.Person;

public interface PersonDao {

	List<Person> getAll();

	List<Person> search(Person person);

	Person get(long id);

	void create(Person person);

	void update(Person person);

	void delete(long id);
}
