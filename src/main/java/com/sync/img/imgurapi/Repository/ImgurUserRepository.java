package com.sync.img.imgurapi.Repository;

import com.sync.img.imgurapi.model.ImgrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImgurUserRepository extends JpaRepository<ImgrUser, Long> {
    ImgrUser findByUsername(String username);
}
