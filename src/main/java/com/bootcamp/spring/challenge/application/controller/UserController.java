package com.bootcamp.spring.challenge.application.controller;

import com.bootcamp.spring.challenge.application.dto.user.UserRequestDTO;
import com.bootcamp.spring.challenge.application.dto.user.UserResponseDTO;
import com.bootcamp.spring.challenge.application.usecase.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserUseCase userService;

    public UserController(final UserUseCase userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAllUsers() {
        final List<UserResponseDTO> response = userService.listAllUsers();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable final String userId) {
        final UUID userUUID = UUID.fromString(userId);

        final UserResponseDTO response = userService.getInfo(userUUID);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfoByUsername(@RequestParam final String username) {

        if (!userService.userExists(username)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final UserResponseDTO response = userService.getInfoByUsername(username);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> userSignUp(@RequestBody final UserRequestDTO userRequestDTO) {
        final UserResponseDTO response = userService.signUp(userRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
