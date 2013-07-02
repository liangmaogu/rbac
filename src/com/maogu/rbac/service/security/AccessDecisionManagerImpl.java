package com.maogu.rbac.service.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AccessDecisionManagerImpl implements AccessDecisionManager {
	private static final Log log = LogFactory.getLog(AccessDecisionManagerImpl.class);
	
	@Override
	public void decide(Authentication authentication, Object obj,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		
		if (configAttributes == null) {
			log.info("need not permission");
			return;
		}
		
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			String needPermission = configAttribute.getAttribute();
			
			log.info("need permission is " + needPermission);

			for (GrantedAuthority ga : authentication.getAuthorities()) {
				log.info("auth permission is " + ga.getAuthority());
				if (needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		
		throw new AccessDeniedException("没有权限访问");
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
