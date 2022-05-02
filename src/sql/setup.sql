DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS empleado CASCADE;
DROP TABLE IF EXISTS beneficiario CASCADE;
DROP TABLE IF EXISTS persona CASCADE;
DROP TABLE IF EXISTS cargo CASCADE;
DROP TABLE IF EXISTS gobernacion CASCADE;
DROP TABLE IF EXISTS fundacion CASCADE;
DROP TABLE IF EXISTS rol CASCADE;
DROP TABLE IF EXISTS permiso CASCADE;
DROP TABLE IF EXISTS rol_permiso CASCADE;
DROP TABLE IF EXISTS servicio CASCADE;
DROP TABLE IF EXISTS tipo_servicio CASCADE;
DROP TABLE IF EXISTS fundacion_servicio CASCADE;
DROP TABLE IF EXISTS prioridad CASCADE;
DROP TABLE IF EXISTS solicitud_status CASCADE;
DROP TABLE IF EXISTS solicitud CASCADE;
DROP TABLE IF EXISTS detalle_solicitud_servicio CASCADE;
DROP TABLE IF EXISTS presupuesto;
DROP TABLE IF EXISTS charla CASCADE;
DROP TABLE IF EXISTS asistenciaCharla CASCADE;
DROP TABLE IF EXISTS solicitud_presupuesto CASCADE;
DROP TABLE IF EXISTS log_partidas_asignadas CASCADE;
DROP TABLE IF EXISTS asistencia_charla CASCADE;
--VIEW
DROP VIEW IF EXISTS partida_total_actual;
DROP VIEW IF EXISTS view_presupuestos_totales_solicitud;
DROP VIEW IF EXISTS view_total_gastado_anual;
--ENUM
DROP TYPE IF EXISTS tipo_servicio;
DROP TYPE IF EXISTS solicitud_prioridad;
DROP TYPE IF EXISTS solicitud_status;
CREATE TYPE tipo_servicio AS ENUM ('medico','otros');
CREATE TYPE solicitud_prioridad AS ENUM ('baja', 'media', 'alta');
CREATE TYPE solicitud_status AS ENUM ('aprobado','rechazado','pendiente');


--BUSINESS LOGIC TABLES
CREATE TABLE IF NOT EXISTS gobernacion(
	id VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(80) NOT NULL,
	fondos FLOAT NOT NULL DEFAULT 0,
	deleted_at DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS fundacion(
	id VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(80) NOT NULL,
	presupuesto FLOAT NOT NULL DEFAULT 0,
	porcentaje_partido_anual FLOAT NOT NULL DEFAULT 0,
	gobernacion_id VARCHAR(40) NOT NULL, --FOREIGN KEY
	deleted_at DATE DEFAULT NULL,
	CONSTRAINT fk_gobernacion FOREIGN KEY(gobernacion_id) REFERENCES gobernacion(id) --FOREIGN KEY
);

CREATE TABLE IF NOT EXISTS log_partidas_asignadas(
	id VARCHAR(40) PRIMARY KEY,
	fundacion_id VARCHAR(40) NOT NULL,
	partida_asignada FLOAT NOT NULL,
	annio INT NOT NULL,
	deleted_at DATE DEFAULT NULL,
	CONSTRAINT fk_fundacion FOREIGN KEY(fundacion_id) REFERENCES fundacion(id)
);


CREATE TABLE IF NOT EXISTS servicio(
	id VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(80) NOT NULL,
	tipo tipo_servicio NOT NULL,
	deleted_at DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS fundacion_servicio(
	fundacion_id VARCHAR(40) NOT NULL,
	servicio_id VARCHAR(40) NOT NULL,
	costo FLOAT NOT NULL DEFAULT 0,
	deleted_at DATE DEFAULT NULL,
	CONSTRAINT fk_fundacion FOREIGN KEY(fundacion_id) REFERENCES fundacion(id),
	CONSTRAINT fk_servicio FOREIGN KEY(servicio_id) REFERENCES servicio(id)
);

CREATE TABLE IF NOT EXISTS persona(
	cedula VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	apellido VARCHAR(20) NOT NULL,
	telefono VARCHAR(20),
	correo VARCHAR(30),
	direccion TEXT,
	deleted_at DATE DEFAULT NULL

);

CREATE TABLE IF NOT EXISTS empleado(
	id VARCHAR(40) PRIMARY KEY,
	cedula VARCHAR(20) UNIQUE,
	fundacion_id VARCHAR(40) NOT NULL,
	CONSTRAINT fk_fundacion FOREIGN KEY(fundacion_id) REFERENCES fundacion(id),
	CONSTRAINT fk_cedula FOREIGN KEY(cedula) REFERENCES persona(cedula),
	deleted_at DATE DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS beneficiario(
	id VARCHAR(40) PRIMARY KEY,
	cedula VARCHAR(20) UNIQUE,
	fundacion_id VARCHAR(40) NOT NULL,
	CONSTRAINT fk_fundacion FOREIGN KEY(fundacion_id) REFERENCES fundacion(id),
	CONSTRAINT fk_cedula FOREIGN KEY(cedula) REFERENCES persona(cedula),
	deleted_at DATE DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS solicitud(
	id VARCHAR(40) PRIMARY KEY,
	beneficiario_id VARCHAR(40) NOT NULL,
	empleado_id VARCHAR(40) NOT NULL,
	fundacion_id VARCHAR(40) NOT NULL,
	prioridad solicitud_prioridad NULL,
	status solicitud_status NOT NULL,
	created_at DATE NOT NULL,
	updated_at DATE NOT NULL,
	deleted_at DATE DEFAULT NULL,
	CONSTRAINT fk_beneficiario FOREIGN KEY(beneficiario_id) REFERENCES beneficiario(id),
	CONSTRAINT fk_empleado FOREIGN KEY(empleado_id) REFERENCES empleado(id),
	CONSTRAINT fk_fundacion FOREIGN KEY(fundacion_id) REFERENCES fundacion(id)
);

CREATE TABLE IF NOT EXISTS solicitud_presupuesto(
	solicitud_id VARCHAR(40) NOT NULL,
	servicio_id VARCHAR(40) NOT NULL,
	costo_generado FLOAT NOT NULL,
	deleted_at DATE DEFAULT NULL,
	CONSTRAINT fk_solicitud FOREIGN KEY(solicitud_id) REFERENCES solicitud(id),
	CONSTRAINT fk_servicio FOREIGN KEY(servicio_id) REFERENCES servicio(id)
);

--USER MANAGEMENT TABLES
CREATE TABLE IF NOT EXISTS rol(
	id VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(40) NOT NULL,
	deleted_at DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS permiso(
	id VARCHAR(40) PRIMARY KEY,
	descripcion VARCHAR(80) NOT NULL,
	deleted_at DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS rol_permiso(
	rol_id VARCHAR(40) NOT NULL,
	permiso_id VARCHAR(40) NOT NULL,
	CONSTRAINT fk_rol FOREIGN KEY(rol_id) REFERENCES rol(id),
	CONSTRAINT fk_permiso FOREIGN KEY(permiso_id) REFERENCES permiso(id),
	deleted_at DATE DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS usuario(
	id VARCHAR(40) PRIMARY KEY,
	empleado_id VARCHAR(40) NOT NULL,
	rol_id VARCHAR(40) NOT NULL,
	username VARCHAR(40) NOT NULL,
	password VARCHAR(60) NOT NULL,
	CONSTRAINT fk_empleado FOREIGN KEY(empleado_id) REFERENCES empleado(id),
	CONSTRAINT fk_rol FOREIGN KEY(rol_id) REFERENCES rol(id),
	deleted_at DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS charla(
	id VARCHAR(40) PRIMARY KEY,
	tema VARCHAR(80) NOT NULL,
	lugar VARCHAR(80) NOT NULL,
	organismo VARCHAR(80) NOT NULL,
	fecha VARCHAR(60) NOT NULL,
	deleted_at DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS asistencia_charla(
	cedula VARCHAR(40) NOT NULL,
	charla_id VARCHAR(80) NOT NULL,
	deleted_at DATE DEFAULT NULL
);

--VIEWS
CREATE VIEW partida_total_actual AS
select sum(partida_asignada) from log_partidas_asignadas
where annio in (select max(annio) as currentYear from log_partidas_asignadas);

CREATE VIEW view_presupuestos_totales_solicitud AS
SELECT fundacion_id, solicitud_id, sum(costo_generado) as costo_total, s.status, s.updated_at as fecha FROM solicitud s
join solicitud_presupuesto sp
on sp.solicitud_id = s.id
group by fundacion_id, solicitud_id, s.status, fecha;

CREATE VIEW view_total_gastado_anual AS
select fundacion_id, sum(costo_total) total_gastado, EXTRACT(YEAR FROM fecha) annio from view_presupuestos_totales_solicitud
where status = 'aprobado'
group by fundacion_id, fecha;
--INSERT DEFAULT VALUES

INSERT INTO persona(cedula, nombre, apellido, telefono, correo, direccion)
	VALUES('27317962','kevin','cheng','584126796098','chengkev2000@gmail.com','direccion'),
		  ('27317963','Juan','cheng','584126796098','test@test.com',null),
		  ('27317964','Carmelo','Perez','584126796099',null,null),
		  ('27317965','Theo','Bach',null,null,null);
		  
INSERT INTO gobernacion(id, nombre, fondos)
	VALUES('gb001','Gobernacion de Lara', 10000);
		  

INSERT INTO fundacion(id, nombre, presupuesto, porcentaje_partido_anual, gobernacion_id)
	VALUES('43422123-da06-4890-8e3a-7131e32e5c2a','Fundacion de Ayuda a Niños, Niñas y Adolescentes',100,50, 'gb001'),
		  ('309899e5-3dae-4d58-83c3-8cec9763db82','Fundacion para la Atencion al Discapacitado',100,30, 'gb001'),
		  ('0832688e-e376-4e20-bbf7-3d7be51e9d43','Fundacion Regional de la Mujer',100,20, 'gb001');
		  
INSERT INTO empleado(id, cedula, fundacion_id)
	VALUES('e81a71bf-413a-4554-bcd7-834f7d4fafd8','27317962','43422123-da06-4890-8e3a-7131e32e5c2a'),
		  ('78fe4548-d818-429f-b5d4-65388fba3738','27317963','309899e5-3dae-4d58-83c3-8cec9763db82'),
		  ('32ded874-6467-4463-bf01-cc37490bd381','27317964','0832688e-e376-4e20-bbf7-3d7be51e9d43');

INSERT INTO beneficiario(id, cedula, fundacion_id)
	VALUES('7552170102','27317962','43422123-da06-4890-8e3a-7131e32e5c2a'),
		  ('9038677715','27317963','309899e5-3dae-4d58-83c3-8cec9763db82'),
		  ('8865837763','27317964','0832688e-e376-4e20-bbf7-3d7be51e9d43');
		  		  

INSERT INTO rol(id, nombre)
	VALUES('c603f913-2ac5-4353-b2aa-776d222af6ca','admin'),
		  ('336a1ef0-e552-4506-9f91-b2400e08a3b5','supervisor'),
		  ('c5067a41-1389-493b-84b9-4b277cd733ee','operador');

INSERT INTO permiso(id, descripcion)
	VALUES('bck001','backoffice'),
		  ('hom001','home'),
		  ('sup001','supervisor'),
		  ('opr001','operador');

INSERT INTO rol_permiso(rol_id, permiso_id)
	VALUES('c603f913-2ac5-4353-b2aa-776d222af6ca', 'bck001'),
		  ('c603f913-2ac5-4353-b2aa-776d222af6ca', 'hom001'),
		  ('c603f913-2ac5-4353-b2aa-776d222af6ca', 'sup001'),
		  ('c603f913-2ac5-4353-b2aa-776d222af6ca', 'opr001');

INSERT INTO usuario(id, empleado_id, rol_id, username, password)
	VALUES('5c84c51a-0470-4fef-8456-c4f61822c610','e81a71bf-413a-4554-bcd7-834f7d4fafd8','c603f913-2ac5-4353-b2aa-776d222af6ca','admin','admin');
	
-- ids are placeholders at the moment
INSERT INTO servicio(id, nombre, tipo)
	VALUES('1', 'examen', 'medico'),
		  ('2', 'consula medica general', 'medico'),
		  ('3', 'consulta psicologica', 'medico'),
		  ('4', 'consulta odontologica', 'medico'),
		  ('5', 'examen Rx', 'medico'),
		  ('6', 'donaciones para discapacitados', 'otros'),
		  ('7', 'cesta de comida', 'otros'),
		  ('8', 'tanques de agua', 'otros');

INSERT INTO fundacion_servicio(fundacion_id, servicio_id, costo)
	VALUES('43422123-da06-4890-8e3a-7131e32e5c2a', '1', 10),
		  ('43422123-da06-4890-8e3a-7131e32e5c2a', '2', 20),
		  ('43422123-da06-4890-8e3a-7131e32e5c2a', '3', 30),
		  ('43422123-da06-4890-8e3a-7131e32e5c2a', '4', 40),
		  ('309899e5-3dae-4d58-83c3-8cec9763db82', '1', 30),
		  ('309899e5-3dae-4d58-83c3-8cec9763db82', '5', 50),
		  ('309899e5-3dae-4d58-83c3-8cec9763db82', '6', 10),
		  ('309899e5-3dae-4d58-83c3-8cec9763db82', '7', 20),
		  ('309899e5-3dae-4d58-83c3-8cec9763db82', '8', 20),
		  ('0832688e-e376-4e20-bbf7-3d7be51e9d43', '8', 50),
		  ('0832688e-e376-4e20-bbf7-3d7be51e9d43', '5', 65),
		  ('0832688e-e376-4e20-bbf7-3d7be51e9d43', '3', 30),
		  ('0832688e-e376-4e20-bbf7-3d7be51e9d43', '7', 20);
		  
INSERT INTO charla(id, tema, lugar, organismo, fecha)
	VALUES('1', 'Importancia de la Libertad Economica', 'Parque Nacional', 'Econintech', '2022-01-01'),
		  ('2', 'Crypto: Camino a la Economia Digital', 'Parque Nacional', 'digital Ocean', '2022-01-01'),
		  ('3', 'Play to Earn: Beware of scams', 'Parque Nacional', 'Google', '2022-01-01'),
		  ('4', 'Venezuela, posible Silicon Valley de Latinoamerica?', 'Parque Nacional', 'Platzi', '2022-01-01');

INSERT INTO asistencia_charla(cedula, charla_id)
	VALUES('27317962','1'),
		  ('27317962','2'),
		  ('27317962','3'),
		  ('27317962','4'),
		  ('27317963','4'),
		  ('27317963','3'),
		  ('27317963','2'), 
		  ('27317963','1'),
		  ('27317964','3'),
		  ('27317964','1'),
		  ('27317965','3');
