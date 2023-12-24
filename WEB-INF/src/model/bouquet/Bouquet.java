package model.bouquet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;
import bdd.BddObject;
import model.database.ConnexionBdd;
import model.database.Constante;
import model.activite.Activite;


@Table(tableName="bouquet", idTable = "id_bouquet", prefixe = "BOUQ", sequence = "id_bouquet", nbNumerique = 6)
public class Bouquet {
    @PrimaryKey
    @FieldMapping(columnName = "id_bouquet")
    String idBouquet;
    @FieldMapping(columnName = "nom_bouquet")
    String nomBouquet;
    @FieldMapping(columnName = "date_creation")
    Date dateCreation;
    BouquetActivite[] listeBouquetActivite;

    public Bouquet(String idBouquet, String nomBouquet, Date dateCreation)
    throws Exception {
        this.setIdBouquet(idBouquet);
        this.setNomBouquet(nomBouquet);
        this.setDateCreation(dateCreation);
    }

    public Bouquet(String idBouquet, String nomBouquet, Date dateCreation, BouquetActivite[] listeBouquetActivite)
    throws Exception {
        this.setIdBouquet(idBouquet);
        this.setNomBouquet(nomBouquet);
        this.setDateCreation(dateCreation);
        this.setListeBouquetActivite(listeBouquetActivite);
    }

    public Bouquet() {
    }

    public String getIdBouquet() {
        return idBouquet;
    }

    public void setIdBouquet(String idBouquet)
    throws Exception {
        if(idBouquet==null||idBouquet.length()==0) {
            throw new Exception("Veuillez entrer un id de bouquet");
        }
        this.idBouquet = idBouquet;
    }

    public String getNomBouquet() {
        return nomBouquet;
    }

    public void setNomBouquet(String nomBouquet)
    throws Exception {
        if(nomBouquet==null||nomBouquet.length()==0) {
            throw new Exception("Veuillez entrer un nom de bouquet");
        }
        this.nomBouquet = nomBouquet;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation)
    throws Exception {
        if(dateCreation==null) {
            throw new Exception("Veuillez entrer une date de creation de bouquet");
        }
        this.dateCreation = dateCreation;
    }

    public void setListeBouquetActivite(Object[] listeBouquetActivite)
    throws Exception {
        if(listeBouquetActivite==null) {
            throw new Exception("Veuillez entrer une liste d'activite pour le bouquet "+this.getNomBouquet());
        }
        this.listeBouquetActivite=new BouquetActivite[listeBouquetActivite.length];
        for(int i=0; i<listeBouquetActivite.length; i++) {
            BouquetActivite bouquetActivite=(BouquetActivite) listeBouquetActivite[i];
            if(bouquetActivite.getDateBouquetActivite().compareTo(this.getDateCreation())<=0) {
                throw new Exception("Date d'ajout de bouquet activite invalide");
            }
            this.listeBouquetActivite[i]=bouquetActivite;
        }
    }

    public Object[] getListeBouquetActivite(Connection con)
    throws Exception {
        ArrayList<BouquetActivite> result=new ArrayList<BouquetActivite>();
        boolean jAiOuvert=false;
        if(con==null) {
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            jAiOuvert=true;
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from activite_bouquet where id_bouquet=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getIdBouquet());
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new BouquetActivite(this, (Activite) BddObject.findById(con, Activite.class, resultSet.getString("id_activite"), Constante.getUser(), Constante.getMdp(), Constante.getDatabase()), resultSet.getDate("date_creation")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            preparedStatement.close();
            if(jAiOuvert) {
                con.close();
            }
        }
        return result.toArray();
    }

    public BouquetActivite[] getListeBouquetActivite() {
        return listeBouquetActivite;
    }

    public void setListeBouquetActivite(BouquetActivite[] listeBouquetActivite)
    throws Exception {
        if(listeBouquetActivite==null) {
            throw new Exception("Veuillez entrer une liste de bouquet activite");
        }
        for(int i=0; i<listeBouquetActivite.length; i++) {
            if(listeBouquetActivite[i].getDateBouquetActivite().compareTo(this.getDateCreation())<=0) {
                throw new Exception("Date d'ajout de bouquet activite invalide");
            }
        }
        this.listeBouquetActivite = listeBouquetActivite;
    }
}
