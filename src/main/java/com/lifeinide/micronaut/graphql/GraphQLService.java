package com.lifeinide.micronaut.graphql;

import javax.inject.Qualifier;
import javax.inject.Singleton;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * All beans with this annotation represent the GraphQL service which handles the GraphQL fields.
 *
 * @author Lukasz Frankowski
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Singleton
public @interface GraphQLService {
}
