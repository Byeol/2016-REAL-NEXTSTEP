package org.nhnnext.security;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.Course;
import org.nhnnext.domain.SecurityUser;
import org.nhnnext.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@RepositoryEventHandler(Course.class)
public class SpringDataRestEventHandler {

//	private final MutableAclService aclService;
	private final SecurityUserRepository securityUserRepository;

	@HandleBeforeCreate
	public void applyUserInformationUsingSecurityContext(Course course) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		SecurityUser securityUser = this.securityUserRepository.findByName(name);

//		if (securityUser == null) {
//			SecurityUser newSecurityUser = new SecurityUser();
//			newSecurityUser.setName(name);
//			newSecurityUser.setRoles(new String[]{"ROLE_MANAGER"});
//			securityUser = this.securityUserRepository.save(newSecurityUser);
//		}

		course.setInstructor(securityUser);

//		course.addInstructor(newSecurityUser);
	}

//	@HandleAfterCreate
//	public void handleAfterCreate(Course object) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		ObjectIdentity oi = new ObjectIdentityImpl(object);
//		Sid sid = new PrincipalSid(authentication);
//
//		createOrUpdateAcl(oi, BasePermission.READ, sid);
//		createOrUpdateAcl(oi, BasePermission.CREATE, sid);
//		createOrUpdateAcl(oi, BasePermission.WRITE, sid);
//		createOrUpdateAcl(oi, BasePermission.DELETE, sid);
//	}
//
//	private void createOrUpdateAcl(ObjectIdentity oi, Permission p, Sid sid) {
//		// Create or update the relevant ACL
//		MutableAcl acl = null;
//		try {
//			acl = (MutableAcl) aclService.readAclById(oi);
//		} catch (NotFoundException nfe) {
//			acl = aclService.createAcl(oi);
//		}
//
//		//acl.setOwner();
//		//acl.setParent();
//		// Now grant some permissions via an access control entry (ACE)
//		acl.insertAce(acl.getEntries().size(), p, sid, true);
//		aclService.updateAcl(acl);
//	}
}
