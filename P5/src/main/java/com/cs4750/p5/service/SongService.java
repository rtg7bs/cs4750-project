package com.cs4750.p5.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.cs4750.p5.entity.Song;

public interface SongService {
    public abstract ResponseEntity<Song> createSong(Song Song);
    public abstract ResponseEntity<List<Song>> getAllSongs();
    public abstract ResponseEntity<Song> getSongById(Integer id);
    public abstract ResponseEntity<Song> updateSong(Integer id, Song Song);
    public abstract ResponseEntity<Song> deleteSong(Integer id);
}