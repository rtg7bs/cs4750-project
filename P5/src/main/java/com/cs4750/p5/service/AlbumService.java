package com.cs4750.p5.service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.cs4750.p5.entity.Album;
import com.cs4750.p5.entity.Song;

public interface AlbumService {
    public abstract ResponseEntity<Album> createAlbum(Album album, Integer user_Id);
    public abstract ResponseEntity<List<Album>> getAllAlbums();
    public abstract ResponseEntity<Album> getAlbum(Integer albumId);
    public abstract ResponseEntity<List<Song>> getAlbumSongs(Integer albumId);
    public abstract ResponseEntity<Album> updateAlbum(Integer albumId, Album album);
    public abstract ResponseEntity<Album> deleteAlbum(Integer albumId);
}
