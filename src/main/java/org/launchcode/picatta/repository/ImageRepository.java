package org.launchcode.picatta.repository;

import org.launchcode.picatta.data.Image;
import org.launchcode.picatta.data.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByFileName(String file_name);

}