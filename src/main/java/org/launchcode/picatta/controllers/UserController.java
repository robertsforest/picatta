package org.launchcode.picatta.controllers;


import org.launchcode.picatta.data.User;
import org.launchcode.picatta.exception.ResourceNotFoundException;
import org.launchcode.picatta.repository.UserRepository;
import org.launchcode.picatta.security.CurrentUser;
import org.launchcode.picatta.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}