package com.cs4750.p5.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cs4750.p5.entity.Artist;
import com.cs4750.p5.entity.User;
import com.cs4750.p5.repository.ArtistRepository;
import com.cs4750.p5.repository.UserRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository repository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Artist> createArtist(Artist artist) {
        try {
            Optional<User> userData = userRepository.findById(artist.getUserId());
            if (userData.isPresent()) {
                repository.save(artist);
                return new ResponseEntity<>(artist, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artistList = repository.findAll();
        return new ResponseEntity<>(artistList, HttpStatus.OK);
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
