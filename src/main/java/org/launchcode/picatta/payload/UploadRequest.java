package org.launchcode.picatta.payload;

import org.launchcode.picatta.data.User;

import javax.validation.constraints.NotBlank;

public class UploadRequest {
    @NotBlank
    private String fileName;

    @NotBlank
    private String filePath;

//    @NotBlank
//    private User user;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
