package com.cs4750.p5.service;

import com.cs4750.p5.entity.Song;
import com.cs4750.p5.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
     public ResponseEntity<List<Song>> getAllSongs() {
        try {
        List<Song> songList = this.songRepository.findAll();
            if (songList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Song> getSongById(Integer id) {
        Optional<Song> songData = songRepository.findById(id);

        return songData.map(song -> new ResponseEntity<>(song, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Override
    public ResponseEntity<Song> createSong(Song song) { // our db doesnt refernece artist in song table, idk if is hsould still have it becaus eof the song belongs to db
        try {
            Song _song = songRepository.save(new Song(song.getSongId(), song.getTitle(), song.getDuration(), song.getReleaseDate()));
            return new ResponseEntity<>(_song, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<Song> updateSong(Integer id, Song song) {
        Optional<Song> songData = songRepository.findById(id);

        if (songData.isPresent()) {
            Song _song = songData.get();
            _song.setTitle(song.getTitle());
            _song.setDuration(song.getDuration());
            _song.setReleaseDate(song.getReleaseDate());

            return new ResponseEntity<>(songRepository.save(_song), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @Override
    public ResponseEntity<Song> deleteSong(Integer id) {
        try {
            Optional<Song> songData = songRepository.findById(id);
            if (songData.isPresent()) {
                songRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
               } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


