package com.fundoonotes.service.impl;

import com.fundoonotes.dto.request.UserLoginRequestDto;
import com.fundoonotes.dto.request.UserRegisterRequestDto;
import com.fundoonotes.dto.response.LoginResponseDto;
import com.fundoonotes.dto.response.UserResponseDto;
import com.fundoonotes.entity.User;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegisterRequestDto dto) {

        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getEmail()
        );
    }

    @Override
    public LoginResponseDto login(UserLoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new LoginResponseDto("Login successful (JWT coming next step)");
    }
}