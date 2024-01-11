package model.activite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import database.ConnexionBdd;
import model.database.Constante;

public class ActivitePrix {
    Activite activite;
    double prixActivite;
    Date datePrixActivite;
    public ActivitePrix(Activite activite, double prixActivite, Date datePrixActivite) {
        this.activite = activite;
        this.prixActivite = prixActivite;
        this.datePrixActivite = datePrixActivite;
    }
    public ActivitePrix() {
    }
    public Activite getActivite() {
        return activite;
    }
    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    public double getPrixActivite() {
        return prixActivite;
    }
    public void setPrixActivite(double prixActivite) {
        this.prixActivite = prixActivite;
    }
    public Date getDatePrixActivite() {
        return datePrixActivite;
    }
    public void setDatePrixActivite(Date datePrixActivite) {
        this.datePrixActivite = datePrixActivite;
    }

    public void newActivitePrix(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        PreparedStatement preparedStatement=null;
        try {
            String sql="insert into activite_prix values(?, ?, ?)";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getActivite().getIdActivite());
            preparedStatement.setDouble(2, this.getPrixActivite());
            preparedStatement.setDate(3, this.getDatePrixActivite());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }
}
