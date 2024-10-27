-- create playlist (all are empty at creation)
CREATE PROCEDURE CreatePlaylist
    -- playlist_id is identity
    -- creation_date is generated at runtime
    -- num_of_songs and duration are DEFAULT (0)
    @user_id INT NOT NULL,
    @playlist_name NVARCHAR(254),
    @description TEXT,
    @status NVARCHAR(50), 
AS
	SET NOCOUNT ON;
BEGIN
	INSERT INTO playlist (user_id, playlist_name, creation_date, [description], [status])
    VALUES (@user_id, @playlist_name, GETDATE(), @description, @status);
END;


-- add song to playlist
CREATE PROCEDURE AddSongToPlaylist
	@song_id INT
    @playlist_id INT
AS
	SET NOCOUNT ON;
BEGIN
	INSERT INTO playlist_contains
    VALUES (@song_id, @playlist_id);
END;


-- remove song from playlist
CREATE PROCEDURE RemoveSongFromPlaylist
	@song_id INT
    @playlist_id INT
AS
	SET NOCOUNT ON;
BEGIN
	DELETE FROM playlist_contains
    WHERE song_id = @song_id AND playlist_id = @playlist_id;
END;