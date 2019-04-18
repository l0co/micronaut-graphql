package com.lifeinide.micronaut.graphql.services;

import com.lifeinide.micronaut.domain.Book;
import com.lifeinide.micronaut.domain.Genre;
import com.lifeinide.micronaut.graphql.GraphQLService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lukasz Frankowski
 */
@SuppressWarnings("unchecked")
@Transactional @GraphQLService
public class GenreGraphQLService {

	@PersistenceContext private EntityManager entityManager;

	@GraphQLQuery
	public List<Genre> genreList() {
		return entityManager.createQuery("from Genre").getResultList();
	}

	@GraphQLQuery
	public Optional<Genre> genre(@GraphQLArgument(name = "id") long id) {
		return Optional.ofNullable(entityManager.find(Genre.class, id));
	}

	@GraphQLQuery
	public List<Book> books(@GraphQLContext Genre genre, @GraphQLArgument(name = "limit") long limit) {
		return genre.getBooks().stream().limit(limit).collect(Collectors.toList());
	}

}
