package com.cs4750.p5.entity;

import jakarta.persistence.*;

@Entity
@Table(name="artist")
public class Artist {
    @Id
    private Integer userId;

    @OneToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @MapsId("userId")
    private User user;

    @Column(name="artist_name")
    private String artistName;

    @Column(name="description")    
    private String description;

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

    public User getUser() {
      return user;
    }

    public String getArtistName() {
      return artistName;
    }

    public String getDescription() {
      return description;
    }
}
