USE Hangman;

CREATE TABLE IF NOT EXISTS Hangman_lobbies (
    lobbyId VARCHAR(255) PRIMARY KEY,
    secretWord VARCHAR(255)
);


GRANT SELECT, INSERT, UPDATE, DELETE ON Hangman.Hangman_lobbies TO 'HangmanDefault'@'%';
FLUSH PRIVILEGES;