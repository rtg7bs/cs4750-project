package com.cs4750.p5.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="artist")
public class Artist {
    @Id
    @Column(name="user_id")
    private Integer userId;

    @Column(name="artist_name")
    private String artistName;

    @Column(name="description")    
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @MapsId("userId")
    private User user;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL) // referenced by Album entity
    @JsonBackReference
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "artist_performs",
        joinColumns = @JoinColumn(name = "user_id"), // joinColumns refers to fkey cols in the cur entity
        inverseJoinColumns = @JoinColumn(name = "song_id") // inverseJoinColumns refers to fkey cols in the related entity
    )
    @JsonIgnore
    private List<Song> songs = new ArrayList<>();

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

    public List<Album> getAlbums() {
      return albums;
    }

    public void setAlbums(List<Album> albums) {
      this.albums = albums;
    }

    public List<Song> getSongs() {
      return songs;
    }

    public void setSongs(List<Song> songs) {
      this.songs = songs;
    }
}
