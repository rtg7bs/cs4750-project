CREATE NONCLUSTERED INDEX IX_user_username ON
schema.user(username);

CREATE NONCLUSTERED INDEX IX_album_album_name ON
schema.album(album_name);

CREATE NONCLUSTERED INDEX IX_song_song_name ON
schema.song(song_name);