package com.cs4750.p5.controller;

import com.cs4750.p5.model.Song;
import com.cs4750.p5.repository.SongRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SongController {

    @Autowired
    SongRepository songRepository;

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getAllSongs(@RequestParam(required = false) String title) {
        try {
            List<Song> songs = new ArrayList<>();

            if (title == null) { // i dont think a title can be null, but followingh her code
                songRepository.findAll().forEach(songs::add);
            }
            else {
                songRepository.findByTitleContaining(title).forEach(songs::add);
            }
            if (songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(songs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") long id) {
        Optional<Song> songData = songRepository.findById(id);

        if (songData.isPresent()) {
            return new ResponseEntity<>(songData.get(), HttpStatus.OK); // Song found, return with OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Song not found, return NOT_FOUND
        }
    }

    @PostMapping("/songs")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        try {
            Song _song = songRepository
                    .save(new Song(song.getTitle(), song.getDuration(), song.getReleaseDate()));

            return new ResponseEntity<>(_song, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable("id") long id, @RequestBody Song song) {
        Optional<Song> songData = songRepository.findById(id);

        if (songData.isPresent()) {
            Song _song = songData.get();
            _song.setTitle(song.getTitle());
            _song.setDuration(song.getDuration());
            _song.setReleaseDate(song.getReleaseDate());

            return new ResponseEntity<>(songRepository.save(_song), HttpStatus.OK); // Update song and return updated data
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If song doesn't exist, return NOT_FOUND
        }
    }

    @DeleteMapping("/songs/{id}")
        public ResponseEntity<HttpStatus> deleteSong(@PathVariable("id") Integer id) {
        try {
            songRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/songs")
    public ResponseEntity<HttpStatus> deleteAllSongs() {
        try {
            songRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
