package com.nissan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nissan.common.APIResponse;
import com.nissan.dao.UserDao;
import com.nissan.dto.UserDetailsDTO;
import com.nissan.entity.UserRegistration;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public APIResponse getAllUsers() {
		APIResponse apiResponse = new APIResponse();
		List<UserRegistration> userList = userDao.findAll();

		// if user is not found
		if (userList.isEmpty()) {
			apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			apiResponse.setData("Database is empty!!!");
		} else {
			apiResponse.setData(userList);
		}
		return apiResponse;
	}

	@Override
	public APIResponse findUserById(Integer id) {
		APIResponse apiResponse = new APIResponse();

		UserRegistration user = userDao.findById(id).orElse(null);

		// if user is not found
		if (user == null) {
			apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			apiResponse.setData("User not found!!!");
		} else {
			apiResponse.setData(user);
		}
		return apiResponse;
	}

	@Override
	public APIResponse addUserDetails(UserDetailsDTO userDetailsDTO, Integer l_id) {
		APIResponse apiResponse = new APIResponse();

		UserRegistration newUser = new UserRegistration(userDetailsDTO.getFirstName(), userDetailsDTO.getLastName(),
				userDetailsDTO.getAge(), userDetailsDTO.getGender(), userDetailsDTO.getAddress(),
				userDetailsDTO.getPhoneNumber(), l_id);
		userDao.save(newUser);
		
		apiResponse.setData("User details saved successfully!!!");
		
		return apiResponse;
	}

	@Override
	public APIResponse deleteUserbyId(Integer id) {
		APIResponse apiResponse = new APIResponse();
		
		UserRegistration deleteUser = userDao.findById(id).orElse(null) ;
		
		// if user is not found
		if(deleteUser == null) {
			apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			apiResponse.setData("User not found!!!");
		} else {
			userDao.delete(deleteUser);
			apiResponse.setData("User " + deleteUser.getFirstName() + " deleted successfully!!!");
		}
		return apiResponse;
	}

	@Override
	public APIResponse updateUserDetailsById(UserDetailsDTO userDetailsDTO, Integer id) {
		APIResponse apiResponse = new APIResponse();
		
		UserRegistration updateUser = userDao.findById(id).orElse(null);
		
		// if user is not found
		if(updateUser == null) {
			apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			apiResponse.setData("User not found!!!");
		} else {
			// check if the user details are proper
			if(userDetailsDTO.getFirstName() != null && !userDetailsDTO.getFirstName().isBlank()) {
				updateUser.setFirstName(userDetailsDTO.getFirstName());
			}
			if(userDetailsDTO.getLastName() != null && !userDetailsDTO.getLastName().isBlank()) {
				updateUser.setLastName(userDetailsDTO.getLastName());
			}
			if(userDetailsDTO.getAddress() != null && !userDetailsDTO.getAddress().isBlank()) {
				updateUser.setAddress(userDetailsDTO.getAddress());
			}
			if(userDetailsDTO.getPhoneNumber() > 7000000000l && userDetailsDTO.getPhoneNumber() < 9999999999l) {
				updateUser.setPhoneNumber(userDetailsDTO.getPhoneNumber());
			}
			userDao.save(updateUser);
			apiResponse.setData("User " + updateUser.getFirstName() + " details updated successfully!!!");
		}
		return apiResponse;
	}

}
