package com.cs4750.p5.entity;

import jakarta.persistence.*;

@Entity
@Table(name="artist")
public class Artist {
    @Id
    private Integer userId;

    @Column(name="artist_name")
    private String artistName;

    @Column(name="description")    
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @MapsId("userId")
    private User user;

    Artist() {}

    Artist(Integer userId, String artistName, String description, User user) {
        this.userId = userId;
        this.artistName = artistName;
        this.description = description;
        this.user = user;
    }

    @Override
    public String toString() {
      return "Artist{" + "user_id=" + this.userId + ", artist_name='" + this.artistName + '\'' + ", description='" + this.description + '\'' + '}';
    }

    public Integer getUserId() {
      return userId;
    }

    public void setUserId(Integer userId) {
      this.userId = userId;
    }

    public User getUser() {
      return user;
    }

    public void setUser(User user) {
      this.user = user;
    }

    public String getArtistName() {
      return artistName;
    }

    public void setArtistName(String artistName) {
      this.artistName = artistName;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
}
