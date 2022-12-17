package com.nissan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tblLogin")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer l_id;
	
	private String username;
	
	private String password;
	
	private Integer ut_id;
	
	@ManyToOne
	@JoinColumn(name = "ut_id", insertable = false, updatable = false)
	private UserType userType;
	
	@OneToOne(mappedBy = "login")
	@JsonIgnore
	private UserRegistration userRegistration;

	public void setL_id(Integer l_id) {
		this.l_id = l_id;
	}
	
	
	
}
