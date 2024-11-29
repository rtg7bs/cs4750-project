package com.cs4750.p5.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.cs4750.p5.entity.Playlist;
import com.cs4750.p5.entity.Song;

public interface PlaylistService {
    public abstract ResponseEntity<Playlist> createPlaylist(Playlist playlist);
    public abstract ResponseEntity<List<Playlist>> getAllPlaylists();
    public abstract ResponseEntity<Playlist> getPlaylist(Integer id);
    public abstract ResponseEntity<List<Song>> getPlaylistSongs(Integer id);
    public abstract ResponseEntity<Playlist> updatePlaylist(Integer id, Playlist playlist);
    public abstract ResponseEntity<Playlist> deletePlaylist(Integer id);
}