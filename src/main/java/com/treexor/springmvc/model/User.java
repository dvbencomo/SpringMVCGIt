package com.treexor.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="USERS")
@ApiModel(value="UserModel", description="Atributos del Modelo de datos")
public class User {
	
    @ApiModelProperty(value = "1", dataType = "java.lang.Integer", required = false)
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @ApiModelProperty(value = "1111", dataType = "java.lang.Integer", required = true)
    @NotEmpty
	@Column(name="SSO_ID", unique=true, nullable=false)
	private String ssoId;
	
    @ApiModelProperty(value = "9876", dataType = "java.lang.String", required = false)
	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
	private String password;
		
    @ApiModelProperty(value = "Daniel", dataType = "java.lang.String", required = false)
	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

    @ApiModelProperty(value = "Velazquez", dataType = "java.lang.String", required = false)
	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

    @ApiModelProperty(value = "dvbencomo@gmail.com", dataType = "java.lang.String", required = false)
	@NotEmpty
	@Column(name="EMAIL", nullable=false)
	private String email;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}


	
}
