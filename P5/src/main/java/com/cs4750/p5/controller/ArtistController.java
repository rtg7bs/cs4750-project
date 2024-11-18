package com.cs4750.p5.controller;

import java.util.List;

import com.cs4750.p5.entity.Artist;
import com.cs4750.p5.repository.ArtistRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ArtistController {

	private final ArtistRepository repo;

	ArtistController(ArtistRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/artist")
	public List<Artist> all() {
		return repo.findAll();
	}

}
