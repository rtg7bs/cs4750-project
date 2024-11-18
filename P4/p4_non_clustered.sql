USE PLAYLIST_PROJECT_CS4750
GO

CREATE NONCLUSTERED INDEX IX_user_username ON
[user](username);

CREATE NONCLUSTERED INDEX IX_album_album_name ON
album(album_name);

CREATE NONCLUSTERED INDEX IX_song_song_name ON
song(title);
