package com.DrK.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.DTO.UserinfoDTO;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idx;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name="password", nullable = false, length = 128)
	private String password;
	
	@Column(name="email", nullable = false, length = 100)
	private String email;

	@Temporal(TemporalType.DATE)
	private Date signupDate;
	
	@OneToMany
	@JoinColumn(name = "userIdx")
	private List<UserLikeCompanyEntity> companies = new ArrayList<UserLikeCompanyEntity>();

	public boolean equals(PasswordEncoder passwordEncoder, SignupDTO signupDTO){
		return this.email.equals(signupDTO.getEmail()) &&
			this.name.equals(signupDTO.getUserName()) &&
			passwordEncoder.matches(signupDTO.getPassword(), this.getPassword());		
	}

	public boolean equals(PasswordEncoder passwordEncoder, SigninDTO signinDTO) {
		return this.name.equals(signinDTO.getUserName()) &&
			passwordEncoder.matches(signinDTO.getPassword(), this.getPassword());
	}

	public boolean equals(UserinfoDTO userinfoDTO){
		boolean result =
		 this.email.equals(userinfoDTO.getEmail()) &&
			this.name.equals(userinfoDTO.getUserName());
		if (result){
			if (userinfoDTO.getSignupDate()!=null)
				result = this.signupDate.equals(userinfoDTO.getSignupDate());
		}
		return result;
	}
}
