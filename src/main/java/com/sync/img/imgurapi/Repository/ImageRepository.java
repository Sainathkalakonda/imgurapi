package com.sync.img.imgurapi.Repository;

import com.sync.img.imgurapi.model.Image;
import com.sync.img.imgurapi.model.ImgrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findImagesByUsername(String username);
    Image findByDeleteHash(String deleteHash);
    void deleteByDeleteHash(String deleteHash);
}
