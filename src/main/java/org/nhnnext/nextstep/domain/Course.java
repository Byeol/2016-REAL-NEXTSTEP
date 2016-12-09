package org.nhnnext.nextstep.domain;

import lombok.Data;
import org.nhnnext.nextstep.core.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Entity
public class Course extends AbstractPersistable<Long> {

	@Column(unique = true)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
	private final List<Session> sessions = new ArrayList<>();

	@NotNull
	private String name;

	@Lob
	private String description;

	@ManyToOne
	private SecurityUser instructor;

//	@ManyToMany
//	private Collection<User> instructors;

//	@OneToMany(mappedBy = "course")
////	@OrderColumn(name = "lecture_order")
//	private List<Lecture> lectures;
//
//	void swapLecture(int i, int j) {
//		Collections.swap(lectures, i, j);
//	}
//
//
//	public boolean isInstructor(Authentication authentication) {
//		return true;
//	}
//
//	public boolean isMember(Authentication authentication) {
//		return true;
//	}
//
//	public List<Sid> getSids(Authentication authentication) {
//		List<Sid> sids = new ArrayList<>();
//
//		sids.add(new PrincipalSid(authentication));
//
//		if (isMember(authentication)) {
//			sids.add(new GrantedAuthoritySid("COURSE_MEMBER"));
//		}
//
//		if (isInstructor(authentication)) {
//			sids.add(new GrantedAuthoritySid("COURSE_INSTRUCTOR"));
//		}
//
//		return sids;
//	}
//
//	@JsonIgnore
//	@Transactional
//	public Acl getAcl() {
//		MutableAcl acl = new AclImpl();
//		acl.insertAce(acl.getEntries().size(), BasePermission.READ, new GrantedAuthoritySid("ROLE_GUEST"), true);
//		acl.insertAce(acl.getEntries().size(), BasePermission.CREATE, new GrantedAuthoritySid("ROLE_INSTRUCTOR"), true);
//		acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, new GrantedAuthoritySid("COURSE_INSTRUCTOR"), true);
//		acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, new GrantedAuthoritySid("COURSE_INSTRUCTOR"), true);
//		return acl;
//	}
}