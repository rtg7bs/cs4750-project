package com.cs4750.p5.controller;

import java.util.List;

import com.cs4750.p5.entity.Playlist;
import com.cs4750.p5.entity.Song;
import com.cs4750.p5.service.PlaylistService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService service;
    PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        return service.createPlaylist(playlist);
    }

    @GetMapping("")
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return service.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable("id") Integer id) {
        return service.getPlaylist(id);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getPlaylistSongs(@PathVariable("id") Integer id) { 
        return service.getPlaylistSongs(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") Integer id, @RequestBody Playlist playlist) {
       return service.updatePlaylist(id, playlist);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Playlist> deletePlaylist(@PathVariable("id") Integer id) {
        return service.deletePlaylist(id);
    }

    @PutMapping("/{playlistId}/add/{songId}")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer songId) { 
        return service.addSongToPlaylist(playlistId, songId);
    }

    @PutMapping("/{playlistId}/delete/{songId}")
    public ResponseEntity<Playlist> deleteSongFromPlaylist(@PathVariable Integer playlistId, @PathVariable Integer songId) { 
        return service.deleteSongFromPlaylist(playlistId, songId);
    }
}