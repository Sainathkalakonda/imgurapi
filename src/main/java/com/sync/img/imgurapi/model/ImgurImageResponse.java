package com.sync.img.imgurapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Add this annotation
public class ImgurImageResponse {

    private Data data;
    private boolean success;
    private int status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
// Getters and Setters
@JsonIgnoreProperties(ignoreUnknown = true) // Add this annotation
    public static class Data {
        private String id;
        private String link;
        private String deletehash;

        // Getters and Setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDeletehash() {
            return deletehash;
        }

        public void setDeletehash(String deletehash) {
            this.deletehash = deletehash;
        }
    }
}


