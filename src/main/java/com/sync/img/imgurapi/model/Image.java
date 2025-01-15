package com.sync.img.imgurapi.model;


import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false)
    private String imageId;

    public String getUrl() {
        return url;
    }

    private String url;

    //@Column(nullable = false)
    private String deleteHash;

    //@Column(nullable = false)
    private String link;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ImgrUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImgrUser getUser() {
        return user;
    }

    public void setUser(ImgrUser user) {
        this.user = user;
    }

    public void setUrl(String url) {
    }
}

