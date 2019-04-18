package com.lifeinide.micronaut.domain;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Model comes from https://guides.micronaut.io/micronaut-data-access-jpa-hibernate/guide/index.html
 */
@Entity
@Table(name = "genre")
public class Genre {

	public Genre() {
	}

	public Genre(@NotNull String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "genre")
	private Set<Book> books = new HashSet<>();

	@GraphQLQuery
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@GraphQLQuery
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Genre{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}

}
