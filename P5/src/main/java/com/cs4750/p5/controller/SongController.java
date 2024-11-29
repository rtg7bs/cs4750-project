package com.cs4750.p5.controller;

import com.cs4750.p5.entity.Song;
import com.cs4750.p5.service.SongService;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

// @CrossOrigin
@RestController
@RequestMapping("/song")

public class SongController {
        private final SongService service;


    public SongController(SongService service) {
        this.service = service;
    }


    @GetMapping("")
        public ResponseEntity<List<Song>> getAllSongs() {
            return service.getAllSongs();
        }


        @PostMapping("/create/{user_id}")
        public ResponseEntity<Song> createSong(@RequestBody Song song) { // , @PathVariable Integer user_id
            return service.createSong(song);
        }


        @GetMapping("/{songId}")
        public ResponseEntity<Song> getSongById(@PathVariable Integer songId) {
            return service.getSongById(songId);
        }

        @PutMapping("/update/{songId}")
        public ResponseEntity<Song> updateSong(@PathVariable Integer songId, @RequestBody Song newSong) {
            return service.updateSong(songId, newSong);
        }

        @DeleteMapping("/delete/{songId}")
        public ResponseEntity<Song> deleteSong(@PathVariable Integer songId) {
            return service.deleteSong(songId);
    }
}