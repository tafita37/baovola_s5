package model.duree;

import java.sql.Connection;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;



@Table(tableName="type_duree", idTable = "id_type_duree", prefixe = "TYPD", sequence = "id_type_duree", nbNumerique = 6)
public class TypeDuree {
    @PrimaryKey
    @FieldMapping(columnName = "id_type_duree")
    String idTypeDuree;
    @FieldMapping(columnName = "nom_type_duree")
    String nomTypeDuree;
    @FieldMapping(columnName = "debut_duree")
    int debut;
    @FieldMapping(columnName = "fin_duree")
    int fin;

    public TypeDuree(String idTypeDuree, String nomTypeDuree, int debut, int fin)
    throws Exception {
        this.setIdTypeDuree(idTypeDuree);
        this.setNomTypeDuree(nomTypeDuree);
        this.setDebut(debut);
        this.setFin(fin);
    }

    public TypeDuree(String nomTypeDuree, int debut, int fin)
    throws Exception {
        this.setNomTypeDuree(nomTypeDuree);
        this.setDebut(debut);
        this.setFin(fin);
    }

    public TypeDuree() {
    }

    public String getIdTypeDuree() {
        return idTypeDuree;
    }

    public void setIdTypeDuree(String idTypeDuree)
    throws Exception {
        if(idTypeDuree==null||idTypeDuree.length()==0) {
            throw new Exception("Veuillez entrer un id de type de duree");
        }
        this.idTypeDuree = idTypeDuree;
    }

    public String getNomTypeDuree() {
        return nomTypeDuree;
    }

    public void setNomTypeDuree(String nomTypeDuree)
    throws Exception {
        if(nomTypeDuree==null||nomTypeDuree.length()==0) {
            throw new Exception("Veuillez entrer un nom de type de duree");
        }
        this.nomTypeDuree = nomTypeDuree;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut)
    throws Exception {
        if(debut<0) {
            throw new Exception("Veuillez entrer un debut plus grand");
        }
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin)
    throws Exception {
        if(fin<=debut) {
            throw new Exception("Fin invalide");
        }
        this.fin = fin;
    }

    public static void updateTypeDuree(Connection con, String idType, int debut, int fin) {

    }
}

