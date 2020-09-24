package org.launchcode.picatta.controllers;

import org.launchcode.picatta.data.Image;
import org.launchcode.picatta.data.User;
import org.launchcode.picatta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ImageController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listImages")
    public List<Image> listImages(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.get().getImages();
    }
}
