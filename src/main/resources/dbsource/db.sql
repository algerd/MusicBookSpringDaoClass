CREATE TABLE "artist" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name"  TEXT(255) NOT NULL,
    "rating"  INTEGER,
    "description"  TEXT
);

CREATE TABLE "album" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_artist"  INTEGER NOT NULL DEFAULT 1,
    "name"  TEXT(255) NOT NULL,
    "year"  INTEGER NOT NULL DEFAULT 0,
    "time"  TEXT,
    "rating"  INTEGER NOT NULL DEFAULT 0,
    "description"  TEXT,
    CONSTRAINT "fkey0" FOREIGN KEY ("id_artist") REFERENCES "artist" ("id") ON DELETE CASCADE
);

CREATE TABLE "song" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_album"  INTEGER DEFAULT 1,
    "name"  TEXT(255),
    "track"  INTEGER NOT NULL DEFAULT 0,
    "lyric"  TEXT,
    "time"  TEXT,
    "rating"  INTEGER NOT NULL DEFAULT 0,
    "description"  TEXT,
    CONSTRAINT "fkey0" FOREIGN KEY ("id_album") REFERENCES "album" ("id") ON DELETE CASCADE
);

CREATE TABLE "genre" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name"  TEXT(255) NOT NULL,
    "description"  TEXT,
    CONSTRAINT "name" UNIQUE ("name")
);

CREATE TABLE "instrument" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name"  TEXT(255) NOT NULL,
    "description"  BLOB(10000) NOT NULL
);

CREATE TABLE "musician" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "name"  TEXT(255),
    "date_of_birth"  TEXT,
    "date_of_death"  TEXT,
    "country"  TEXT(255),
    "description"  TEXT,
    "rating"  INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE "artist_reference" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_artist"  INTEGER NOT NULL,
    "name"  TEXT(255),
    "reference"  TEXT(255),
    FOREIGN KEY ("id_artist") REFERENCES "artist" ("id") ON DELETE CASCADE
);

CREATE TABLE "artist_genre" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_genre"  INTEGER NOT NULL DEFAULT 1,
    "id_artist"  INTEGER NOT NULL DEFAULT 1,
    CONSTRAINT "fkey0" FOREIGN KEY ("id_genre") REFERENCES "genre" ("id") ON DELETE CASCADE,
    CONSTRAINT "fkey1" FOREIGN KEY ("id_artist") REFERENCES "artist" ("id") ON DELETE CASCADE
);

CREATE TABLE "album_genre" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_genre"  INTEGER NOT NULL DEFAULT 1,
    "id_album"  INTEGER NOT NULL DEFAULT 1,
    CONSTRAINT "fkey0" FOREIGN KEY ("id_genre") REFERENCES "genre" ("id") ON DELETE CASCADE,
    CONSTRAINT "fkey1" FOREIGN KEY ("id_album") REFERENCES "album" ("id") ON DELETE CASCADE
);

CREATE TABLE "song_genre" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_genre"  INTEGER NOT NULL DEFAULT 1,
    "id_song"  INTEGER NOT NULL DEFAULT 1,
    CONSTRAINT "fkey0" FOREIGN KEY ("id_song") REFERENCES "song" ("id") ON DELETE CASCADE,
    CONSTRAINT "fkey1" FOREIGN KEY ("id_genre") REFERENCES "genre" ("id") ON DELETE CASCADE
);

CREATE TABLE "musician_genre" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_genre"  INTEGER NOT NULL DEFAULT 1,
    "id_musician"  INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY ("id_genre") REFERENCES "genre" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_musician") REFERENCES "musician" ("id") ON DELETE CASCADE
);

CREATE TABLE "musician_group" (
    "id"  INTEGER NOT NULL,
    "id_musician"  INTEGER NOT NULL,
    "id_artist"  INTEGER NOT NULL,
    "start_date"  TEXT,
    "end_date"  TEXT,
    PRIMARY KEY ("id" ASC),
    FOREIGN KEY ("id_musician") REFERENCES "musician" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_artist") REFERENCES "artist" ("id") ON DELETE CASCADE
);

CREATE TABLE "musician_album" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_musician"  INTEGER NOT NULL DEFAULT 1,
    "id_album"  INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY ("id_musician") REFERENCES "musician" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_album") REFERENCES "album" ("id") ON DELETE CASCADE
);

CREATE TABLE "musician_instrument" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_instrument"  INTEGER NOT NULL DEFAULT 1,
    "id_musician"  INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY ("id_instrument") REFERENCES "instrument" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_musician") REFERENCES "musician" ("id") ON DELETE CASCADE
);

CREATE TABLE "musician_song" (
    "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "id_musician"  INTEGER NOT NULL DEFAULT 1,
    "id_song"  INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY ("id_musician") REFERENCES "musician" ("id") ON DELETE CASCADE,
    FOREIGN KEY ("id_song") REFERENCES "song" ("id") ON DELETE CASCADE
);

