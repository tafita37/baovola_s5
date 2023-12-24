package model.lieu;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;

@Table(tableName="categorie_lieu", idTable = "id_categorie_lieu", prefixe = "CATL", sequence = "id_categorie_lieu", nbNumerique = 6)
public class CategorieLieu {
    @PrimaryKey
    @FieldMapping(columnName = "id_categorie_lieu")
    String idCategorieLieu;
    @FieldMapping(columnName = "nom_categorie_lieu")
    String nomCategorieLieu;
    public CategorieLieu(String nomCategorieLieu)
    throws Exception {
        this.setNomCategorieLieu(nomCategorieLieu);
    }
    public CategorieLieu(String idCategorieLieu, String nomCategorieLieu)
    throws Exception {
        this.setIdCategorieLieu(idCategorieLieu);
        this.setNomCategorieLieu(nomCategorieLieu);
    }
    public CategorieLieu() {
    }
    public String getIdCategorieLieu() {
        return idCategorieLieu;
    }
    public void setIdCategorieLieu(String idCategorieLieu)
    throws Exception {
        if(idCategorieLieu==null||idCategorieLieu.length()==0) {
            throw new Exception("Veuillez entrer une id de categorie");
        }
        this.idCategorieLieu = idCategorieLieu;
    }
    public String getNomCategorieLieu() {
        return nomCategorieLieu;
    }
    public void setNomCategorieLieu(String nomCategorieLieu)
    throws Exception {
        if(nomCategorieLieu==null||nomCategorieLieu.length()==0) {
            throw new Exception("Veuillez entrer un nom de categorie");
        }
        this.nomCategorieLieu = nomCategorieLieu;
    }
}
