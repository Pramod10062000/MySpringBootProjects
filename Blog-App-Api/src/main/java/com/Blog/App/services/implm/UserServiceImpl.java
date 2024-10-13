package com.Blog.App.services.implm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Blog.App.Entites.Role;
import com.Blog.App.Entites.UserEntity;
import com.Blog.App.repositories.RoleRepo;
import com.Blog.App.repositories.UserRepository;
import com.Blog.App.services.UserService;
import com.Blog.App.Exceptions.InvalidIdException;
import com.Blog.App.Exceptions.ResourceNotFoundException;
import com.Blog.App.Payloads.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
		
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@Autowired
	private RoleRepo roleRepo;
	

	@Override
	public UserDto registerUser(UserDto user) {
//		System.out.println(user);
		UserEntity u=this.modelMapper.map(user, UserEntity.class);
		
		//password encoding
		String EncodedPassword=this.passwordEncoder.encode(u.getPassword());
		u.setUserPassword(EncodedPassword);
		
		//roles 502 - is normal role/Role_User.---assiging roles to the new user
		Role r=this.roleRepo.findById(502).get();
//		System.out.println(r);
		u.getRoles().add(r);
		UserEntity userEntity=repository.save(u);
		return this.modelMapper.map(userEntity, UserDto.class);
	}
	
	@Override
	public UserDto createUser(UserDto user) {
		// no password encoding 
		//no role assigning
	UserEntity u=this.modelMapper.map(user, UserEntity.class);
	UserEntity insertedUser = repository.save(u);
	UserDto dto=this.modelMapper.map(insertedUser, UserDto.class);
	System.out.println("New User Created ");
	return dto;
	}

	@Override
	public UserDto createAdminUser(UserDto user) {
         UserEntity u=this.modelMapper.map(user, UserEntity.class);
		
		//password encoding
		String EncodedPassword=this.passwordEncoder.encode(u.getPassword());
		u.setUserPassword(EncodedPassword);
		
		//roles 501 - is Admin role/ROLE_ADMIN.
		Role r=this.roleRepo.findById(501).get();
		System.out.println(r);
		u.getRoles().add(r);
		UserEntity userEntity=repository.save(u);
		return this.modelMapper.map(userEntity, UserDto.class);
			
	}

	@Override
	public UserDto updateUser(UserDto user, Integer id) {
		UserEntity userObj =repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		userObj.setUserName(user.getUserName());
		userObj.setUserPassword(user.getPassword());
		userObj.setUserId(user.getId());
		userObj.setUserEmail(user.getUserEmail());
		userObj.setUserAbout(user.getAbout());
	    UserEntity updatedUser=this.repository.save(userObj);
	    UserDto dto=this.modelMapper.map(updatedUser, UserDto.class);
		return dto;
	}

	@Override
	public UserDto getUserById(Integer id) {
		UserEntity user=repository.findById(id).orElseThrow(()-> new InvalidIdException(id));
		UserDto dto = this.modelMapper.map(user, UserDto.class);
		return dto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<UserEntity> AllUsers=repository.findAll();
		List<UserDto> allUsers=AllUsers.stream().map((m)->this.modelMapper.map(m, UserDto.class)).collect(Collectors.toList());
		return allUsers;
	}

	@Override
	public void Delete(Integer id) {
		UserEntity user=repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		repository.delete(user);
		
	}

}
