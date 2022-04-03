DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS empleado;
DROP TABLE IF EXISTS persona;
DROP TABLE IF EXISTS cargo;
DROP TABLE IF EXISTS fundacion;

CREATE TABLE IF NOT EXISTS fundacion(
	id VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(80) NOT NULL,
	presupuesto FLOAT NOT NULL DEFAULT 0,
	porcentajePartidaAnual FLOAT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS persona(
	cedula VARCHAR(10) PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	apellido VARCHAR(20) NOT NULL,
	telefono VARCHAR(20),
	correo VARCHAR(30),
	direccion TEXT
);

CREATE TABLE IF NOT EXISTS cargo(
	id VARCHAR(40) PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS empleado(
	id VARCHAR(40) PRIMARY KEY,
	cedula VARCHAR(20) UNIQUE,
	cargoId VARCHAR(40) NOT NULL,
	fundacionId VARCHAR(40) NOT NULL,
	CONSTRAINT fk_fundacion FOREIGN KEY(fundacionId) REFERENCES fundacion(id),
	CONSTRAINT fk_cargo FOREIGN KEY(cargoId) REFERENCES cargo(id),
	CONSTRAINT fk_cedula FOREIGN KEY(cedula) REFERENCES persona(cedula)
);

CREATE TABLE IF NOT EXISTS usuario(
	id VARCHAR(40) PRIMARY KEY,
	empleadoId VARCHAR(40) NOT NULL,
	username VARCHAR(40) NOT NULL,
	password VARCHAR(60) NOT NULL,
	CONSTRAINT fk_empleado FOREIGN KEY(empleadoId) REFERENCES empleado(id)
);


INSERT INTO persona(cedula, nombre, apellido, telefono, correo, direccion)
	VALUES('27317962','kevin','cheng','584126796098','chengkev2000@gmail.com','direccion'),
		  ('27317963','Juan','cheng','584126796098','test@test.com',null),
		  ('27317964','Carmelo','Perez','584126796099',null,null),
		  ('27317965','Theo','Bach',null,null,null);
		  
INSERT INTO fundacion(id, nombre, presupuesto, porcentajePartidaAnual)
	VALUES('43422123-da06-4890-8e3a-7131e32e5c2a','Fundacion de Ayuda a Niños, Niñas y Adolescentes',100,50),
		  ('309899e5-3dae-4d58-83c3-8cec9763db82','Fundacion para la Atencion al Discapacitado',100,30),
		  ('0832688e-e376-4e20-bbf7-3d7be51e9d43','Fundacion Regional de la Mujer',100,20);
		  
INSERT INTO cargo(id, nombre)
	VALUES('cad3f554-e675-4885-beaa-3f6b5af5d918','operador'),
		  ('5364a8ea-9c02-4292-8d2b-8936a719a497','supervisor'),
		  ('43bf5250-c83a-4a33-9abc-a28b12bcd45a','administrador');
		  
INSERT INTO empleado(id, cedula, cargoId, fundacionId)
	VALUES('e81a71bf-413a-4554-bcd7-834f7d4fafd8','27317962','43bf5250-c83a-4a33-9abc-a28b12bcd45a','43422123-da06-4890-8e3a-7131e32e5c2a'),
		  ('78fe4548-d818-429f-b5d4-65388fba3738','27317963','5364a8ea-9c02-4292-8d2b-8936a719a497','309899e5-3dae-4d58-83c3-8cec9763db82'),
		  ('32ded874-6467-4463-bf01-cc37490bd381','27317964','cad3f554-e675-4885-beaa-3f6b5af5d918','0832688e-e376-4e20-bbf7-3d7be51e9d43');
		  
INSERT INTO usuario(id, empleadoId, username, password)
	VALUES('5c84c51a-0470-4fef-8456-c4f61822c610','e81a71bf-413a-4554-bcd7-834f7d4fafd8','admin','root');