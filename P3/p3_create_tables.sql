CREATE DATABASE PLAYLIST_PROJECT_CS4750
GO
Use PLAYLIST_PROJECT_CS4750
GO

CREATE TABLE [plan] (
    plan_id INT PRIMARY KEY IDENTITY(1, 1),
    plan_name NVARCHAR(7) NOT NULL default ('basic'),
    price DECIMAL(5, 2) NOT NULL,
    [description] TEXT, --  check with professor
    CONSTRAINT plan_price_CHK CHECK (price >= 0.00),
    CONSTRAINT plan_name_CHK CHECK (plan_name IN ('basic', 'premium'))
);

CREATE TABLE [user] (
    user_id INT PRIMARY KEY IDENTITY(1, 1),
    plan_id INT NOT NULL DEFAULT 0,
    username NVARCHAR(254) NOT NULL UNIQUE,
    [password] NVARCHAR(254) NOT NULL,
    email NVARCHAR(254) NOT NULL UNIQUE,
    date_joined DATE NOT NULL,
    FOREIGN KEY (plan_id) REFERENCES [plan](plan_id)
);

CREATE TABLE artist (
    user_id INT PRIMARY KEY,
    artist_name NVARCHAR(254) NOT NULL,
    [description] TEXT,
    FOREIGN KEY (user_id) REFERENCES [user](user_id)
);

CREATE TABLE playlist (
    playlist_id INT PRIMARY KEY IDENTITY(1, 1),
    user_id INT NOT NULL,
    playlist_name NVARCHAR(254) NOT NULL,
    creation_date DATE NOT NULL,
    num_of_songs INT DEFAULT 0,
    duration INT DEFAULT 0, -- ms
    [description] TEXT,
    [status] NVARCHAR(50) NOT NULL, -- check this public or private
    FOREIGN KEY (user_id) REFERENCES [user](user_id),
    CONSTRAINT status_CHK CHECK (status IN ('public','private'))
);

CREATE TABLE song (
    song_id INT PRIMARY KEY IDENTITY(1, 1),
    title NVARCHAR(254) NOT NULL,
    duration INT,
    release_date DATE NOT NULL,
    CONSTRAINT song_duration_CHK CHECK (duration > 0)
);

CREATE TABLE record_label (
    label_id INT PRIMARY KEY IDENTITY(1, 1),
    label_name NVARCHAR(254) NOT NULL,
    headquarters NVARCHAR(254),
    founding_year INT
);

CREATE TABLE album (
    album_id INT PRIMARY KEY IDENTITY(1, 1),
    user_id INT NOT NULL,
    album_name NVARCHAR(254) NOT NULL,
    num_of_songs INT NOT NULL,
    duration INT NOT NULL,
    release_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES artist(user_id)
);

CREATE TABLE genre (
    genre_id INT PRIMARY KEY IDENTITY(1, 1),
    genre_name NVARCHAR(254) NOT NULL
);


-- relationships

CREATE TABLE playlist_contains (
    playlist_id INT NOT NULL,
    song_id INT NOT NULL,
    CONSTRAINT playlist_contains_pk PRIMARY KEY (playlist_id, song_id),
    FOREIGN KEY (playlist_id) REFERENCES playlist(playlist_id),
    FOREIGN KEY (song_id) REFERENCES song(song_id)
);

CREATE TABLE artist_signed_with(
    user_id INT NOT NULL,
    label_id INT NOT NULL,
    CONSTRAINT artist_signed_with_pk PRIMARY KEY (user_id, label_id),
    FOREIGN KEY (user_id) REFERENCES artist(user_id),
    FOREIGN KEY (label_id) REFERENCES record_label(label_id)
);

CREATE TABLE artist_performs(
    user_id INT NOT NULL,
    song_id INT NOT NULL,
    CONSTRAINT artist_performs_pk PRIMARY KEY (user_id, song_id),
    FOREIGN KEY (user_id) REFERENCES artist(user_id),
    FOREIGN KEY (song_id) REFERENCES song(song_id)
);

CREATE TABLE album_has(
    album_id INT NOT NULL,
    song_id INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (album_id) REFERENCES album(album_id),
    FOREIGN KEY (song_id) REFERENCES song(song_id)
);

CREATE TABLE song_belongs_to (
    song_id INT NOT NULL,
    genre_id INT NOT NULL,
    CONSTRAINT song_belongs_to_pk PRIMARY KEY (song_id, genre_id),
    FOREIGN KEY (song_id) REFERENCES song(song_id),
    FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);
