package com.cs4750.p5.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "songId")
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Integer songId;

    @Column(name = "title", nullable = false, length = 254)
    private String title;

    @Column(name = "duration")
    private Integer duration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @ManyToMany(mappedBy = "playlist_songs") // referenced by Playlist entity
    @JsonIgnore
    private List<Playlist> playlists = new ArrayList<>();

    @ManyToMany(mappedBy = "album_songs") // referenced by Album entity
    @JsonIgnore
    private List<Album> albums = new ArrayList<>();

    public Song() {}

    public Song(Integer songId, String title, Integer duration, Date releaseDate, List<Playlist> playlists, List<Album> albums) {
        this.songId = songId;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.playlists = playlists;
        this.albums = albums;
    }

    // Getters and setters
    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
