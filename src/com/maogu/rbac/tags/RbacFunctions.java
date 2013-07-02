package com.maogu.rbac.tags;

import java.util.List;

import com.maogu.rbac.model.AuthorityResource;
import com.maogu.rbac.model.RoleAuthority;
import com.maogu.rbac.model.UserRole;

public class RbacFunctions {
	public static String checked(List list, int firstId, int secondId, String type) {
		String checked = "";
		if ("resource".equals(type)) {
			List<AuthorityResource> ars = (List<AuthorityResource>) list;
			for (AuthorityResource ar : ars) {
				if (ar.getResourceId() == firstId && ar.getAuthorityId() == secondId) {
					checked = "checked";
				}
			}
		} else if ("role".equals(type)) {
			List<RoleAuthority> ras = (List<RoleAuthority>) list;
			for (RoleAuthority ra : ras) {
				if (ra.getRoleId() == firstId && ra.getAuthorityId() == secondId) {
					checked = "checked";
				}
			}
		} else if ("user".equals(type)) {
			List<UserRole> urs = (List<UserRole>) list;
			for (UserRole ur : urs) {
				if (ur.getUserId() == firstId && ur.getRoleId() == secondId) {
					checked = "checked";
				}
			}
		}
		
		return checked;
	}
}
