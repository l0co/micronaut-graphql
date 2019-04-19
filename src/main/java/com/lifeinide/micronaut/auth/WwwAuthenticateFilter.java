package com.lifeinide.micronaut.auth;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.OncePerRequestHttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

/**
 * The filter to add {@code WWW-Authenticate} header for the browser to display Basic HTTP Auth popup on HTTP 401: Unauthorized.
 *
 * @author Lukasz Frankowski
 */
@Filter("/**")
public class WwwAuthenticateFilter extends OncePerRequestHttpServerFilter {

	@Override
	protected Publisher<MutableHttpResponse<?>> doFilterOnce(HttpRequest<?> request, ServerFilterChain chain) {
		return Flowable.fromPublisher(chain.proceed(request)).map(response -> {
			if (response.getStatus().equals(HttpStatus.UNAUTHORIZED))
				response.header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"The Realm\"");
			return response;
		});
	}
	
}
