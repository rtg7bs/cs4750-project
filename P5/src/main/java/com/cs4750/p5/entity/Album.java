package com.cs4750.p5.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
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

    Album() {}

    Album(Integer albumId, Artist artist, String albumName, Integer numOfSongs, Integer duration, LocalDate releaseDate) {
        this.albumId = albumId;
        this.artist = artist;
        this.albumName = albumName;
        this.numOfSongs = numOfSongs;
        this.duration = duration;
        this.releaseDate = releaseDate;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
}
