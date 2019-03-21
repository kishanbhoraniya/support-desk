package com.finalhints.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.finalhints.entity.Role;
import com.finalhints.entity.Status;
import com.finalhints.entity.User;
import com.finalhints.reposioty.RoleRepository;
import com.finalhints.reposioty.StatusRepository;
import com.finalhints.reposioty.UserRepository;

@Component
public class DataLoader {

	private UserRepository userRepository;

	private StatusRepository statusRepository;

	private RoleRepository roleRepository;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public DataLoader(UserRepository userRepository, StatusRepository statusRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.statusRepository = statusRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		createData();
	}

	private void createData() {
		createRoles();
		createStatus();
		createUsers();
	}

	private void createUsers() {
		createUser("admin@gmail.com", "admin", "Admin", "", "admin");
	}

	private void createStatus() {
		createStatus("Open");
		createStatus("In Progress");
		createStatus("Closed");
	}

	private void createRoles() {
		createRole("admin");
		createRole("manager");
		createRole("agent");
		createRole("user");
	}

	private void createRole(String roleName) {
		Optional<Role> role = roleRepository.findByName(roleName);
		if (!role.isPresent()) {
			Role newRole = new Role();
			newRole.setName(roleName);
			roleRepository.save(newRole);
		}
	}

	private void createStatus(String statusName) {
		Optional<Status> status = statusRepository.findByName(statusName);
		if (!status.isPresent()) {
			Status newStatus = new Status();
			newStatus.setName(statusName);
			statusRepository.save(newStatus);
		}
	}

	private void createUser(String email, String password, String fName, String lName, String role) {
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent()) {
			User newUser = new User();
			newUser.setEmail(email);
			newUser.setPassword(passwordEncoder.encode(password));
			newUser.setFirstName(fName);
			newUser.setLastName(lName);
			newUser.setNumber("");
			newUser.setRole(roleRepository.findByName(role).get());
			newUser.setActive(true);
			userRepository.save(newUser);
		}
	}

}