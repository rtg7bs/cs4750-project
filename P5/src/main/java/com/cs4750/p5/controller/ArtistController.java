package com.cs4750.p5.controller;

import com.cs4750.p5.entity.Artist;
import com.cs4750.p5.service.ArtistService;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/artist")
public class ArtistController {

	private final ArtistService service;

	ArtistController(ArtistService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
		return service.createArtist(artist);
	}

	@GetMapping("")
	public ResponseEntity<List<Artist>> getAllArtists() {
		return service.getAllArtists();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Artist> getArtist(@PathVariable Integer userId) {
		return service.getArtist(userId);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<Artist> updateArtist(@PathVariable Integer userId, @RequestBody Artist artist) {
		return service.updateArtist(userId, artist);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Artist> deleteArtist(@PathVariable Integer userId) {
		return service.deleteArtist(userId);
	}
}
