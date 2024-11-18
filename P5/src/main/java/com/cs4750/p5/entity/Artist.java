package com.cs4750.p5.entity;

// import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false)
    private Integer userId;

    @Column(name="artist_name")
    private String artistName;

    @Column(name="description")    
    private String description;

    Artist() {}

    Artist(int userId, String artistName, String description) {
        this.userId = userId;
        this.artistName = artistName;
        this.description = description;
    }

    @Override
    public String toString() {
      return "Artist{" + "user_id=" + this.userId + ", artist_name='" + this.artistName + '\'' + ", description='" + this.description + '\'' + '}';
    }

    public Integer getUserId() {
      return userId;
    }

    public String getArtistName() {
      return artistName;
    }

    public String getDescription() {
      return description;
    }
}
