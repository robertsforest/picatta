package org.launchcode.picatta.controllers;

import org.launchcode.picatta.data.AmazonClient;
import org.launchcode.picatta.data.Image;
import org.launchcode.picatta.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam String email) {
        Image image = new Image();
        String fileName = amazonClient.uploadFile(file, email);
        image.setFileName(fileName);
        Image result = imageRepository.save(image);
        return fileName;
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}