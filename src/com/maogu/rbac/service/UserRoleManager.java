/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.maogu.rbac.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.maogu.rbac.model.*;
import com.maogu.rbac.dao.*;
import com.maogu.rbac.service.*;
import com.maogu.rbac.vo.query.*;

/**
 * @author liangmaogu email:liangmaogu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class UserRoleManager extends BaseManager<UserRole,java.lang.Integer>{

	private UserRoleDao userRoleDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setUserRoleDao(UserRoleDao dao) {
		this.userRoleDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.userRoleDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(UserRoleQuery query) {
		return userRoleDao.findPage(query);
	}
	
	@Transactional
	public void deleteByUserIdAndRoleId(int userId, int roleId) {
		UserRoleQuery query = new UserRoleQuery();
		query.setUserId(userId);
		query.setRoleId(roleId);
		
		Page<UserRole> page = userRoleDao.findPage(query);
		List<UserRole> urs = page.getResult();
		for (UserRole ur : urs) {
			userRoleDao.delete(ur);
		}
	}
	
}
