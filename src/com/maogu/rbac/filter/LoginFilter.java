package com.maogu.rbac.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.org.rapid_framework.page.Page;

import com.maogu.rbac.dao.UserDao;
import com.maogu.rbac.model.User;
import com.maogu.rbac.vo.query.UserQuery;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	private static final Log log = LogFactory.getLog(LoginFilter.class);
	
	private UserDao userDao;
	
	private LoginFilter() {
		if (log.isInfoEnabled()) {
			log.info("enabled");
		}
	}
	
	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		
		UserQuery query = new UserQuery();
		query.setEnabled(1);
		query.setUsername(username);
		
		Page<User> page = userDao.findPage(query);
		List<User> users = page.getResult();
		if (users != null && users.size() > 0) {
			User user = users.get(0);
			
			String encodePassword = new Md5PasswordEncoder().encodePassword(password, username);
			if (!user.getPassword().equals(encodePassword)) {
				throw new AuthenticationServiceException("密码错误"); 
			}

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			setDetails(request, token);
		
			return getAuthenticationManager().authenticate(token);
		} else {
			 throw new AuthenticationServiceException("用户名错误");  
		}
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter("username");
		return obj == null ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter("password");
		return obj == null ? "" : obj.toString();
	}
	
	
}
