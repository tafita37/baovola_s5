package model.biais.mouvement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnexionBdd;
import model.biais.Biais;
import model.database.Constante;

public class BiaisMouvement {
    Biais biais;
    double entree;
    double sortie;
    Date dateMouvement;
    public BiaisMouvement(Biais biais, double entree, double sortie, Date dateMouvement) {
        this.biais = biais;
        this.entree = entree;
        this.sortie = sortie;
        this.dateMouvement = dateMouvement;
    }
    public BiaisMouvement() {
    }
    public Biais getBiais() {
        return biais;
    }
    public void setBiais(Biais biais) {
        this.biais = biais;
    }
    public double getEntree() {
        return entree;
    }
    public void setEntree(double entree) {
        this.entree = entree;
    }
    public double getSortie() {
        return sortie;
    }
    public void setSortie(double sortie) {
        this.sortie = sortie;
    }
    public Date getDateMouvement() {
        return dateMouvement;
    }
    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public void newMouvement(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        String query = "INSERT INTO biais_mouvement (id_biais, entree, sortie, date_mouvement) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.valueOf(this.getBiais().getIdBiais()));
            preparedStatement.setDouble(2, this.getEntree());
            preparedStatement.setDouble(3, this.getSortie());
            preparedStatement.setDate(4, this.getDateMouvement());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public static Biais getResteBiaisEnStockByActivite(Connection con, String idActivite)
    throws Exception {
        Biais result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from v_reste_biais_mouvement where id_activite=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, idActivite);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=new Biais(con, resultSet.getString("id_biais"), resultSet.getString("id_activite"), resultSet.getDate("date_biais"), resultSet.getDouble("reste_stock"));
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

    // public BiaisMouvement[] getListeMouvement(Connection con, String idActivite)
    // throws Exception {
    //     BiaisMouvement[] result=null;
    //     boolean jAiOuvert=false;
    //     if(con==null) {
    //         jAiOuvert=true;
    //         con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
    //     }
    //     PreparedStatement preparedStatement=null;
    //     ResultSet resultSet=null;
    //     try {
    //         String sql="select*from v_reste_biais_mouvement where id_activite=?";
    //         preparedStatement=con.prepareStatement(sql);
    //         preparedStatement.setString(1, idActivite);
    //         resultSet=preparedStatement.executeQuery();
    //         while(resultSet.next()) {
    //             result=new Biais(con, resultSet.getString("id_biais"), resultSet.getString("id_activite"), resultSet.getDate("date_biais"), resultSet.getDouble("reste_stock"));
    //         }
    //     } catch (Exception e) {
    //         throw e;
    //     } finally {
    //         if(jAiOuvert) {
    //             con.close();
    //         }
    //     }
    //     return result;
    // }
}
