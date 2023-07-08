package com.manit.scs.demo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.manit.scs.demo.validation.FieldMatch;
import com.manit.scs.demo.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class CrmUser {

	private Integer Id;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	@NotNull(message = "is required")
	private int year;
	
	@NotNull(message = "is required")
	private String departmentId;
	
	
	private String image;

	public CrmUser() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	

	public String getImage() {
		return image;
	}



	

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	
	
	
	

}
