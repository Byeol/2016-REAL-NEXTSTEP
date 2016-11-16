package org.nhnnext.config;

import org.nhnnext.security.AuditingEntityPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
	@Autowired
	private RoleHierarchy roleHierarchy;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		final DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
		PermissionEvaluator permissionEvaluator = new AuditingEntityPermissionEvaluator();
		handler.setPermissionEvaluator(permissionEvaluator);
		handler.setRoleHierarchy(this.roleHierarchy);

		return handler;
	}

	//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//		PermissionEvaluator permissionEvaluator = new AuditingEntityPermissionEvaluator();
//		expressionHandler.setPermissionEvaluator(permissionEvaluator);
//		return expressionHandler;
//	}
}
