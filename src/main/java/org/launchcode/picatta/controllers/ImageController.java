package org.launchcode.picatta.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.launchcode.picatta.data.AwsStorageService;
import org.launchcode.picatta.data.Image;
import org.launchcode.picatta.data.User;
import org.launchcode.picatta.payload.ApiResponse;
import org.launchcode.picatta.payload.AuthResponse;
import org.launchcode.picatta.payload.LoginRequest;
import org.launchcode.picatta.payload.UploadRequest;
import org.launchcode.picatta.repository.ImageRepository;
import org.launchcode.picatta.repository.UserRepository;
import org.launchcode.picatta.security.CurrentUser;
import org.launchcode.picatta.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class ImageController {
    private static final Regions clientRegion = Regions.US_EAST_2;
    private static final String bucketName = "picatta-images";
    private static final String SUFFIX = "/";
    private static final String imagePath = "M:\\picatta\\tmp";

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listImages")
    public List<Image> listImages(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.get().getImages();
    }

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(MultipartHttpServletRequest request){
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(request.getParameter("email")).toUri();
//
//        if (request.getFileNames().hasNext()) {
//            //1. get the files from the request object
//            Iterator<String> itr = request.getFileNames();
//            MultipartFile multipartFileImage = request.getFile(itr.next());
//            StringBuilder sb=new StringBuilder(multipartFileImage.getOriginalFilename());
//
//
//
//
//            String filename=sb.substring(sb.lastIndexOf("."), sb.length()); // getting file extension
//            filename="()"+filename; // concatenation unique value i.e email to its file name with extension
//
//            try {
//                File saveImage = new File(imagePath+filename);  //Local path for image file
//
//                AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                        .withRegion(clientRegion)
//                        .build();
//                PutObjectResult putResult = s3Client.putObject(
//                        bucketName,
//                        filename,
//                        saveImage
//                );
//                multipartFileImage.transferTo(saveImage);
//            }catch(Exception e) {
//                ResponseEntity.created(location)
//                        .body(new ApiResponse(true, "Unable to upload image due to internet connection failure. Try again later."));
//            }
//
//        }
//
//
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "Image uploaded successfully@"));
//    }

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("email") String email){
//
//        URI location = ServletUriComponentsBuilder
//        .fromCurrentContextPath().path("/user/me")
//        .buildAndExpand(email).toUri();
//
//        awsStorageService.store(file, email);
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "Image uploaded successfully@"));
//    }

}
