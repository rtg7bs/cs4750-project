package com.cs4750.p5.controller;

import java.util.List;

import com.cs4750.p5.entity.Playlist;
import com.cs4750.p5.repository.PlaylistRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlaylistController {

    private final PlaylistRepository repo;

    PlaylistController(PlaylistRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/playlists")
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return repo.findAll()
    }

    @GetMapping("/playlists/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable("id") Integer id) {
        Optional<Playlist> playlistData = repo.findById(id); // findById() is built-in for JPA

        if (playlistData.isPresent()) {
            return new ResponseEntity<>(playlistData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/playlists")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        try {
            Playlist _playlist = repo.save(playlist);
            return new ResponseEntity<>(_playlist, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/playlists/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") Integer id, @RequestBody Playlist playlist) {
        Optional<Playlist> playlistData = repo.findById(id);

        if (playlistData.isPresent()) {
            Playlist _playlist = playlistData.get();
            _playlist.setPlaylistName(playlist.getPlaylistName());
            _playlist.setNumOfSongs(playlist.getNumOfSongs());
            _playlist.setDuration(playlist.getDuration());
            _playlist.setDescription(playlist.getDescription());
            _playlist.setStatus(playlist.getStatus());
            return new ResponseEntity<>(repo.save(_playlist), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/playlists{id}")
    public ResponseEntity<HttpStatus> deletePlaylist(@PathVariable("id") Integer id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<HttpStatus> deletePlaylist(@PathVariable Integer id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}