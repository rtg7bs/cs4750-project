package com.cs4750.p5.service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.cs4750.p5.entity.Album;

public interface AlbumService {
    public abstract ResponseEntity<Album> createAlbum(Album album, Integer user_Id);
    public abstract ResponseEntity<List<Album>> getAllAlbums();
    public abstract ResponseEntity<Album> getAlbum(Integer albumId);
    public abstract ResponseEntity<Album> updateArtist(Integer albumId, Album album);
    public abstract ResponseEntity<Album> deleteArtist(Integer albumId);
}
