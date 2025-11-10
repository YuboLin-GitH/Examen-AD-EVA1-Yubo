DROP DATABASE IF EXISTS Taller;
CREATE DATABASE Taller;
Use Taller;

CREATE TABLE Coches(
				   idcoche int auto_increment primary key,
                   matricula varchar(7) ,
                   marca varchar(16) not null,
                   km int not null);

INSERT INTO Coches(matricula,marca,km)  VALUES ('4455BBB','Renault',25000), ('445CCC','Ford',36000), ('4455DDD','Mercedes',38000),
							('4455FFF','BMV',89000), ('4455GGG','Honda',3600);


CREATE TABLE Reparaciones(
                   idreparacion int unsigned auto_increment primary key,
                   idcoche int ,
                   fechareparacion date,
                   descripcion  varchar(200),
                   precio int unsigned,
                   pagado varchar(2),
                   FOREIGN KEY (idcoche) REFERENCES Coches(idcoche) ON DELETE CASCADE
);
INSERT INTO Reparaciones VALUES
            (1,1,"2025/10/25","Cambio de aceite y filtro de polen",300,"Si"),
            (2,1,"2025/10/30","Cambio del alternador",800,"No"),
            (3,2,"2025/06/30","Cambio de bateria",200,"Si"),
            (4,2,"2025/05/03","Cambio de la distribucion ",1200,"Si"),
            (5,2,"2025/11/03","Cambio amortiguadores",500,"No"),
            (6,3,"2025/02/01","Cambio de ruedas",600,"Si"),
            (7,3,"2025/11/01","Cambio de amortiguadores",450,"No"),
            (8,4,"2025/11/03","Cambio de bombillas faros delanteros",100,"No");
