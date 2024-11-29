package com.cs4750.p5.entity;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "playlistId")
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id", nullable=false)
    private Integer playlistId;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // user_id col in playlist tb references user_id col in user tb
    private User owningUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "playlist_contains",
        joinColumns = @JoinColumn(name = "playlist_id"), // joinColumns refers to fkey cols in the cur entity
        inverseJoinColumns = @JoinColumn(name = "song_id") // inverseJoinColumns refers to fkey cols in the related entity
    )
    @JsonIgnore
    private List<Song> playlistSongs = new ArrayList<>();

    public Playlist() {}

    public Playlist(Integer playlistId, String playlistName, Date creationDate, Integer numOfSongs, Integer duration, String description, String status, User owningUser, List<Song> playlistSongs) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.creationDate = creationDate;
        this.numOfSongs = numOfSongs;
        this.duration = duration;
        this.description = description;
        this.status = status;
        this.owningUser = owningUser;
        this.playlistSongs = playlistSongs;
    }

    // getters and setters
    public Integer getPlaylistId() { return playlistId; }

    public void setPlaylistId(Integer playlistId) { this.playlistId = playlistId; }

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

    public User getUser() { return owningUser; }

    public void setUser(User owningUser) { this.owningUser = owningUser; }

    public List<Song> getPlaylistSongs() { return playlistSongs; }

    public void setPlaylistSongs(List<Song> songs) { this.playlistSongs = songs; }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", user =" + owningUser +
                ", playlistName='" + playlistName + '\'' +
                ", creationDate=" + creationDate +
                ", numOfSongs=" + numOfSongs +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
