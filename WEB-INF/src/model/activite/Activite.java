package model.activite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;
import database.ConnexionBdd;
import model.database.Constante;

@Table(tableName="activite", idTable = "id_activite", prefixe = "ACT", sequence = "id_activite", nbNumerique = 6)
public class Activite {
    @PrimaryKey
    @FieldMapping(columnName = "id_activite")
    String idActivite;
    @FieldMapping(columnName = "nom_activite")
    String nomActivite;
    @FieldMapping(columnName = "date_creation")
    Date dateCreation;
    ActivitePrix dernierPrix;

    public Activite(String nomActivite, Date dateCreation)
    throws Exception {
        this.setNomActivite(nomActivite);
        this.setDateCreation(dateCreation);
    }

    public Activite(String idActivite, String nomActivite, Date dateCreation)
    throws Exception {
        this.setIdActivite(idActivite);
        this.setNomActivite(nomActivite);
        this.setDateCreation(dateCreation);
    }

    public Activite() {
    }

    public void newActivite() {
        
    }

    public String getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(String idActivite)
    throws Exception {
        if(idActivite==null||idActivite.length()==0) {
            throw new Exception("Veuillez entrer une id de activite");
        }
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite)
    throws Exception {
        if(nomActivite==null||nomActivite.length()==0) {
            throw new Exception("Veuillez entrer un nom de activite");
        }
        this.nomActivite = nomActivite;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation)
    throws Exception {
        if(dateCreation==null) {
            throw new Exception("Veuillez entrer une date de creation d'activite");
        }
        this.dateCreation = dateCreation;
    }

    public ActivitePrix getLastPrix(Connection con)
    throws Exception {
        ActivitePrix result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from v_last_activite_prix where id_activite=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getIdActivite());
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=new ActivitePrix(this, resultSet.getDouble("prix_activite"), resultSet.getDate("date_prix_activite"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public ActivitePrix getDernierPrix() {
        return dernierPrix;
    }

    public void setDernierPrix(ActivitePrix dernierPrix) {
        this.dernierPrix = dernierPrix;
    }
}
