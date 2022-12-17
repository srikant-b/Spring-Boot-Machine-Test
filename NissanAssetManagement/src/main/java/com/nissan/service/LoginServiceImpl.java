package com.nissan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nissan.common.APIResponse;
import com.nissan.dao.LoginDao;
import com.nissan.dto.LoginDTO;
import com.nissan.entity.Login;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public APIResponse findByUserNameAndPassword(LoginDTO loginDTO) {
		APIResponse apiResponse = new APIResponse();

		// verify user exist with given email and password
		Login userLogin = loginDao.findOneByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

		// response
		if (userLogin == null) {
			apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			apiResponse.setData("User login failed!!!");
		} else {
			// Storing more details
			Map<String, Object> data = new HashMap<>();
			data.put("role", userLogin.getUt_id());
			data.put("emailId", userLogin.getUsername());
			apiResponse.setData(data);
		}
		return apiResponse;

	}

}
