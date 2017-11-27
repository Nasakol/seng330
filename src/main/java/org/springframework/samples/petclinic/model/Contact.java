package org.springframework.samples.petclinic.model;

import java.util.Date;
import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;


@Entity
@Table(name = "contact")
public class Contact extends NamedEntity{

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "phone")
	@NotEmpty(message = "*Please provide an phone number")
	private int phone;

	//vendor or tempAgency
	@Column(name = "type")
	@NotEmpty(message = "*Please provide an type")
	private String type;



	public Contact() {

	}

	public Contact(int id, String name, String email, int phone, String type) {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [email=" + email + ", name=" + phone + ", type=" + type
				+ "]";
	}

}
