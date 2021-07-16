use master;
GO
DROP database IF EXISTS DBCONFERENCIAS;
CREATE DATABASE DBCONFERENCIAS;
GO
USE DBCONFERENCIAS;
GO
-- Table: CONFERENCIAS
CREATE TABLE CONFERENCIAS (
    IDCONF int  NOT NULL IDENTITY(1,1),
    NOMCONF varchar(50)  NOT NULL,
    NOMPRICONF varchar(40)  NOT NULL,
    NOMSEGCONF varchar(40)  NOT NULL,
    PRECONF int  NOT NULL,
    DESCONF varchar(60)  NOT NULL,
    FECCONF date  NOT NULL,
    ESTCONF char(1)  NOT NULL DEFAULT 'A',
    CONSTRAINT CONFERENCIAS_pk PRIMARY KEY  (IDCONF)
);

-- Table: CONFERENCIA_DETALLE
CREATE TABLE CONFERENCIA_DETALLE (
    IDCONDET int  NOT NULL IDENTITY(1,1),
    IDCONF int  NOT NULL,
    DESCONDET varchar(60)  NOT NULL,
    ACUCONDET int  NOT NULL,
    MONCONDET int  NOT NULL,
    CONSTRAINT CONFERENCIA_DETALLE_pk PRIMARY KEY  (IDCONDET)
);

-- Table: PAGOS
CREATE TABLE PAGOS (
    IDPAG int  NOT NULL IDENTITY(1,1),
    FECPAG date  NOT NULL,
    CANPAG int  NOT NULL,
    MONPAG int  NOT NULL,
    IDPER int  NOT NULL,
    IDCONF int  NOT NULL,
    CONSTRAINT PAGOS_pk PRIMARY KEY  (IDPAG)
);

-- Table: PERSONA
CREATE TABLE PERSONA (
    IDPER int  NOT NULL IDENTITY(1,1),
    NOMPER varchar(20)  NOT NULL,
    APEPER varchar(20)  NOT NULL,
    PASPER varchar(16)  NOT NULL,
    EMAPER varchar(40)  NOT NULL,
	DIRPER varchar(50) not null,
    DNIPER char(8)  NOT NULL,
    CELPER char(9)  NOT NULL,
    ESTPER char(1)  NOT NULL DEFAULT 'A',
    ROLPER char(1)  NOT NULL,
    PERSONA_IDPER int  NULL,
    CONSTRAINT PERSONA_pk PRIMARY KEY  (IDPER)
);

-- foreign keys
-- Reference: CONFERENCIA_DETALLE_CONFERENCIAS (table: CONFERENCIA_DETALLE)
ALTER TABLE CONFERENCIA_DETALLE ADD CONSTRAINT CONFERENCIA_DETALLE_CONFERENCIAS
    FOREIGN KEY (IDCONF)
    REFERENCES CONFERENCIAS (IDCONF);
GO
-- Reference: PAGOS_CONFERENCIAS (table: PAGOS)
ALTER TABLE PAGOS ADD CONSTRAINT PAGOS_CONFERENCIAS
    FOREIGN KEY (IDCONF)
    REFERENCES CONFERENCIAS (IDCONF);
GO
-- Reference: PAGOS_PERSONA (table: PAGOS)
ALTER TABLE PAGOS ADD CONSTRAINT PAGOS_PERSONA
    FOREIGN KEY (IDPER)
    REFERENCES PERSONA (IDPER);
GO
-- Reference: PERSONA_PERSONA (table: PERSONA)
ALTER TABLE PERSONA ADD CONSTRAINT PERSONA_PERSONA
    FOREIGN KEY (PERSONA_IDPER)
    REFERENCES PERSONA (IDPER);
GO
-- validar si hay administradores con el mismo DNI
CREATE OR ALTER PROCEDURE sptAdministrador
(
    @nombre VARCHAR(20),
    @apellido VARCHAR(20),
	@password VARCHAR(16),
	@email varchar(40),
	@direccion varchar(50),
    @dni CHAR(8),
	@celular CHAR(9),
    @estado CHAR(1),
	@rol CHAR(1)
    
)
AS
    BEGIN
        BEGIN TRAN
        BEGIN TRY
            IF (SELECT COUNT(*) FROM dbo.PERSONA AS V WHERE V.DNIPER = @dni) = 1
                ROLLBACK TRAN
            ELSE
                INSERT INTO dbo.PERSONA
                (NOMPER, APEPER,PASPER, EMAPER,DIRPER,DNIPER,CELPER,ESTPER,ROLPER)
                VALUES
                (UPPER(@nombre), UPPER(@apellido), @password, @email, @direccion, @dni, @celular, @estado,@rol)
                COMMIT TRAN

        END TRY
        BEGIN CATCH
            SELECT 'Este vendedor ya ha sido registrado.' AS 'ERROR'
                IF @@TRANCOUNT > 0 ROLLBACK TRAN; 
        END CATCH
    END
GO
-- insertar datos de administradores
EXEC sptAdministrador 'EDGARD', 'RODRIGUEZ', 'QUID1343','ADMIN@GO.COM','JR. PUNO', '72717476','945654234','A','A'
EXEC sptAdministrador 'JULIAN', 'SANCHEZ', 'QUID1343','ADMIN@GO.COM','JR. CHINCHA', '37717377','945654234','A','A'
EXEC sptAdministrador 'MARCOS', 'PEÑA', 'QUID1343','ADMIN@GO.COM','JR. AREQUIPA', '27717322','945654234','A','A'
EXEC sptAdministrador 'LUCAS', 'FILM', 'QUID1343','ADMIN@GO.COM','JR. CHACHAPOLLAS', '17717372','945654234','A','A'
EXEC sptAdministrador 'MATEO', 'FERNADEZ', 'QUID1343','ADMIN@GO.COM','JR. ICA', '66757362','945654234','A','A'
GO
-- VALIDAR SI EXISTEN PERSONAS INSCRITAS EN LAS CONFERENCIAS CON EL MISMO DNI
CREATE OR ALTER PROCEDURE sptPersonasInscritas
(
    @nombre VARCHAR(20),
    @apellido VARCHAR(20),
	@password VARCHAR(16),
	@email varchar(40),
	@direccion varchar(50),
    @dni CHAR(8),
	@celular CHAR(9),
    @estado CHAR(1),
	@rol CHAR(1),
	@identificadorPersonal int 
)
AS
    BEGIN
        BEGIN TRAN
        BEGIN TRY
            IF (SELECT COUNT(*) FROM dbo.PERSONA AS V WHERE V.DNIPER = @dni) = 1
                ROLLBACK TRAN
            ELSE
                INSERT INTO dbo.PERSONA
                (NOMPER, APEPER,PASPER, EMAPER,DIRPER,DNIPER,CELPER,ESTPER,ROLPER,PERSONA_IDPER)
                VALUES
                (UPPER(@nombre), UPPER(@apellido), @password, @email, @direccion, @dni, @celular, @estado,@rol,@identificadorPersonal)
                COMMIT TRAN

        END TRY
        BEGIN CATCH
            SELECT 'Este vendedor ya ha sido registrado.' AS 'ERROR'
                IF @@TRANCOUNT > 0 ROLLBACK TRAN; 
        END CATCH
    END
GO
-- inserción de datos
EXEC sptPersonasInscritas 'MARTA', 'MONTOYA', 'QUID1343','user1@GO.COM','JR. moquegua', '62227476','945654234','A','A','1'
EXEC sptPersonasInscritas 'WILIAMS', 'PINO', 'Quilmana','user2@GO.COM','JR. parteos', '47733377','945654234','A','A','1'
EXEC sptPersonasInscritas 'BRIGITH', 'RIVERA', 'Imper123','user3@GO.COM','JR. MARCAHUASI', '17744322','945654234','A','A','2'
EXEC sptPersonasInscritas 'MAYRA', 'HIPOLITO', 'Justin12','user5@GO.COM','JR. CHIN', '67733372','945654234','A','A','1'
EXEC sptPersonasInscritas 'TYRZA', 'CANDELA', 'Martfff','user22@GO.COM','JR. AYACY', '16717362','945654234','A','A','4'
GO

/* Procedimiento Almacenado para insertar conferencias sin que se repita el nombre de las conferencias*/
CREATE OR ALTER PROCEDURE spInsertConf
    (
		@nombreConf VARCHAR(50),
		@NombrePrimero VARCHAR(40),
		@NombreSegundo VARCHAR(40),
		@PrecioConferencia int,
        @descripcionConf VARCHAR(60),
        @FechaConf date,
        @estadoConf CHAR(1)
    )
AS
    BEGIN
        SET NOCOUNT ON
        BEGIN TRY
        BEGIN TRAN;
            SET DATEFORMAT dmy
            IF (SELECT COUNT(*) FROM dbo.CONFERENCIAS AS P WHERE P.NOMCONF = @nombreConf) = 1
                ROLLBACK TRAN;
            ELSE
                INSERT INTO dbo.CONFERENCIAS
               (NOMCONF, NOMPRICONF, NOMSEGCONF, PRECONF, DESCONF, FECCONF,ESTCONF)
                VALUES
               (UPPER(@nombreConf),UPPER(@NombrePrimero),UPPER(@NombreSegundo), @PrecioConferencia, @descripcionConf, @FechaConf, @estadoConf)
               COMMIT TRAN;
        END TRY
        BEGIN CATCH
            SELECT 'El producto ya existe.' AS 'ERROR'
            IF @@TRANCOUNT > 0 ROLLBACK TRAN; 
        END CATCH
    END
GO
EXEC spInsertConf 'CONTAMINACION Y MEDIO AMBIENTE', 'JUAN PEREZ', 'TYRZA CANDELA', '40', 'MEDIO AMBIENTE Y DESARROLLO', '2021-12-05','A'
EXEC spInsertConf 'PROGRAMACION POO', 'MARCOS ANDRE', 'XIOMARA MUÑOS', '40', 'JAVA Y OTROS', '2021-12-05','A'
EXEC spInsertConf 'CIENCIA Y PROBLEMAS', 'EDGAR FELIPE', 'MIRTHA VASQUEZ', '40', 'NUEVOS PARADIGMAS', '2021-12-05','A'
EXEC spInsertConf 'MEDIOS DISRRUPTIVOS', 'ALEXA MENDOZA', 'FRANK SUAREZ', '40', 'NUEVAS TECNOLOGIAS', '2021-12-05','A'
GO

INSERT INTO PAGOS (FECPAG,CANPAG,MONPAG,IDPER,IDCONF)
VALUES	('2021-12-05','40','30','1','1'),
		('2021-12-05','40','10','2','3'),
		('2021-12-05','40','20','1','2'),
		('2021-12-05','40','35','2','1');
GO
INSERT INTO CONFERENCIA_DETALLE(IDCONF,DESCONDET,ACUCONDET,MONCONDET)
VALUES	('1','GASTOS ADMINISTRATIVOS','80','60'),
		('2','GASTOS EXTRAS','90','50'),
		('3','EXTRAS','40','30'),
		('4','DETALLES','50','90');
GO

CREATE OR ALTER  VIEW V_PAGOS as

SELECT FECPAG,CANPAG,MONPAG,CONCAT(PERSONA.NOMPER,' ',PERSONA.APEPER)AS PERPAG,CONFERENCIAS.NOMCONF FROM PAGOS
inner join PERSONA ON PAGOS.IDPAG= PERSONA.IDPER
inner join CONFERENCIAS ON PAGOS.IDPAG= CONFERENCIAS.IDCONF
GO

CREATE VIEW V_PERSONA as
select ROW_NUMBER() OVER( ORDER BY super.IDPER desc) AS FILA, SUPER.IDPER,
super.NOMPER,super.APEPER,super.PASPER,
super.EMAPER,super.DIRPER,super.DNIPER,super.CELPER,
super.ROLPER,super.ESTPER,CONCAT(infer.NOMPER,' ',infer.APEPER)
as RELACION  from PERSONA  as super
left join PERSONA as infer on super.PERSONA_IDPER =infer.IDPER;
GO