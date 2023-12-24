package model.activite;

import java.sql.Date;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;

@Table(tableName="activite", idTable = "id_activite", prefixe = "ACT", sequence = "id_activite", nbNumerique = 6)
public class Activite {
    @PrimaryKey
    @FieldMapping(columnName = "id_activite")
    String idActivite;
    @FieldMapping(columnName = "nom_activite")
    String nomActivite;
    @FieldMapping(columnName = "date_creation")
    Date dateCreation;

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
}
