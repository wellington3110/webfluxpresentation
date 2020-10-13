CREATE TABLE league
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE team
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(255) NOT NULL,
    league_id INT          NOT NULL,
    CONSTRAINT fk_league FOREIGN KEY (league_id) REFERENCES league (id)
);