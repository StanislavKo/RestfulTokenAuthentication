package com.solver.swagger.backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "user")
@NamedQueries({
		@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM PUUser u WHERE u.username = :username"),
		@NamedQuery(name = "User.findByVerificationCode", query = "SELECT u FROM PUUser u WHERE u.verificationCode = :verificationCode"),
		@NamedQuery(name = "User.findByUsernamePassword", query = "SELECT u FROM PUUser u WHERE u.username = :username AND u.password = :password"), })
public class PUUser {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String verificationCode;
	private Boolean isVerified;
	private Set<PUAccessToken> accessTokens;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "verification_code")
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	@Column(name = "is_verified")
	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	@OneToMany
	@JoinTable(name = "ACCESS_TOKEN_USER", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "access_token_id"))
	public Set<PUAccessToken> getAccessTokens() {
		return accessTokens;
	}

	public void setAccessTokens(Set<PUAccessToken> accessTokens) {
		this.accessTokens = accessTokens;
	}
}
