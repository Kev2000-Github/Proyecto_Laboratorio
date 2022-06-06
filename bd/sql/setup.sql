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
DROP VIEW IF EXISTS view_lista_presupuestos;
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
group by fundacion_id, annio;

CREATE VIEW view_lista_presupuestos AS
SELECT 
	s.id,
	f.nombre as fundacion,
	e.cedula as empleado,
	b.cedula as beneficiario,
	vp.costo_total as presupuesto_total,
	s.status
FROM solicitud s
JOIN fundacion f on s.fundacion_id = f.id
JOIN view_presupuestos_totales_solicitud vp on vp.solicitud_id = s.id
JOIN beneficiario b on b.id = s.beneficiario_id
JOIN empleado e on e.id = s.empleado_id;
--INSERT DEFAULT VALUES

INSERT INTO persona(cedula, nombre, apellido, telefono, correo, direccion)
	VALUES('27317962','kevin','cheng','584126796098','chengkev2000@gmail.com','direccion'),
		  ('27317963','Juan','cheng','584126796098','test@test.com','mi casa'),
		  ('27317964','Carmelo','Perez','584126796099','carmelo@test.com','mi mansion'),
		  ('27317965','Theo','Bach','123456789','theo@test.com','mi choza');
		  
INSERT INTO gobernacion(id, nombre, fondos)
	VALUES('gb001','Gobernacion de Lara', 10000);
		  

INSERT INTO fundacion(id, nombre, presupuesto, porcentaje_partido_anual, gobernacion_id)
	VALUES('PRRV06ODH1G4XM1RH9','Fundacion de Ayuda a Niños, Niñas y Adolescentes',100,50, 'gb001'),
		  ('N1RXIYLQ9E6DXTFA54','Fundacion para la Atencion al Discapacitado',100,30, 'gb001'),
		  ('YBGLMAYHG4ZA699Q5O','Fundacion Regional de la Mujer',100,20, 'gb001');
		  
INSERT INTO empleado(id, cedula, fundacion_id)
	VALUES('QG7Y54GAZAVRB1S2KD','27317962','PRRV06ODH1G4XM1RH9'),
		  ('B54W9JZ3FU4H4WC8Y8','27317963','N1RXIYLQ9E6DXTFA54'),
		  ('W15T033XKAVIZVK95X','27317964','YBGLMAYHG4ZA699Q5O');

INSERT INTO beneficiario(id, cedula, fundacion_id)
	VALUES('7552170102','27317962','PRRV06ODH1G4XM1RH9'),
		  ('9038677715','27317963','N1RXIYLQ9E6DXTFA54'),
		  ('8865837763','27317964','YBGLMAYHG4ZA699Q5O');
		  		  

INSERT INTO rol(id, nombre)
	VALUES('OPTE5KYCY3M4BL49N9','admin'),
		  ('P62ZT9JUGH789WT54H','supervisor'),
		  ('QS0YGF5RCQ3F7UMSE4','operador');

INSERT INTO permiso(id, descripcion)
	VALUES('backOffice',''),
		  ('home',''),
		  ('login',''),
		  ('charla',''),
		  ('registros',''),
		  ('reportes',''),
		  ('persona',''),
		  ('addBeneficiario',''),
		  ('beneficiario',''),
		  ('updateBeneficiario',''),
		  ('addEmpleado',''),
		  ('empleado',''),
		  ('updateEmpleado',''),
		  ('listaPresupuesto',''),
		  ('listaResponsable',''),
		  ('listaSolicitante',''),
		  ('addSolicitud',''),
		  ('detalleSolicitud',''),
          ('supervisor',''),
          ('operador',''),
          ('fundacion',''),
		  ('addFundacion',''),
		  ('updateFundacion',''),
          ('servicio',''),
          ('addServicio',''),
		  ('solicitudes',''),
		  ('usuario',''),
		  ('updateUsuario',''),
		  ('addUsuario',''),
		  ('registrarAsistentes',''),
		  ('addCharla','');

INSERT INTO rol_permiso(rol_id, permiso_id)
	VALUES('OPTE5KYCY3M4BL49N9', 'backOffice'),
		  ('OPTE5KYCY3M4BL49N9', 'home'),
		  ('OPTE5KYCY3M4BL49N9', 'login'),
		  ('OPTE5KYCY3M4BL49N9', 'charla'),
		  ('OPTE5KYCY3M4BL49N9', 'registros'),
		  ('OPTE5KYCY3M4BL49N9', 'reportes'),
		  ('OPTE5KYCY3M4BL49N9', 'persona'),
		  ('OPTE5KYCY3M4BL49N9', 'addBeneficiario'),
		  ('OPTE5KYCY3M4BL49N9', 'beneficiario'),
		  ('OPTE5KYCY3M4BL49N9', 'updateBeneficiario'),
		  ('OPTE5KYCY3M4BL49N9', 'addEmpleado'),
		  ('OPTE5KYCY3M4BL49N9', 'empleado'),
          ('OPTE5KYCY3M4BL49N9', 'supervisor'),
          ('OPTE5KYCY3M4BL49N9', 'operador'),
		  ('OPTE5KYCY3M4BL49N9', 'updateEmpleado'),
		  ('OPTE5KYCY3M4BL49N9', 'listaPresupuesto'),
		  ('OPTE5KYCY3M4BL49N9', 'listaResponsable'),
		  ('OPTE5KYCY3M4BL49N9', 'listaSolicitante'),
		  ('OPTE5KYCY3M4BL49N9', 'addSolicitud'),
		  ('OPTE5KYCY3M4BL49N9', 'detalleSolicitud'),
		  ('OPTE5KYCY3M4BL49N9', 'solicitudes'),
          ('OPTE5KYCY3M4BL49N9', 'fundacion'),
		  ('OPTE5KYCY3M4BL49N9', 'addFundacion'),
		  ('OPTE5KYCY3M4BL49N9', 'updateFundacion'),
          ('OPTE5KYCY3M4BL49N9', 'servicio'),
          ('OPTE5KYCY3M4BL49N9', 'addServicio'),
		  ('OPTE5KYCY3M4BL49N9', 'usuario'),
		  ('OPTE5KYCY3M4BL49N9', 'updateUsuario'),
		  ('OPTE5KYCY3M4BL49N9', 'addUsuario'),
		  ('OPTE5KYCY3M4BL49N9', 'registrarAsistentes'),
		  ('OPTE5KYCY3M4BL49N9', 'addCharla'),
		  ('P62ZT9JUGH789WT54H', 'solicitudes'),
		  ('P62ZT9JUGH789WT54H', 'detalleSolicitud'),
		  ('P62ZT9JUGH789WT54H', 'login'),
		  ('QS0YGF5RCQ3F7UMSE4', 'addSolicitud'),
		  ('QS0YGF5RCQ3F7UMSE4', 'login');

INSERT INTO usuario(id, empleado_id, rol_id, username, password)
	VALUES('BXQPP9YHGLOM9RHU19','QG7Y54GAZAVRB1S2KD','OPTE5KYCY3M4BL49N9','admin','admin'),
		  ('HWQI6NKX4WADFDHHRJ','B54W9JZ3FU4H4WC8Y8','P62ZT9JUGH789WT54H','supervisor','supervisor'),
		  ('LJDBOOW0F4JL1MKHLO','W15T033XKAVIZVK95X','QS0YGF5RCQ3F7UMSE4','operador','operador');
	
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
	VALUES('PRRV06ODH1G4XM1RH9', '1', 10),
		  ('PRRV06ODH1G4XM1RH9', '2', 20),
		  ('PRRV06ODH1G4XM1RH9', '3', 30),
		  ('PRRV06ODH1G4XM1RH9', '4', 40),
		  ('N1RXIYLQ9E6DXTFA54', '1', 30),
		  ('N1RXIYLQ9E6DXTFA54', '5', 50),
		  ('N1RXIYLQ9E6DXTFA54', '6', 10),
		  ('N1RXIYLQ9E6DXTFA54', '7', 20),
		  ('N1RXIYLQ9E6DXTFA54', '8', 20),
		  ('YBGLMAYHG4ZA699Q5O', '8', 50),
		  ('YBGLMAYHG4ZA699Q5O', '5', 65),
		  ('YBGLMAYHG4ZA699Q5O', '3', 30),
		  ('YBGLMAYHG4ZA699Q5O', '7', 20);
		  
INSERT INTO charla(id, tema, lugar, organismo, fecha)
	VALUES('AQI1AUJTVUB0TILVKU', 'Importancia de la Libertad Economica', 'Parque Nacional', 'Econintech', '2022-01-01'),
		  ('V6LIWKRZAUPN6O5BTP', 'Crypto: Camino a la Economia Digital', 'Parque Nacional', 'digital Ocean', '2022-01-01'),
		  ('S1R2D917T2IQN23BKA', 'Play to Earn: Beware of scams', 'Parque Nacional', 'Google', '2022-01-01'),
		  ('X3TK9YL6TSX2PK5Y35', 'Venezuela, posible Silicon Valley de Latinoamerica?', 'Parque Nacional', 'Platzi', '2022-01-01'),
		  ('LNY2BRRWCZCDR6YG03', 'Las consecuencias del Bullying dejan marca en la sociedad', 'Parque Nacional', 'Psicologa', '2022-01-01'),
		  ('2HTN4O6LWX0XMICPB3', 'Los adultos infantilizados de hoy dia', 'Parque Nacional', 'Platzi', '2022-01-01');

INSERT INTO asistencia_charla(cedula, charla_id)
	VALUES('27317962','AQI1AUJTVUB0TILVKU'),
		  ('27317962','V6LIWKRZAUPN6O5BTP'),
		  ('27317962','S1R2D917T2IQN23BKA'),
		  ('27317962','X3TK9YL6TSX2PK5Y35'),
		  ('27317963','X3TK9YL6TSX2PK5Y35'),
		  ('27317963','S1R2D917T2IQN23BKA'),
		  ('27317963','V6LIWKRZAUPN6O5BTP'), 
		  ('27317963','AQI1AUJTVUB0TILVKU'),
		  ('27317964','S1R2D917T2IQN23BKA'),
		  ('27317964','AQI1AUJTVUB0TILVKU'),
		  ('27317965','S1R2D917T2IQN23BKA');
