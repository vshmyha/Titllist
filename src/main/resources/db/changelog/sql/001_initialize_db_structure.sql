CREATE TABLE animes
(
    id           INTEGER NOT NULL AUTO_INCREMENT,
    duration     INTEGER,
    episodes     INTEGER,
    jap_name     VARCHAR(255),
    release_date SMALLINT,
    rus_name     VARCHAR(255),
    type         VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;
CREATE TABLE genres
(
    id   INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;
CREATE TABLE genres_to_animes
(
    id_anime INTEGER NOT NULL,
    id_genre INTEGER NOT NULL
) ENGINE = InnoDB;
CREATE TABLE roles
(
    id   INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;
CREATE TABLE titllist_notes
(
    status   VARCHAR(255),
    user_id  INTEGER NOT NULL,
    anime_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, anime_id)
) ENGINE = InnoDB;
CREATE TABLE types
(
    id   INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;
CREATE TABLE users
(
    id       INTEGER NOT NULL AUTO_INCREMENT,
    password VARCHAR(255),
    username VARCHAR(255),
    role_id  INTEGER,
    PRIMARY KEY (id)
) ENGINE = InnoDB;
ALTER TABLE genres_to_animes
    ADD CONSTRAINT animes_to_genres_fk FOREIGN KEY (id_genre) REFERENCES genres (id);
ALTER TABLE genres_to_animes
    ADD CONSTRAINT genres_to_animes_fk FOREIGN KEY (id_anime) REFERENCES animes (id);
ALTER TABLE titllist_notes
    ADD CONSTRAINT titllist_notes_to_users_fk FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE titllist_notes
    ADD CONSTRAINT titllist_notes_to_animes_fk FOREIGN KEY (anime_id) REFERENCES animes (id);
ALTER TABLE users
    ADD CONSTRAINT users_to_role_fk FOREIGN KEY (role_id) REFERENCES roles (id);