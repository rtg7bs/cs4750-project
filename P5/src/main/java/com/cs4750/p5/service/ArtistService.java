package com.cs4750.p5.service;

import org.springframework.http.ResponseEntity;

import com.cs4750.p5.entity.Artist;

public interface ArtistService {
    public abstract ResponseEntity<Artist> createArtist(Artist artist);
    public abstract ResponseEntity<Artist> updateArtist(Integer userId, Artist artist);
    public abstract ResponseEntity<Artist> deleteArtist(Integer userId);
    public abstract ResponseEntity<Artist> getArtist(Integer userId);
}
