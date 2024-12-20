package com.cs4750.p5.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.cs4750.p5.entity.Album;
import com.cs4750.p5.entity.Artist;
import com.cs4750.p5.entity.Song;

public interface ArtistService {
    public abstract ResponseEntity<Artist> createArtist(Artist artist);
    public abstract ResponseEntity<List<Artist>> getAllArtists();
    public abstract ResponseEntity<Artist> getArtist(Integer userId);
    public abstract ResponseEntity<List<Album>> getArtistAlbums(Integer userId);
    public abstract ResponseEntity<List<Song>> getArtistSongs(Integer userId);
    public abstract ResponseEntity<Artist> updateArtist(Integer userId, Artist artist);
    public abstract ResponseEntity<Artist> deleteArtist(Integer userId);
}
