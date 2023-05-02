
-- If the database already exists, just delete it.--------------------------------
DROP DATABASE wordy;

-- Create the database. ----------------------------------------------------------
CREATE DATABASE wordy;
USE wordy;


-- Create the player table--------------------------------------------------------
CREATE TABLE player(
                       player_id INT AUTO_INCREMENT,
                       username VARCHAR(30),
                       password VARCHAR(30),
                       status VARCHAR(30) NOT NULL,
                       num_wins INT DEFAULT 0,
                       game_id INT DEFAULT NULL,
                       PRIMARY KEY(player_id));

-- Create the game table----------------------------------------------------------
CREATE TABLE game(
                     game_id INT AUTO_INCREMENT,
                     game_status VARCHAR(30) NOT NULL,
                     num_rounds INT,
                     PRIMARY KEY(game_id));

-- Create the player_status table-------------------------------------------------
CREATE TABLE player_status(
                              status_id INT AUTO_INCREMENT,
                              status VARCHAR(30),
                              PRIMARY KEY(status_id));

-- Create the game_status table---------------------------------------------------
CREATE TABLE game_status(
                            status_id INT AUTO_INCREMENT,
                            status VARCHAR(30) NOT NULL,
                            PRIMARY KEY(status_id));

-- Create the longest_word table--------------------------------------------------
CREATE TABLE longest_word(
                             word_id INT AUTO_INCREMENT,
                             word VARCHAR(30),
                             PRIMARY KEY(word_id));

-- Add the foreign keys for each table--------------------------------------------
ALTER TABLE player ADD FOREIGN KEY(status)
    REFERENCES player_status(status_id)
    ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE player ADD FOREIGN KEY(game_id)
    REFERENCES game(game_id)
    ON DELETE SET NULL ON UPDATE RESTRICT;

ALTER TABLE game ADD FOREIGN KEY(game_status)
    REFERENCES game_status(status_id)
    ON DELETE RESTRICT ON UPDATE CASCADE;

-- Start filling up data for some of the tables-----------------------------------
INSERT INTO player_status(status) VALUES('Online');
INSERT INTO player_status(status) VALUES('Offline');

INSERT INTO game_status(status) VALUES('Pending Players');
INSERT INTO game_status(status) VALUES('Ongoing');
INSERT INTO game_status(status) VALUES('Finished');


INSERT INTO player(username, password, status) VALUES(
                                                         'root1',
                                                         'rootPassword1',
                                                         (SELECT status_id FROM player_status
                                                          WHERE status = 'OFFLINE'));

INSERT INTO player(username, password, status) VALUES(
                                                         'root2',
                                                         'rootPassword2',
                                                         (SELECT status_id FROM player_status
                                                          WHERE status = 'OFFLINE'));


INSERT INTO player(username, password, status) VALUES(
                                                         'root3',
                                                         'rootPassword3',
                                                         (SELECT status_id FROM player_status
                                                          WHERE status = 'OFFLINE'));

INSERT INTO player(username, password, status) VALUES(
                                                         'root4',
                                                         'rootPassword4',
                                                         (SELECT status_id FROM player_status
                                                          WHERE status = 'OFFLINE'));

INSERT INTO player(username, password, status) VALUES(
                                                         'root5',
                                                         'rootPassword5',
                                                         (SELECT status_id FROM player_status
                                                          WHERE status = 'OFFLINE'));

-- Create any necessary views for easy access-------------------------------------
CREATE VIEW best_player AS
SELECT player.username AS 'Player', player.num_wins AS 'Wins' FROM player
ORDER BY 'Wins' DESC;
