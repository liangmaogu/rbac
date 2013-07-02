package com.maogu.rbac.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.maogu.rbac.dao.AuthorityDao;
import com.maogu.rbac.dao.AuthorityResourceDao;
import com.maogu.rbac.dao.ResourceDao;
import com.maogu.rbac.filter.LoginFilter;
import com.maogu.rbac.model.Authority;
import com.maogu.rbac.model.AuthorityResource;
import com.maogu.rbac.model.Resource;

public class SecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private static final Log log = LogFactory.getLog(LoginFilter.class);
	
	private AuthorityDao authorityDao;
	private ResourceDao resourceDao;
	private AuthorityResourceDao authorityResourceDao;
	
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		String reqUrl = ((FilterInvocation) obj).getRequestUrl();
		reqUrl = reqUrl.substring(1);
		log.info("request url " + reqUrl);
		
		if (resourceMap == null) {
			loadResourceDefine();
		}
		
		int markIndex = reqUrl.indexOf("?");
		if (markIndex != -1) {
			reqUrl = reqUrl.substring(0, markIndex);
		}
		
		return resourceMap.get(reqUrl);
	}
	
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			
			List<Authority> authorities = authorityDao.findAll();
			for (Authority authority : authorities) {
				ConfigAttribute configAttribute = new SecurityConfig(authority.getName());
				
				List<AuthorityResource> ars = authorityResourceDao.findAllBy("authorityId", authority.getId());
				for (AuthorityResource ar : ars) {
					Resource resource = resourceDao.getById(ar.getResourceId());
					String url = resource.getUrl();
					
					Collection<ConfigAttribute> configAttributes = null;
					if (resourceMap.containsKey(url)) {
						configAttributes = resourceMap.get(url);
						configAttributes.add(configAttribute);
						resourceMap.put(url, configAttributes);
					} else {
						configAttributes = new ArrayList<ConfigAttribute>();    
		                configAttributes.add(configAttribute);
		                resourceMap.put(url, configAttributes);
					}
					
					if (log.isInfoEnabled()) {
	                	log.info("url=" + resource.getUrl() + ", authority=" + authority.getName());
	                }
				}
			}
		} 
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public void setAuthorityDao(AuthorityDao dao) {
		this.authorityDao = dao;
	}

	public void setResourceDao(ResourceDao dao) {
		this.resourceDao = dao;
	}
	
	public void setAuthorityResourceDao(AuthorityResourceDao dao) {
		this.authorityResourceDao = dao;
	}
}
