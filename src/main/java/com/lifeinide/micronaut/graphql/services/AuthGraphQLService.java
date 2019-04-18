package com.lifeinide.micronaut.graphql.services;

import com.lifeinide.micronaut.graphql.GraphQLService;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.utils.SecurityService;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author Lukasz Frankowski
 */
@GraphQLService
public class AuthGraphQLService {

	@Inject protected SecurityService securityService;

	@GraphQLQuery
	public Object authUser() {
		return securityService.getAuthentication().map(Authentication::getAttributes).orElse(Map.of());
	}

}
