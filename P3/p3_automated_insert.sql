USE PLAYLIST_PROJECT_CS4750
GO

/* For all of the following, the 'SQL Server Import' extension was used to import data from local CSV files into temp tables, 
then the data in these temp tables were inserted into the tables that we manually created with column types and constraints

TUTORIAL: https://youtu.be/3alzYE7p_VY?si=qTNG2FGVZk6z31YT&t=312 */ 

-- DATA FOR SONGS
INSERT INTO song (title, duration, release_date)
SELECT title, duration, release_date FROM temp_songs;

DROP TABLE temp_songs;

-- DATA FOR ARTIST_SIGNED_WITH (RUN AFTER INSERTING DATA FOR ARTISTS AND RECORD LABELS)
INSERT INTO artist_signed_with (user_id, label_id)
SELECT user_id, label_id FROM temp_artist_signed_with;

DROP TABLE temp_artist_signed_with;

-- DATA FOR ALBUM_HAS (RUN AFTER INSERTING DATA FOR ALBUMS AND SONGS)
INSERT INTO album_has (album_id, song_id)
SELECT album_id, song_id FROM temp_album_has; 

DROP TABLE temp_album_has;

-- DATA FOR ARTIST_PERFORMS (RUN AFTER INSERTING DATA FOR ARTISTS AND SONGS)
INSERT INTO artist_performs (user_id, song_id)
SELECT user_id, song_id FROM temp_artist_performs;

DROP TABLE temp_artist_performs;

-- DATA FOR SONG_BELONGS_TO (RUN AFTER INSERTING DATA FOR SONGS AND GENRES)
INSERT INTO song_belongs_to (song_id, genre_id)
SELECT song_id, genre_id FROM temp_song_belongs_to;

DROP TABLE temp_song_belongs_to;