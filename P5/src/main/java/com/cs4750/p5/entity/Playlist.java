package com.cs4750.p5.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id", nullable=false)
    private Integer playlistId;

    @Column(name = "user_id", nullable=false)
    private Integer userId;

    @Column(name = "playlist_name", nullable=false)
    private String playlistName;

    @Column(name = "creation_date", nullable=false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    // not sure about how to handle default value
    @Column(name = "num_of_songs")
    private Integer numOfSongs;

    // not sure about how to handle default value
    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    public Playlist() {

    }

    public Playlist(Integer playlistId, Integer userId, String playlistName, Date creationDate, Integer numOfSongs, Integer duration, String description, String status) {
        this.playlistId = playlistId;
        this.userId = userId;
        this.playlistName = playlistName;
        this.creationDate = creationDate;
        this.numOfSongs = numOfSongs;
        this.duration = duration;
        this.description = description;
        this.status = status;
    }

    // getters and setters
    public Integer getPlaylistId() { return playlistId; }

    public void setPlaylistId(Integer playlistId) { this.playlistId = playlistId; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer playlistId) { this.userId = userId; }

    public String getPlaylistName() { return playlistName; }

    public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }

    public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate;}

    public Integer getNumOfSongs() { return numOfSongs; }

    public void setNumOfSongs(Integer numOfSongs) { this.numOfSongs = numOfSongs; }

    public Integer getDuration() { return duration; }

    public void setDuration(Integer duration) { this.duration = duration; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", userId=" + userId +
                ", playlistName='" + playlistName + '\'' +
                ", creationDate=" + creationDate +
                ", numOfSongs=" + numOfSongs +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
