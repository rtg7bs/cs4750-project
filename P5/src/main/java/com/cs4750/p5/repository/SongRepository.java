package com.cs4750.p5.repository;


import com.cs4750.p5.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//public interface SongRepository extends JpaRepository<Song,Long> {
//    List<Song> findByTitleContaining(String Title);
//}
//
@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByTitleContaining(String Title);

}