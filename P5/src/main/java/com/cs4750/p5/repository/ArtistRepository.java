package com.cs4750.p5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs4750.p5.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}