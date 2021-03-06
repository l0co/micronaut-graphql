package com.lifeinide.micronaut.domain;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Model comes from https://guides.micronaut.io/micronaut-data-access-jpa-hibernate/guide/index.html
 */
@Entity
@Table(name = "book")
public class Book {

	public Book() {
	}

	public Book(@NotNull String author, @NotNull String name, Genre genre) {
		this.author = author;
		this.name = name;
		this.genre = genre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "author", nullable = false)
	private String author;

	@ManyToOne
	private Genre genre;

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

	@GraphQLQuery
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@GraphQLQuery
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book{");
		sb.append("id=");
		sb.append(id);
		sb.append(", name='");
		sb.append(name);
		sb.append("', isbn='");
		sb.append(author);
		sb.append("', genre='");
		sb.append(genre);
		sb.append("'}");
		return sb.toString();
	}
}
