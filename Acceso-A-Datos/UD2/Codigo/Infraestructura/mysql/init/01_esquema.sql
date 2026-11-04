USE ad_ud2;

CREATE TABLE IF NOT EXISTS club (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(90) NOT NULL UNIQUE,
  ciudad VARCHAR(90) NOT NULL,
  presupuesto DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT chk_presupuesto CHECK (presupuesto >= 0)
);

CREATE TABLE IF NOT EXISTS jugador (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(90) NOT NULL,
  dorsal INT NOT NULL,
  club_id INT NOT NULL,
  CONSTRAINT fk_jugador_club FOREIGN KEY (club_id)
    REFERENCES club(id) ON DELETE RESTRICT,
  CONSTRAINT uq_dorsal_club UNIQUE (club_id, dorsal)
);

CREATE TABLE IF NOT EXISTS movimiento (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  club_origen_id INT NOT NULL,
  club_destino_id INT NOT NULL,
  importe DECIMAL(10,2) NOT NULL,
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT chk_importe CHECK (importe > 0),
  CONSTRAINT fk_mov_origen FOREIGN KEY (club_origen_id) REFERENCES club(id),
  CONSTRAINT fk_mov_destino FOREIGN KEY (club_destino_id) REFERENCES club(id)
);

INSERT INTO club (id, nombre, ciudad, presupuesto) VALUES
  (1, 'Granada CF', 'Granada', 2500.00),
  (2, 'Málaga CF', 'Málaga', 1500.00),
  (3, 'Cádiz CF', 'Cádiz', 1000.00)
ON DUPLICATE KEY UPDATE id = id;

INSERT INTO jugador (id, nombre, dorsal, club_id) VALUES
  (1, 'Lucía', 10, 1),
  (2, 'Pablo', 7, 1),
  (3, 'Sara', 9, 2)
ON DUPLICATE KEY UPDATE id = id;

DROP PROCEDURE IF EXISTS contar_jugadores;
DELIMITER //
CREATE PROCEDURE contar_jugadores(IN p_club_id INT, OUT p_total INT)
BEGIN
  SELECT COUNT(*) INTO p_total
  FROM jugador WHERE club_id = p_club_id;
END //
DELIMITER ;
