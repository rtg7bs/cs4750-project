CREATE NONCLUSTERED INDEX IX_user_user_id ON
schema.user(user_id);

CREATE NONCLUSTERED INDEX IX_album_album_id ON
schema.album(album_id);

CREATE NONCLUSTERED INDEX IX_playlist_contains_song_id ON
schema.playlist_contains(song_id);