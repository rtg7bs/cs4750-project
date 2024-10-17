-- Average song duration for each individual album
SELECT A.album_name, AVG(S.duration) AS avg_song_duration 
FROM album A
	INNER JOIN album_has A_H ON A.album_id = A_H.album_id 
	INNER JOIN song S ON A_H.song_id = S.song_id
	GROUP BY A.album_name;

-- Album with the most recent release date 
SELECT TOP 1 album_name, release_date FROM album 
ORDER BY release_date DESC; 

-- Genre with the greatest number of songs
SELECT TOP 1 G.genre_name, COUNT(B.song_id) AS num_songs 
FROM genre G 
INNER JOIN song_belongs_to B ON G.genre_id = B.genre_id 
GROUP BY G.genre_name 
ORDER BY num_songs DESC;

-- Most popular album
SELECT A.album_id, A.album_name
FROM album A 
WHERE album_id IN (
   SELECT TOP 1 album_has.album_id
   FROM playlist_contains
   LEFT JOIN album_has ON album_has.song_id = playlist_contains.song_id
   GROUP BY album_id
   ORDER BY COUNT(playlist_id) DESC
);

-- Most popular artist (in the most playlists)
SELECT A.user_id, A.artist_name
FROM artist A 
WHERE user_id IN (
   SELECT TOP 1 artist_performs.user_id
   FROM playlist_contains
   LEFT JOIN artist_performs ON artist_performs.song_id = playlist_contains.song_id
   GROUP BY user_id
   ORDER BY COUNT(playlist_id) DESC
);

-- Artist with the most songs
SELECT artist.artist_name, temp.total_songs
FROM artist INNER JOIN (
    SELECT TOP 1 user_id, COUNT(song_id) AS total_songs
    FROM artist_performs
    GROUP BY user_id
    ORDER BY total_songs DESC
) AS temp ON artist.user_id = temp.user_id;

-- Song with the most recent release date
SELECT TOP 1 title, release_date FROM song ORDER BY release_date DESC;

-- Most common plan among users
SELECT TOP 1 P.plan_name, COUNT(user_id) AS num_users 
FROM [user] U
JOIN [plan] P ON U.plan_id = P.plan_id 
GROUP BY P.plan_name
ORDER BY num_users DESC; 

-- Most popular song overall (in the most playlists)
SELECT song.title, temp.num_of_associated_playlists
FROM song INNER JOIN (
    SELECT TOP 1 song_id, COUNT(playlist_id) as num_of_associated_playlists
    FROM playlist_contains
    GROUP BY song_id
    ORDER BY num_of_associated_playlists DESC
) AS temp ON song.song_id = temp.song_id;

-- User with the most playlists
SELECT [user].username, temp.total_playlists
FROM [user] INNER JOIN (
    SELECT TOP 1 user_id, COUNT(playlist_id) AS total_playlists
    FROM playlist
    GROUP BY user_id
    ORDER BY total_playlists DESC
) AS temp ON [user].user_id = temp.user_id;

