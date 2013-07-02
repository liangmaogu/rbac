/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.maogu.rbac.dao;

import static cn.org.rapid_framework.util.ObjectUtils.isNotEmpty;
import javacommon.base.BaseHibernateDao;

import org.springframework.stereotype.Repository;

import cn.org.rapid_framework.page.Page;

import com.maogu.rbac.model.User;
import com.maogu.rbac.vo.query.UserQuery;

@Repository
public class UserDao extends BaseHibernateDao<User,java.lang.Integer>{

	public Class getEntityClass() {
		return User.class;
	}
	
	public Page findPage(UserQuery query) {
        StringBuilder sql = new StringBuilder("select t from User t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getUsername())) {
            sql.append(" and  t.username = :username ");
        }
        if(isNotEmpty(query.getPassword())) {
            sql.append(" and  t.password = :password ");
        }
        if(isNotEmpty(query.getEnabled())) {
            sql.append(" and  t.enabled = :enabled ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql.toString(), query);
	}
	

}
