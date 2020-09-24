package org.launchcode.picatta.controllers;

import org.launchcode.picatta.data.AmazonClient;
import org.launchcode.picatta.data.Image;
import org.launchcode.picatta.data.User;
import org.launchcode.picatta.exception.ResourceNotFoundException;
import org.launchcode.picatta.repository.ImageRepository;
import org.launchcode.picatta.repository.UserRepository;
import org.launchcode.picatta.security.CurrentUser;
import org.launchcode.picatta.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam String email) {
        Image image = new Image();
        String fileName = amazonClient.uploadFile(file, email);
        image.setFileName(fileName);
        image.setOrigName(file.getOriginalFilename());
        image.setUser(userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email)));
        Image result = imageRepository.save(image);
        return fileName;
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestParam(value = "fileName") String fileName) {
        Optional<Image> file = imageRepository.findByFileName(fileName);
        if(file.isPresent()){
            imageRepository.delete(file.get());
        }
        return this.amazonClient.deleteFileFromS3Bucket(fileName);
    }
}