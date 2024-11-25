package com.cs4750.p5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cs4750.p5.entity.Artist;
import com.cs4750.p5.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository repository;

    public ResponseEntity<Artist> createArtist(Artist artist) {
        return null;
    }

    public ResponseEntity<Artist> getArtist(Integer userId) {
        Optional<Artist> artistData = repository.findById(userId);
        if (artistData.isPresent()) {
            return new ResponseEntity<>(artistData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Artist> updateArtist(Integer userId, Artist artist) {
        return null;
    }

    public ResponseEntity<Artist> deleteArtist(Integer userId) {
        return null;
    }
}
