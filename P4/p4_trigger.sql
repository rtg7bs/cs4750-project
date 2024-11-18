USE PLAYLIST_PROJECT_CS4750
GO

CREATE TABLE PlaylistErrors (
    ErrorID INT IDENTITY(1,1) PRIMARY KEY,
    playlist_id INT,
    song_id INT,
    ErrorMessage VARCHAR(255),
    ErrorDate DATETIME DEFAULT GETDATE()
);
GO

CREATE TRIGGER trg_InsteadOfInsertPlaylistContains
ON playlist_contains
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @song_id INT, @playlist_id INT;

    -- extract values from the inserted row
    SELECT @song_id = song_id, @playlist_id = playlist_id
    FROM inserted; 

    -- check if the playlist exists in the db
    IF NOT EXISTS (
        SELECT 1 
        FROM playlist 
        WHERE playlist_id = @playlist_id
    )
    BEGIN
        -- log the error into the PlaylistErrors table
        INSERT INTO PlaylistErrors (playlist_id, song_id, ErrorMessage, ErrorDate)
        VALUES (@playlist_id, @song_id, 'Invalid PlaylistID: Playlist does not exist.', GETDATE());
    END

    -- check if the song exists in the db
    ELSE IF NOT EXISTS (
        SELECT 1 
        FROM song 
        WHERE song_id = @song_id
    ) 
    BEGIN
        -- log the error into the PlaylistErrors table
        INSERT INTO PlaylistErrors (playlist_id, song_id, ErrorMessage, ErrorDate)
        VALUES (@playlist_id, @song_id, 'Invalid SongID: Song does not exist.', GETDATE());    
    END

    -- check if song is already in the playlist
    ELSE IF EXISTS (
        SELECT 1 
        FROM playlist_contains 
        WHERE playlist_id = @playlist_id AND song_id = @song_id
    )
    BEGIN
        -- log the error into the PlaylistErrors table
        INSERT INTO PlaylistErrors (playlist_id, song_id, ErrorMessage, ErrorDate)
        VALUES (@playlist_id, @song_id, 'Duplicate SongID: Song already exists in the playlist', GETDATE());    
    END
 
    ELSE
    BEGIN
        -- insert the song into the playlist in the playlist_contains table
        INSERT INTO playlist_contains (playlist_id, song_id)
        VALUES (@playlist_id, @song_id);
    END
END;
GO
