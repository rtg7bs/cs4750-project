package com.cs4750.p5.service;

import java.util.Optional;
import java.util.List;

import com.cs4750.p5.entity.Playlist;
import com.cs4750.p5.entity.Song;
import com.cs4750.p5.repository.PlaylistRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository repo;

    public ResponseEntity<Playlist> createPlaylist(Playlist playlist) {
        try {
            Playlist _playlist = repo.save(playlist);
            return new ResponseEntity<>(_playlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        try {
            List<Playlist> playlistList = repo.findAll();
            if (playlistList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Playlist>>(playlistList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Playlist> getPlaylist(Integer id) {
        Optional<Playlist> playlistData = repo.findById(id); // findById() is built-in for JPA

        if (playlistData.isPresent()) {
            return new ResponseEntity<>(playlistData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Song>> getPlaylistSongs(Integer id) {
        try {
            Optional<Playlist> playlistData = repo.findById(id);
            if (playlistData.isPresent()) {
                return new ResponseEntity<>(playlistData.get().getSongs(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Playlist> updatePlaylist(Integer id, Playlist playlist) {
        Optional<Playlist> playlistData = repo.findById(id);

        if (playlistData.isPresent()) {
            Playlist _playlist = playlistData.get();
            _playlist.setPlaylistName(playlist.getPlaylistName());
            _playlist.setNumOfSongs(playlist.getNumOfSongs());
            _playlist.setDuration(playlist.getDuration());
            _playlist.setDescription(playlist.getDescription());
            _playlist.setStatus(playlist.getStatus());
            return new ResponseEntity<>(repo.save(_playlist), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Playlist> deletePlaylist(Integer id) {
        try {
            Optional<Playlist> playlistData = repo.findById(id);
            if (playlistData.isPresent()) {
                repo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}