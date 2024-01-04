package model.bouquet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import bdd.BddObject;
import model.activite.Activite;
import model.database.ConnexionBdd;
import model.database.Constante;

public class BouquetActivite {
    Bouquet bouquet;
    Activite activite;
    Date dateBouquetActivite;
    public BouquetActivite(Bouquet bouquet, Activite activite, Date dateBouquetActivite)
    throws Exception {
        this.setBouquet(bouquet);
        this.setActivite(activite);
        this.setDateBouquetActivite(dateBouquetActivite);
    }

    public BouquetActivite(Connection con, String idBouquet, String idActivite, Date dateBouquetActivite)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            jAiOuvert=true;
        }
        try {
            this.setBouquet((Bouquet) BddObject.findById(con, Bouquet.class, idBouquet, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            this.setActivite((Activite) BddObject.findById(con, Activite.class, idActivite, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            this.setDateBouquetActivite(dateBouquetActivite);
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public BouquetActivite() {
    }
    public Bouquet getBouquet() {
        return bouquet;
    }
    public void setBouquet(Bouquet bouquet)
    throws Exception {
        if(bouquet==null) {
            throw new Exception("Veuillez entrer un bouquet");
        }
        this.bouquet = bouquet;
    }
    public Activite getActivite() {
        return activite;
    }
    public void setActivite(Activite activite)
    throws Exception {
        if(activite==null) {
            throw new Exception("Veuillez entrer une activite");
        }
        this.activite = activite;
    }
    public Date getDateBouquetActivite() {
        return dateBouquetActivite;
    }
    public void setDateBouquetActivite(Date dateBouquetActivite)
    throws Exception {
        if(dateBouquetActivite==null) {
            throw new Exception("Veuillez entrer une date de creation bouquet activite");
        }
        if(this.getActivite()==null) {
            throw new Exception("Veuillez entrer une activite");
        }
        if(this.getBouquet()==null) {
            throw new Exception("Veuillez entrer un bouquet");
        }
        if(dateBouquetActivite.compareTo(this.getActivite().getDateCreation())<0||dateBouquetActivite.compareTo(this.getBouquet().getDateCreation())<0) {
            throw new Exception("Date d'ajout de bouquet activite invalide");
        }
        this.dateBouquetActivite = dateBouquetActivite;
    }
    
    public void newBouquetActivite(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            jAiOuvert=true;
        }
        PreparedStatement preparedStatement=null;
        try {
            String sql="insert into activite_bouquet values(?, ?, ?)";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getBouquet().getIdBouquet());
            preparedStatement.setString(2, this.getActivite().getIdActivite());
            preparedStatement.setDate(3, this.getDateBouquetActivite());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (Exception e) {
            throw e;
        } finally {
            preparedStatement.close();
            if(jAiOuvert) {
                con.close();
            }
        }
    }
}
