package com.Blog.App.Entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Table(name="UserData")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity implements UserDetails  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	
//	@NotEmpty
	@Size(min = 3,message = "USERNAME CANNOT BE LESS THAN 3 LETTERS")
	@Column(nullable = false , length = 100)
	private String userName;
	
	
//	@Email(message="Please provide a valid email address")
//	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@Column(unique = true)
	private String userEmail;
	

//	@NotEmpty
//	@Size(min=3,max=300 , message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String userPassword;
	
	
//	@NotEmpty(message = "Enter your Bio")
	private String userAbout;
	
	// A user can have multiple post so List of post
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	List<PostEntity> posts=new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Role>roles=new HashSet<Role>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> grantedAuth=this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return grantedAuth;
	}

	@Override
	public String getPassword() {
		
		return this.userPassword;
	}

	@Override
	public String getUsername() {
		
		return this.userEmail;
	}



}
