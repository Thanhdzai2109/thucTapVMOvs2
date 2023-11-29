package com.example.jwt.demo.service;

import com.example.jwt.demo.dto.AppUserDTO;
import com.example.jwt.demo.dto.UserEditDTO;
import org.springframework.http.ResponseEntity;

public interface AppUserService {

    ResponseEntity<?> registerUser(AppUserDTO appUserDTO);

    ResponseEntity<?> updateUser(UserEditDTO appUserDTO);

    ResponseEntity<?> removeUser(int user_id);

    ResponseEntity<?> searchUser(int user_id);

    ResponseEntity<?> getRefreshToken(String refresh_token);

    ResponseEntity<?> loginUser(AppUserDTO appUserDTO);

}
