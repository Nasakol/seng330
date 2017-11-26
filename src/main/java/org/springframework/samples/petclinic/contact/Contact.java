package org.springframework.samples.petclinic.contact;

import java.util.Date;
import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.samples.petclinic.model.NamedEntity;


@Entity
public class Contact extends NamedEntity{
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide an name")
	private String name;
	
	@Column(name = "phone")
	@NotEmpty(message = "*Please provide an phone number")
	private int phone;
	
	@Column(name = "type")
	@NotEmpty(message = "*Please provide an type")
	private String type;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles;


	public Contact() {

	}

	public Contact(int id, String name, String email, int phone, String type) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.type = type;
	}
	

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [email=" + email + ", name=" + name + ", phone=" + phone + ", type=" + type
				+ "]";
	}

}
