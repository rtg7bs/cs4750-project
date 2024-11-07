USE PLAYLIST_PROJECT_CS4750;
GO

-- function 1: get all songs on playlist 
CREATE FUNCTION GetAllSongsFromPlaylist
(
    @playlist_id INT
)
RETURNS TABLE
AS 
RETURN
(
    SELECT s.title, artist_name AS artist, s.duration 
    FROM playlist_contains pc 
    INNER JOIN song s ON s.song_id = pc.song_id
    INNER JOIN artist_performs ap ON s.song_id = ap.song_id
    INNER JOIN artist a ON a.user_id = ap.user_id
    WHERE playlist_id = @playlist_id
);
GO

-- to call function
SELECT * FROM GetAllSongsFromPlaylist(1);
GO

-- function 2: get all playlists given a user_id
CREATE FUNCTION GetAllPlaylistsByUser
(
    @user_id INT
)
RETURNS TABLE
AS 
RETURN
(
    SELECT playlist_id, playlist_name, creation_date, num_of_songs, duration
    FROM playlist p
    WHERE p.user_id = @user_id
);
GO

-- to call function
SELECT * FROM GetAllPlaylistsByUser(5);
GO

-- function 3: get user's most popular artist based on all of user's playlists
CREATE FUNCTION GetMostPopularArtistForUser
(
    @user_id INT
)
RETURNS TABLE
AS 
RETURN
(
    SELECT TOP 1 artist_name AS top_artist, COUNT(*) AS num_songs
    FROM playlist p
    INNER JOIN playlist_contains pc ON p.playlist_id = pc.playlist_id
    INNER JOIN artist_performs ap ON pc.song_id = ap.song_id
    INNER JOIN artist a ON a.user_id = ap.user_id
    WHERE p.user_id = @user_id
    GROUP BY a.artist_name
    ORDER BY num_songs DESC 
);
GO

-- to call function
SELECT * FROM GetMostPopularArtistForUser(1);
GO
