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

import com.maogu.rbac.model.UserRole;
import com.maogu.rbac.vo.query.UserRoleQuery;

@Repository
public class UserRoleDao extends BaseHibernateDao<UserRole,java.lang.Integer>{

	public Class getEntityClass() {
		return UserRole.class;
	}
	
	public Page findPage(UserRoleQuery query) {
        StringBuilder sql = new StringBuilder("select t from UserRole t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getUserId())) {
            sql.append(" and  t.userId = :userId ");
        }
        if(isNotEmpty(query.getRoleId())) {
            sql.append(" and  t.roleId = :roleId ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql.toString(), query);
	}
	

}
