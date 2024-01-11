-- Database
    -- Baovola
        \c postgres
        drop database baovola_s5;
        create database baovola_s5;
        \c baovola_s5;

-- Tables
    -- Bouquet
        create table bouquet(
            id_bouquet varchar(20) primary key,
            nom_bouquet varchar(30) unique,
            date_creation date
        );

    -- Activite
        create table activite(
            id_activite varchar(20) primary key,
            nom_activite varchar(30) unique,
            -- duree_heure int,
            date_creation date
        );
        
    -- Activite prix
        create table activite_prix(
            -- id_activite_prix serial primary key,
            id_activite varchar(20) references activite(id_activite),
            prix_activite double precision,
            date_prix_activite date,
            primary key(id_activite, date_prix_activite)
        );

    -- Activite et bouquet
        create table activite_bouquet(
            id_bouquet varchar(20) references bouquet(id_bouquet),
            id_activite varchar(20) references activite(id_activite),
            date_creation date,
            primary key(id_bouquet, id_activite)
        );

    -- Categorie de lieu
        create table categorie_lieu(
            id_categorie_lieu varchar(20) primary key,
            nom_categorie_lieu varchar(30)
        );

    -- Create Type Duree Table
    CREATE TABLE type_duree (
        id_type_duree VARCHAR(20) PRIMARY KEY,
        nom_type_duree VARCHAR(255),
        debut_duree INT,
        fin_duree INT
    );

    -- Create Categorie Lieu Table
    CREATE TABLE categorie_lieu (
        id_categorie_lieu VARCHAR(20) PRIMARY KEY,
        nom_categorie_lieu VARCHAR(255)
    );

    -- Create Voyage Table
    CREATE TABLE voyage (
        id_voyage SERIAL PRIMARY KEY,
        id_categorie_lieu VARCHAR(20) REFERENCES categorie_lieu(id_categorie_lieu),
        id_bouquet VARCHAR(20)
    );

    -- Create Voyage Activite Table
    CREATE TABLE voyage_activite (
        id_voyage int REFERENCES voyage(id_voyage),
        id_activite VARCHAR(20) references activite(id_activite),
        nb_activite INT,
        id_type_duree VARCHAR(20) REFERENCES type_duree(id_type_duree),
        PRIMARY KEY (id_voyage, id_activite)
    );


-- 11 janvier
    -- Biais
        create table biais(
            id_biais serial primary key, 
            id_activite varchar(20) references activite(id_activite), 
            date_biais date
        );

    -- Biais et mouvement
		create table biais_mouvement(
            id_biais int references biais(id_biais), 
            entree double precision, 
            sortie double precision, 
            date_mouvement date
        );

    -- Voya
		create table voyage_reservation(
            id_voyage int references voyage(id_voyage), 
            nb_personne int, 
            date_reservation date
        );
