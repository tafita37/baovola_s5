package model.biais;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bdd.BddObject;
import database.ConnexionBdd;
import model.activite.Activite;
import model.database.Constante;

public class Biais {
    String idBiais;
    Activite activite;
    Date dateBiais;
    double resteStock;

    public Biais(Activite activite, Date dateBiais) {
        this.activite = activite;
        this.dateBiais = dateBiais;
    }

    public Biais(Connection con, String idActivite, Date dateBiais)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        try {
            this.activite = (Activite) BddObject.findById(con, Activite.class, idActivite, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            this.dateBiais = dateBiais;
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Biais(String idBiais, Activite activite, Date dateBiais, double resteStock) {
        this.idBiais = idBiais;
        this.activite = activite;
        this.dateBiais = dateBiais;
        this.resteStock = resteStock;
    }

    public Biais(Connection con, String idBiais, String idActivite, Date dateBiais)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        try {
            this.idBiais = idBiais;
            this.activite = (Activite) BddObject.findById(con, Activite.class, idActivite, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            this.dateBiais = dateBiais;
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Biais(Connection con, String idBiais, String idActivite, Date dateBiais, double resteStock)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        try {
            this.idBiais = idBiais;
            this.activite = (Activite) BddObject.findById(con, Activite.class, idActivite, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            this.dateBiais = dateBiais;
            this.resteStock = resteStock;  
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Biais(String idBiais, Activite activite, Date dateBiais) {
        this.idBiais = idBiais;
        this.activite = activite;
        this.dateBiais = dateBiais;
    }
    public Biais() {
    }
    public String getIdBiais() {
        return idBiais;
    }
    public void setIdBiais(String idBiais) {
        this.idBiais = idBiais;
    }
    public Activite getActivite() {
        return activite;
    }
    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    public Date getDateBiais() {
        return dateBiais;
    }
    public void setDateBiais(Date dateBiais) {
        this.dateBiais = dateBiais;
    }

    public void newBiais(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        String query = "INSERT INTO biais (id_activite, date_biais) VALUES (?, ?)";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, this.getActivite().getIdActivite());
            preparedStatement.setDate(2, this.getDateBiais());
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
    public double getResteStock() {
        return resteStock;
    }
    public void setResteStock(double resteStock) {
        this.resteStock = resteStock;
    }

    public static int countAllBiais(Connection con) 
    throws Exception {
        int result=0;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select count(*) as nb from biais";
            preparedStatement=con.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=resultSet.getInt("nb");
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

    public static Biais[] getAllBiais(Connection con)
    throws Exception {
        Biais[] result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            result=new Biais[Biais.countAllBiais(con)];
            String sql="select*from biais";
            preparedStatement=con.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            int i=0;
            while(resultSet.next()) {
                result[i]=new Biais(con, resultSet.getString("id_biais"), resultSet.getString("id_activite"), resultSet.getDate("date_biais"));
                i++;
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

    public static Biais findBiaisById(Connection con, String idBiais)
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
            String sql="select*from biais where id_biais=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(idBiais));
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=new Biais(con, resultSet.getString("id_biais"), resultSet.getString("id_activite"), resultSet.getDate("date_biais"));
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
}
