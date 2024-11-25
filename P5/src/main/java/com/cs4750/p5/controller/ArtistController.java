package com.cs4750.p5.controller;

import com.cs4750.p5.entity.Artist;
import com.cs4750.p5.service.ArtistService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
