package com.cs4750.p5.repository;


import com.cs4750.p5.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Patient,Long> {
    List<Song> findByTitleContaining(String Title);
}
