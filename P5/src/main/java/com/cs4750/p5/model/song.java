package com.cs4750.p5.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Integer songId;

    @Column(name = "title", nullable = false, length = 254)
    private String title;

    @Min(1)
    @Column(name = "duration")
    private Integer duration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    public Song() {}

    public Song(Integer songId, String title, Integer duration, Date releaseDate) {
        this.songId = songId;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
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
