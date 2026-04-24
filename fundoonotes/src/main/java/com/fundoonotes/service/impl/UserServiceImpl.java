package com.fundoonotes.service.impl;

import com.fundoonotes.dto.request.UserRegisterRequestDto;
import com.fundoonotes.dto.response.UserResponseDto;
import com.fundoonotes.entity.User;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto register(UserRegisterRequestDto dto) {

        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getEmail()
        );
    }
}