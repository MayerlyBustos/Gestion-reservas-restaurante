-- Creacion Base de Datos
CREATE DATABASE RISERVI;


-- Creación Tablas
CREATE TABLE customers(
	customer_id INT AUTO_INCREMENT NOT NULL,
	name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(250) NOT NULL,
	number_phone VARCHAR(20) NOT NULL,
	CONSTRAINT pk_customers PRIMARY KEY (customer_id)	
	
);


CREATE TABLE schedules(
	schedule_id INT AUTO_INCREMENT NOT NULL,
	date DATE NOT NULL,
	hour TIME NOT NULL,
	available INT DEFAULT 1 NOT NULL,
	CONSTRAINT pk_schedules PRIMARY KEY (schedule_id)
);


CREATE TABLE reservations(
	reservation_id INT AUTO_INCREMENT NOT NULL,
	customer_id INT NOT NULL,
	schedule_id INT NOT NULL,
	reservation_date TIMESTAMP NOT null,
	CONSTRAINT pk_reservations PRIMARY KEY (reservation_id),
	CONSTRAINT fk_reservations_customers FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
	CONSTRAINT fk_reservations_schedules FOREIGN KEY (schedule_id) REFERENCES schedules(schedule_id),
	CONSTRAINT only_reservation UNIQUE (schedule_id)
	
);


-- Inserción de datos en las tablas
INSERT INTO customers (name, last_name, email, number_phone)
VALUES
    ('Mayerly', 'Bustos', 'may.bustos@example.com', '3002748254'),
    ('Keren', 'Riveros', 'keren.riveros@example.com', '3202587895'),
    ('Antonella', 'Robayo', 'anto.robayo@example.com', '3553456789'),
	('Arles', 'Bustos', 'ar.bustos@example.com', '325698787'),
    ('Miriam', 'Robayo', 'miriam.r@example.com', '3117894516'),
    ('Maria', 'Parra', 'mparra.78@example.com', '3204567849');


INSERT INTO schedules (date, hour, available)
VALUES
    ('2025-01-09', '09:00:00', 0),
	('2025-01-09', '10:00:00', 0),
    ('2025-01-10', '10:00:00', 0),
	('2025-01-10', '11:00:00', 0),
    ('2025-01-11', '11:00:00', 0),
    ('2025-01-12', '09:00:00', 0),
    ('2025-01-13', '10:00:00', 1),
    ('2025-01-13', '11:00:00', 1);


INSERT INTO reservations (customer_id, schedule_id, reservation_date)
VALUES
    (1, 1, '2025-01-06 09:00:00'),
    (2, 2, '2025-01-06 10:00:00'),
    (3, 3, '2025-01-07 11:00:00'),
	(4, 4, '2025-01-07 09:00:00'),
    (1, 5, '2025-01-08 10:00:00'),
    (3, 6, '2025-01-08 11:00:00');
  

-- 1. Verificar los horarios disponibles para un día específico.
select * FROM schedules WHERE date = '2025-01-13' and available = 1;

-- 2. Crear una nueva reservación para un cliente. 
-- se debe verificar que el horario este disponible para poder realizar la reservacion, eso se hace con el punto(1) anterior;
INSERT INTO reservations (customer_id, schedule_id, reservation_date)
values (4, 7, now());

-- Después de hacer la insercion de la nueva reserva,
-- se procede a actualizar la columna Available del
-- schedule_id = 7 a 0, para que no se puedan realizar más reservas en ese horario.
UPDATE schedules
SET available = 0
WHERE schedule_id = 7;


-- 3. Actualizar una reservación existente. Se debe verificar que el horario este disponible. 
UPDATE reservations
SET schedule_id = 8, reservation_date = now()
WHERE reservation_id = 7
AND EXISTS (
	SELECT 1 
	FROM schedules WHERE schedule_id = 8
	AND available = 1
);

-- Después de hacer la actualización de la reserva,
-- se procede a actualizar la columna Available del
-- schedule_id = 8 a 0, para que no se puedan realizar más reservas
-- en ese horario.

UPDATE schedules
SET available = 0
WHERE schedule_id = 8;


-- Paso siguiente, se actualiza la columna Available del
-- scchdule_id = 7 a 1, para que quede disponible para otra reservación.
UPDATE schedules
SET available = 1
WHERE schedule_id = 7;

-- 4. Cancelar (eliminar) una reservación. Se verifica que exista el registro 
-- de la reserva para luego eliminarlo.
SELECT * FROM reservations WHERE reservation_id = 5
DELETE FROM reservations WHERE reservation_id = 5

-- Despues de eliminar la reservación, actualizamos la columna
-- Available de la tabla schedulers para que quede disponible para otra reserva.
UPDATE schedules
SET available = 1
WHERE schedule_id = 5;


select * from customers
select * from schedules
select * from reservations









