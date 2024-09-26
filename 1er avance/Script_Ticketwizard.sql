CREATE DATABASE ticketwizard;

use ticketwizard;

CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255) NOT NULL,
    apellidoPaterno VARCHAR (255) NOT NULL,
    apellidoMaterno VARCHAR (255),
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    calle VARCHAR(255),
    numero_exterior VARCHAR(10),
    colonia VARCHAR(100),
    edad INT,
    fecha_nacimiento DATE,
    saldo DECIMAL(10, 2) DEFAULT 0.00,
    administrador BOOLEAN NOT NULL
)AUTO_INCREMENT = 1;

INSERT INTO Usuarios(nombres, apellidoPaterno, correo, contrasena, administrador)VALUES
('Administrador', '1', 'administrador@gmail.com', '$2a$12$LkPgVwFRDDyIpjz/9wHwBOvt5Nh4eArowAifZRslIg4dg5s6cd21.', true);

CREATE TABLE Eventos (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    localidad VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    venue VARCHAR(255) NOT NULL
)AUTO_INCREMENT = 1;


CREATE TABLE Transacciones (
    num_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(10, 2) NOT NULL,
    comision DECIMAL (10,2),
    tiempo_limite TIME,
    tipo ENUM ('saldo','compra','venta','comision'),
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
)AUTO_INCREMENT = 1;

CREATE TABLE Boletos (
    id_boleto INT AUTO_INCREMENT PRIMARY KEY,
    num_serie CHAR(8) UNIQUE,
    fila VARCHAR(10),
    asiento VARCHAR(10),
    precio DECIMAL(10, 2) NOT NULL,
    estado_adquisicion ENUM('reventa', 'directo') NOT NULL,
    id_usuario INT, 
    id_evento INT, 
    en_venta BOOLEAN NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_evento) REFERENCES Eventos(id_evento)
)AUTO_INCREMENT = 1;

CREATE TABLE Detalles_BoletoTransaccion (
id_boleto INT,
num_transaccion INT,
estado ENUM ('Completado','Pendiente','Cancelado'),
FOREIGN KEY (id_boleto) REFERENCES Boletos(id_boleto),
FOREIGN KEY (num_transaccion) REFERENCES Transacciones(num_transaccion)
);

DELIMITER //

CREATE TRIGGER actualizar_usuario_saldo
AFTER INSERT ON Transacciones
FOR EACH ROW
BEGIN
    IF NEW.tipo = 'compra' THEN
        -- Si es una compra, restar el monto del saldo
        UPDATE Usuarios
        SET saldo = saldo - NEW.monto
        WHERE id_usuario = NEW.id_usuario;
	ELSEIF NEW.tipo = 'comision' THEN
        -- Si es una comision, restar el monto del saldo
        UPDATE Usuarios
        SET saldo = saldo - NEW.monto
        WHERE id_usuario = NEW.id_usuario;
    ELSEIF NEW.tipo = 'saldo' THEN
        -- Si es saldo, aumentar el monto al saldo
        UPDATE Usuarios
        SET saldo = saldo + NEW.monto
        WHERE id_usuario = NEW.id_usuario;
	ELSEIF NEW.tipo = 'venta' THEN
        -- Si es saldo, aumentar el monto al saldo
        UPDATE Usuarios
        SET saldo = saldo + NEW.monto
        WHERE id_usuario = NEW.id_usuario;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ComprarBoleto (
    IN p_id_boleto INT,
    IN p_num_serie CHAR(8),
    IN p_precio DECIMAL(10, 2),
    IN p_estado_adquisicion ENUM('reventa', 'directo'),
    IN p_tipo ENUM('saldo', 'compra'),
    IN p_id_usuario INT
)
BEGIN
    DECLARE v_num_transaccion INT;
    
    -- Construir el número de transacción usando id_boleto, id_usuario y la fecha actual (solo año, mes y día)
    SET v_num_transaccion = (SELECT COALESCE(MAX(num_transaccion), 0) + 1 FROM Transacciones);

    -- Actualizar el boleto existente
    UPDATE Boletos
    SET num_serie = p_num_serie,
        estado_adquisicion = p_estado_adquisicion,
        id_usuario = p_id_usuario
    WHERE id_boleto = p_id_boleto;

    -- Insertar la transacción en la tabla Transacciones
    INSERT INTO Transacciones (num_transaccion, monto, tipo, id_usuario)
    VALUES (v_num_transaccion, p_precio, p_tipo, p_id_usuario);

    -- Insertar el detalle de la transacción en Detalles_BoletoTransaccion
    INSERT INTO Detalles_BoletoTransaccion (id_boleto, num_transaccion, estado)
    VALUES (p_id_boleto, v_num_transaccion, 'Completado');
END//


DELIMITER ;