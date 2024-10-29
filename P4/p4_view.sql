USE PLAYLIST_PROJECT_CS4750
GO

-- View for users and the playlists they've made
CREATE VIEW UserPlaylists AS
(SELECT U.username, P.playlist_name, P.creation_date, P.num_of_songs, P.duration, P.description, P.status
 FROM [user] U
 INNER JOIN playlist P ON U.user_id = P.user_id);
GO

-- View for playlists and the songs they contain
CREATE VIEW PlaylistSongs AS
(SELECT PC.playlist_id, P.playlist_name, S.title
 FROM playlist_contains PC 
 INNER JOIN playlist P ON PC.playlist_id = P.playlist_id
 INNER JOIN song S ON PC.song_id = S.song_id);
GO

-- View for artists and the albums they've made
CREATE VIEW ArtistAlbums AS
(SELECT A.artist_name, ALB.album_name, ALB.num_of_songs, ALB.duration, ALB.release_date
 FROM artist A 
 INNER JOIN album ALB ON A.user_id = ALB.user_Id);
GO

-- View for albums and the songs they contain 
CREATE VIEW AlbumSongs AS
(SELECT AH.album_id, ALB.album_name, S.title
 FROM album_has AH
 INNER JOIN album ALB ON AH.album_id = ALB.album_id
 INNER JOIN song S ON AH.song_id = S.song_id);
GO

-- View for all songs in the DB
CREATE VIEW AllSongs AS
(SELECT song_id, title, duration, release_date
 FROM song);
GO

-- EXTRA: Check the views
-- SELECT * FROM UserPlaylists;
-- SELECT * FROM PlaylistSongs;
-- SELECT * FROM ArtistAlbums;
-- SELECT * FROM AlbumSongs;
-- SELECT * FROM AllSongs;