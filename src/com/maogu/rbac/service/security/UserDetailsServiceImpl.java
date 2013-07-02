package com.maogu.rbac.service.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.org.rapid_framework.page.Page;

import com.maogu.rbac.dao.AuthorityDao;
import com.maogu.rbac.dao.RoleAuthorityDao;
import com.maogu.rbac.dao.UserDao;
import com.maogu.rbac.dao.UserRoleDao;
import com.maogu.rbac.model.Authority;
import com.maogu.rbac.model.Role;
import com.maogu.rbac.model.RoleAuthority;
import com.maogu.rbac.model.User;
import com.maogu.rbac.model.UserRole;
import com.maogu.rbac.vo.query.UserQuery;

public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);
	
	private UserDao userDao;
	private AuthorityDao authorityDao;
	private UserRoleDao userRoleDao;
	private RoleAuthorityDao roleAuthorityDao;
	
	public UserDetailsServiceImpl() {
		if (log.isInfoEnabled()) {
			log.info("enabled");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserQuery query = new UserQuery();
		query.setEnabled(1);
		query.setUsername(username);
		
		Page<User> page = userDao.findPage(query);
		List<User> users = page.getResult();
		
		if (users != null && users.size() > 0) {
			List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>();
			
			User user = users.get(0);
			List<UserRole> urs = userRoleDao.findAllBy("userId", user.getId());
			for (UserRole ur : urs) {
				Role role = ur.getRole();
				
				List<RoleAuthority> ras = roleAuthorityDao.findAllBy("roleId", role.getId());
				for (RoleAuthority ra : ras) {
					Authority authority = authorityDao.getById(ra.getAuthorityId());
					authsList.add(new GrantedAuthorityImpl(authority.getName()));
					
					log.info("user has authority " + authority.getName());
				}
			}
			
			org.springframework.security.core.userdetails.User userdetail = new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), true, true, true, true, authsList);
			
			return userdetail;
		} else {
			throw new UsernameNotFoundException(username + " 不存在 ");
		}
	}

	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}
	
	public void setAuthorityDao(AuthorityDao dao) {
		this.authorityDao = dao;
	}
	
	public void setUserRoleDao(UserRoleDao dao) {
		this.userRoleDao = dao;
	}
	
	public void setRoleAuthorityDao(RoleAuthorityDao dao) {
		this.roleAuthorityDao = dao;
	}
}
