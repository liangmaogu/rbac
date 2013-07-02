/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2013
 */

package com.maogu.rbac.dao;

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


import static cn.org.rapid_framework.util.ObjectUtils.*;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityResourceDao extends BaseHibernateDao<AuthorityResource,java.lang.Integer>{

	public Class getEntityClass() {
		return AuthorityResource.class;
	}
	
	public Page findPage(AuthorityResourceQuery query) {
        StringBuilder sql = new StringBuilder("select t from AuthorityResource t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getAuthorityId())) {
            sql.append(" and  t.authorityId = :authorityId ");
        }
        if(isNotEmpty(query.getResourceId())) {
            sql.append(" and  t.resourceId = :resourceId ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql.toString(), query);
	}
	

}
