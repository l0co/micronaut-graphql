package com.lifeinide.micronaut.graphql.services;

import com.lifeinide.micronaut.domain.Book;
import com.lifeinide.micronaut.graphql.GraphQLService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Lukasz Frankowski
 */
@SuppressWarnings("unchecked")
@Transactional @GraphQLService
public class BookGraphQLService {

	@PersistenceContext private EntityManager entityManager;

	@GraphQLQuery
	public List<Book> bookList() {
		return entityManager.createQuery("from Book").getResultList();
	}

	@GraphQLQuery
	public Optional<Book> book(@GraphQLArgument(name = "id") long id) {
		return Optional.ofNullable(entityManager.find(Book.class, id));
	}

}
