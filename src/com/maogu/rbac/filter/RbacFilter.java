package com.maogu.rbac.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class RbacFilter extends AbstractSecurityInterceptor implements Filter {
	private static final Log log = LogFactory.getLog(RbacFilter.class);

	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation invocation = new FilterInvocation(req, res, chain);
		InterceptorStatusToken token = super.beforeInvocation(invocation);
		try {
			invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		if (log.isInfoEnabled()) {
		    log.info("Rbac filter enabled");
		}
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return securityMetadataSource;
	}
	
	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
}
