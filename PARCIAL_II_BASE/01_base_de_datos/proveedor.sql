-- =====================================================================
-- UNIVERSIDAD MARIANO GALVEZ DE GUATEMALA - FACULTAD DE INGENIERIA
-- Programacion II - EXAMEN PARCIAL II
-- Script de base de datos (ENTREGADO POR EL CATEDRATICO - NO MODIFICAR)
-- =====================================================================

DROP DATABASE IF EXISTS parcial2;
CREATE DATABASE parcial2 CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE parcial2;

CREATE TABLE proveedor (
    ID_PROVEEDOR INT          NOT NULL AUTO_INCREMENT,
    ESTADO       BOOLEAN      NOT NULL DEFAULT TRUE,
    NOMBRE       VARCHAR(65)  NOT NULL,
    NIT          VARCHAR(20)  NULL,
    TELEFONO     VARCHAR(15)  NULL,
    DIRECCION    VARCHAR(100) NULL,
    PRIMARY KEY (ID_PROVEEDOR)
);

-- Datos de prueba
INSERT INTO proveedor (ESTADO, NOMBRE, NIT, TELEFONO, DIRECCION) VALUES
(TRUE, 'Distribuidora Huehue',     '1234567-8', '77641234', '5a calle 3-20 zona 1, Huehuetenango'),
(TRUE, 'Abarrotes El Progreso',    '2345678-9', '77645678', '2a avenida 10-15 zona 3, Huehuetenango'),
(TRUE, 'Comercial Los Cuchumatanes','3456789-0','77649012', 'Calzada Kaibil Balam 7-40, Huehuetenango'),
(TRUE, 'Lacteos San Pedro',        '4567890-1', '77643456', 'Aldea San Pedro Necta, Huehuetenango'),
(FALSE,'Proveedora Anulada S.A.',  '5678901-2', '77647890', '1a calle 1-01 zona 2, Huehuetenango');

SELECT * FROM proveedor;
