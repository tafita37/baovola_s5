-- Historique d'activite et prix
    create or replace view v_activite_prix_historique as select activite.*, coalesce(activite_prix.prix_activite, 0) as prix_activite, activite_prix.date_prix_activite from activite left join activite_prix on activite_prix.id_activite=activite.id_activite;

-- Id activite avec le max de la date prix
    create or replace view v_id_activite_last_prix as select id_activite, max(date_prix_activite) as date_prix_activite from activite_prix group by id_activite;

-- Activite et dernier prix
    create or replace view v_last_activite_prix as select * from v_activite_prix_historique where (id_activite, date_prix_activite) in (select*from v_id_activite_last_prix) or prix_activite=0;

-- Voyage et activite
    create or replace view v_voyage_activite as select voyage_activite.*, v_last_activite_prix.nom_activite, v_last_activite_prix.date_creation, v_last_activite_prix.prix_activite, v_last_activite_prix.date_prix_activite, v_last_activite_prix.prix_activite*voyage_activite.nb_activite as prix_total from voyage_activite join v_last_activite_prix on v_last_activite_prix.id_activite=voyage_activite.id_activite;

-- Voyage activite et prix total
    create or replace view v_voyage_activite_prix_total as select id_voyage, sum(prix_total) as prix_total from v_voyage_activite group by id_voyage;

-- Biais et mouvement
    create or replace view v_biais_mouvement as select biais.id_activite, biais.date_biais, biais_mouvement.*  from biais join biais_mouvement on biais.id_biais=biais_mouvement.id_biais;

-- Reste de biais en mouvement par activite par voyage
    create or replace view v_reste_biais_mouvement as select id_activite, id_biais, date_biais, sum(entree-sortie) as reste_stock from v_biais_mouvement group by id_activite, id_biais, date_biais;