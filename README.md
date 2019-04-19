# Micronaut GraphQL example

This project shows how to use [Micronaut](https://micronaut.io) with [java-graphql](https://github.com/graphql-java/graphql-java) and [graphql-spql](https://github.com/leangen/graphql-spqr) with transactions and security support.

[//]: # (TODOLF article link reference)

## Running the example

To start the server use the following command:

```bash
$ ./gradlew clean shadowJar
$ java -jar build/libs/micronaut-graphql-0.1.jar
```

## Authentication

After starting the server you can navigate to [http://localhost:8080/graphiql](http://localhost:8080/graphiql) to display the GraphQL console. During the first request for GraphQL schema the Basic HTTP Auth popup should be displayed in the browser. You can authenticate with the [following users](src/main/java/com/lifeinide/micronaut/auth/AuthenticationProvider.java):

1. `user` / `userpass` to become a regular user.
1. `admin` / `adminpass` to become the admin.