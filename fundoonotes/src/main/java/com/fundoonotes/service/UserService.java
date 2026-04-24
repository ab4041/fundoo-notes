package com.fundoonotes.service;

import com.fundoonotes.dto.request.UserLoginRequestDto;
import com.fundoonotes.dto.request.UserRegisterRequestDto;
import com.fundoonotes.dto.response.LoginResponseDto;
import com.fundoonotes.dto.response.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserRegisterRequestDto dto);

    LoginResponseDto login(UserLoginRequestDto dto);
}