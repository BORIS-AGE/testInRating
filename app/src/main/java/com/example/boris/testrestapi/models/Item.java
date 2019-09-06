package com.example.boris.testrestapi.models;

import java.util.List;

public class Item {

    private List<Liker> data;

    public Item(List<Liker> data) {
        this.data = data;
    }

    public List<Liker> getData() {
        return data;
    }

    public void setData(List<Liker> data) {
        this.data = data;
    }

    public class Liker{
        private String id;
        private String name;
        private Avatar avatar_image;

        public Liker(String id, String name, Avatar avatar_image) {
            this.id = id;
            this.name = name;
            this.avatar_image = avatar_image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Avatar getAvatar_image() {
            return avatar_image;
        }

        public void setAvatar_image(Avatar avatar_image) {
            this.avatar_image = avatar_image;
        }

        public class Avatar{
            private String url_medium;

            public Avatar(String url_medium) {
                this.url_medium = url_medium;
            }

            public String getUrl_medium() {
                return url_medium;
            }

            public void setUrl_medium(String url_medium) {
                this.url_medium = url_medium;
            }
        }
    }
}
