
CREATE TABLE PlaylistErrors (
ErrorID INT IDENTITY(1,1) PRIMARY KEY,
playlist_id INT,
song_id INT,
ErrorMessage VARCHAR(255),
ErrorDate DATETIME DEFAULT GETDATE()
);
GO


CREATE TRIGGER trg_InsteadOfInsertPlaylist
ON playlist_contains
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @song_id INT, @playlist_id INT;

    -- extract values from the inserted row
    SELECT @song_id = song_id, @playlist_id = playlist_id
    FROM inserted; -- quesitons

    -- check if the playlist exists
    IF NOT EXISTS (
        SELECT 1 FROM playlist WHERE playlist_id = @playlist_id
    )
    BEGIN
        -- log the error into the ErrorLog table
        INSERT INTO PlaylistErrors (playlist_id, song_id, ErrorMessage, ErrorDate)
        VALUES (NULL, @song_id, 'Invalid PlaylistID: Playlist does not exist.', GETDATE());
    END
    ELSE
    BEGIN
        -- if the playlist exists, insert the song into the playlist_contains table
        INSERT INTO playlist_contains (playlist_id, song_id)
        VALUES (@playlist_id, @song_id);
    END
END;
GO
