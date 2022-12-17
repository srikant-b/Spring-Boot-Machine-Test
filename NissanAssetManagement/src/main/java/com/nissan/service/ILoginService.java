package com.nissan.service;

import com.nissan.common.APIResponse;
import com.nissan.dto.LoginDTO;

public interface ILoginService {

	APIResponse findByUserNameAndPassword(LoginDTO loginDTO);
	
}
