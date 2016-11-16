package org.nhnnext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@ToString(exclude = "password")
@EqualsAndHashCode(of = "id")
@Entity
public class SecurityUser {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@JsonIgnore
	private String password;

	private String[] roles;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	protected SecurityUser() {
	}

	public SecurityUser(String name, String password, String... roles) {
		this.name = name;
		this.setPassword(password);
		this.roles = roles;
	}

}
