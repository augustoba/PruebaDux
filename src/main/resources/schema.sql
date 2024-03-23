CREATE TABLE IF NOT EXISTS LIGA (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    nombre VARCHAR(100) NOT NULL
    );
INSERT INTO LIGA (nombre) VALUES
                                  ('La Liga'),
                                  ('Premier League'),
                                  ('Serie A'),
                                  ('Bundesliga'),
                                  ('Ligue 1'),
                                  ('Primeira Liga'),
                                  ('Eredivisie'),
                                  ('Scottish Premiership'),
                                  ('Süper Lig'),
                                  ('Premier League Rusa');

CREATE TABLE IF NOT EXISTS PAIS (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    nombre VARCHAR(100) NOT NULL
    );

INSERT INTO PAIS (nombre) VALUES
                                  ('España'),
                                  ('Inglaterra'),
                                  ('Italia'),
                                  ('Alemania'),
                                  ('Francia'),
                                  ('Portugal'),
                                  ('Países Bajos'),
                                  ('Escocia'),
                                  ('Turquía'),
                                  ('Rusia');

CREATE TABLE IF NOT EXISTS Equipo (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      nombre VARCHAR(100) NOT NULL,
    liga_id INT,
    pais_id INT,
    FOREIGN KEY (liga_id) REFERENCES LIGA(id),
    FOREIGN KEY (pais_id) REFERENCES PAIS(id)
    );

INSERT INTO Equipo (nombre, liga_id, pais_id) VALUES
                                                      ('Real Madrid', 1, 1),
                                                      ('FC Barcelona', 1, 1),
                                                      ('Manchester United', 2, 2),
                                                      ('Liverpool FC', 2, 2),
                                                      ('Juventus FC', 3, 3),
                                                      ('AC Milan', 3, 3),
                                                      ('Bayern Munich', 4, 4),
                                                      ('Borussia Dortmund', 4, 4),
                                                      ('Paris Saint-Germain', 5, 5),
                                                      ('Olympique de Marseille', 5, 5),
                                                      ('FC Porto', 6, 6),
                                                      ('Sporting CP', 6, 6),
                                                      ('Ajax Amsterdam', 7, 7),
                                                      ('Feyenoord', 7, 7),
                                                      ('Celtic FC', 8, 8),
                                                      ('Rangers FC', 8, 8),
                                                      ('Galatasaray SK', 9, 9),
                                                      ('Fenerbahçe SK', 9, 9),
                                                      ('FC Zenit Saint Petersburg', 10, 10),
                                                      ('Spartak Moscow', 10, 10),
                                                      ('SL Benfica', 6, 6),
                                                      ('Besiktas JK', 9, 9),
                                                      ('SSC Napoli', 3, 3),
                                                      ('Atlético Madrid', 1, 1);

DROP TABLE IF EXISTS USUARIO;

CREATE TABLE USUARIO (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(100) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         role VARCHAR(50)
);
INSERT INTO USUARIO (username, password,role)
VALUES('augusto','2a$10$wWlPSYC2pzSY4Jc.yDLT6OwxUEY69ax4IH2eVO5u16LRQ0aSWox3m','ADMINISTRADOR'),
      ('test','$2a$10$e.VyoXQECNyc46761HhRAO31asO6Mh5hjifoTrsr/UJDP8.ncMWyG','ADMINISTRADOR')