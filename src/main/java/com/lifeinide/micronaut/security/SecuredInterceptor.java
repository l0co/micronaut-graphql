package com.lifeinide.micronaut.security;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.order.Ordered;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * AOP interceptor, intercepting {@link Secured}-annotated methods with security check.
 *
 * @author Lukasz Frankowski
 */
@Singleton
public class SecuredInterceptor implements MethodInterceptor<Object, Object>, Ordered {

	public static final Logger logger = LoggerFactory.getLogger(SecuredInterceptor.class);

	@Inject protected SecurityService securityService;

	@Override
	public Object intercept(MethodInvocationContext<Object, Object> context) {
		logger.trace("Intercepting: {}", context.getTargetMethod());

		context.findAnnotation(Secured.class).ifPresent(annot -> {
			boolean matches = false;
			List<String> rejectedAuthorities = new ArrayList<>();
			for (String authority: annot.getValue(String[].class).orElseThrow()) {
				matches = authority.equals(SecurityRule.IS_ANONYMOUS) || (
					!(authority.equals(SecurityRule.DENY_ALL))
						&& !(authority.equals(SecurityRule.IS_AUTHENTICATED) && !securityService.isAuthenticated())
						&& !(!authority.equals(SecurityRule.IS_AUTHENTICATED) && !securityService.hasRole(authority))
				);
				if (!matches)
					rejectedAuthorities.add(authority);
				else
					break;
			}

			if (!matches) {
				logger.debug("Security rule rejects: {} for: {}", String.join(",", rejectedAuthorities),
					context.getTargetMethod());
				throw new RuntimeException("Resource forbidden");
			}
		});

		return context.proceed();
	}

}
