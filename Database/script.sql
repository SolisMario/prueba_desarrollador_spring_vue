CREATE TABLE Cliente
(
    Id        INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
    Nombre    VARCHAR(50)                    NOT NULL,
    Apellido  VARCHAR(50)                    NOT NULL,
    Direccion VARCHAR(50)                    NOT NULL,
    Telefono  VARCHAR(8)                     NOT NULL,
    Correo    VARCHAR(50)                    NOT NULL
);

CREATE TABLE Tipo_Cuenta
(
    Id          INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
    Nombre      VARCHAR(20)                    NOT NULL,
    Descripcion VARCHAR(50)                    NOT NULL
);

CREATE TABLE Cuenta
(
    Id             INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
    Tipo_Cuenta_Id INT                            NOT NULL,
    Cliente_Id     INT                            NOT NULL,
    Numero         VARCHAR(20)                    NOT NULL,
    Saldo          DECIMAL                        NOT NULL,
    FOREIGN KEY (Tipo_Cuenta_Id) REFERENCES Tipo_Cuenta (Id),
    FOREIGN KEY (Cliente_Id) REFERENCES Cliente (Id)
);

CREATE TABLE Chequera
(
    Id               INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
    Cuenta_Id        INT                            NOT NULL,
    Numero           VARCHAR(20)                    NOT NULL,
    Cantidad_Cheques INT                            NOT NULL
);

CREATE TABLE Cheque
(
    Id          INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
    Chequera_Id INT                            NOT NULL,
    Numero      VARCHAR(20)                    NOT NULL,
    Lugar       VARCHAR(50)                    NOT NULL,
    Fecha       DATE                           NOT NULL,
    Monto       DECIMAL                        NOT NULL,
    Estado      INT                            NOT NULL,
    FOREIGN KEY (Chequera_Id) REFERENCES Chequera (Id)
);

INSERT INTO Tipo_Cuenta(nombre, descripcion) VALUES ('MONETARIA', 'Cuenta de mercado monetario.');
INSERT INTO Tipo_Cuenta(nombre, descripcion) VALUES ('AHORRO', 'Cuenta de ahorros.');