USE PLAYLIST_PROJECT_CS4750
GO

-- create a new playlist (all are empty at creation)
CREATE PROCEDURE CreatePlaylist
    -- note: playlist_id is auto-incremented, creation_date is generated at runtime, and num_of_songs and duration are DEFAULT (0)
    @user_id INT,
    @playlist_name NVARCHAR(254),
    @description TEXT,
    @status NVARCHAR(50)
AS
	SET NOCOUNT ON;
BEGIN
	INSERT INTO playlist (user_id, playlist_name, creation_date, [description], [status])
    VALUES (@user_id, @playlist_name, GETDATE(), @description, @status);
END;
GO

-- add a song to a playlist
CREATE PROCEDURE AddSongToPlaylist
	@song_id INT,
    @playlist_id INT
AS
	SET NOCOUNT ON;
BEGIN
    -- check if the song or playlist exists in the db
    IF NOT EXISTS (
        SELECT 1
        FROM song
        WHERE song_id = @song_id
    ) OR NOT EXISTS (
        SELECT 1 
        FROM playlist
        WHERE playlist_id = @playlist_id
    ) BEGIN
        RAISERROR('Song id or playlist id does not exist in the database', 16, 1);
        RETURN;
    END;

    -- check if the song is in the playlist already
    IF EXISTS (
        SELECT 1
        FROM playlist_contains
        WHERE playlist_id = @playlist_id AND song_id = @song_id
    ) BEGIN
        RAISERROR('Song already exists in the playlist', 16, 1);
        RETURN;
    END; 

    -- add the song to the playlist
    INSERT INTO playlist_contains (playlist_id, song_id)
    VALUES (@playlist_id, @song_id);
END;
GO

-- remove a song from a playlist
CREATE PROCEDURE RemoveSongFromPlaylist
	@song_id INT,
    @playlist_id INT
AS
	SET NOCOUNT ON;
BEGIN
    -- check if the playlist contains the song 
    IF NOT EXISTS (
        SELECT 1 
        FROM playlist_contains
        WHERE playlist_id = @playlist_id AND song_id = @song_id
    ) BEGIN
        RAISERROR('Playlist does not contain the specified song', 16, 1);
        RETURN;
    END;

    -- delete the song from the playlist
	DELETE FROM playlist_contains
    WHERE song_id = @song_id AND playlist_id = @playlist_id;
END;
GO
