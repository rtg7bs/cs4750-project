package com.cs4750.p5.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "albumId")
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer albumId;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "num_of_songs")
    private Integer numOfSongs;

    @Column(name = "duration")
    private Integer duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // user_id col in album tb references user_id col in artist tb
    private Artist artist;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "album_has",
        joinColumns = @JoinColumn(name = "album_id"), // joinColumns refers to fkey cols in the cur entity
        inverseJoinColumns = @JoinColumn(name = "song_id") // inverseJoinColumns refers to fkey cols in the related entity
    )
    @JsonIgnore
    private List<Song> albumSongs = new ArrayList<>();

    Album() {}

    Album(Integer albumId, String albumName, Integer numOfSongs, Integer duration, LocalDate releaseDate, Artist artist, List<Song> albumSongs) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.numOfSongs = numOfSongs;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.albumSongs = albumSongs;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(Integer numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Song> getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(List<Song> songs) {
        this.albumSongs = songs;
    }
}
