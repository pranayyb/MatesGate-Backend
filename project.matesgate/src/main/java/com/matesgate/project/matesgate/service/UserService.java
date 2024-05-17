package com.matesgate.project.matesgate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matesgate.project.matesgate.model.User;
import com.matesgate.project.matesgate.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public Iterable<User> listAll(){
		return this.userRepo.findAll();
	}
	
	public void saveorUpdate(User user) {
		userRepo.save(user);
	}
	public User getUserById(long id) {
		return userRepo.findById(id).get();
	}
	
	public void update(User user,int id) {
		userRepo.save(user);
	}
	public void delete(long id) {
		userRepo.deleteById(id);
	}
	

}