package com.cs4750.p5.controller;

import com.cs4750.p5.entity.Album;
import com.cs4750.p5.entity.Song;
import com.cs4750.p5.service.AlbumService;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService service;

    AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping("/create/{user_id}")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album, @PathVariable Integer user_id) { 
        return service.createAlbum(album, user_id);
    }

    @GetMapping("")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return service.getAllAlbums();
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Album> getAlbum(@PathVariable Integer albumId) { 
        return service.getAlbum(albumId);
    }

    @GetMapping("/{albumId}/songs")
    public ResponseEntity<List<Song>> getAlbumSongs(@PathVariable Integer albumId) { 
        return service.getAlbumSongs(albumId);
    }

    @PutMapping("/update/{albumId}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Integer albumId, @RequestBody Album newAlbum) { 
        return service.updateAlbum(albumId, newAlbum);
    }

    @DeleteMapping("/delete/{albumId}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable Integer albumId) { 
        return service.deleteAlbum(albumId);
    }
}
