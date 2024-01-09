package model.voyage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdd.BddObject;
import database.ConnexionBdd;
import model.activite.Activite;
import model.database.Constante;
import model.duree.TypeDuree;

public class VoyageActivite {
    Voyage voyage;
    TypeDuree typeDuree;
    Activite activite;
    int nbActivite;
    public VoyageActivite(Voyage voyage, TypeDuree typeDuree, Activite activite, int nbActivite)
    throws Exception {
        this.setVoyage(voyage);
        this.setTypeDuree(typeDuree);
        this.setActivite(activite);
        this.setNbActivite(nbActivite);
    }

    public VoyageActivite(Connection con, String idVoyage, String idActivite, String idTypeDuree, int nbActivite) 
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        try {
            this.setVoyage(Voyage.findVoyageById(con, idVoyage));
            this.setActivite((Activite) BddObject.findById(con, Activite.class, idActivite, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            this.setTypeDuree((TypeDuree) BddObject.findById(con, TypeDuree.class, idTypeDuree, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            this.setNbActivite(nbActivite);
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public VoyageActivite() {
    }
    public Voyage getVoyage() {
        return voyage;
    }
    public void setVoyage(Voyage voyage)
    throws Exception {
        if(voyage==null) {
            throw new Exception("Veuillez entrer un voyage");
        }
        this.voyage = voyage;
    }
    public TypeDuree getTypeDuree() {
        return typeDuree;
    }
    public void setTypeDuree(TypeDuree typeDuree)
    throws Exception {
        if(typeDuree==null) {
            throw new Exception("Veuillez entrer un type de duree");
        }
        this.typeDuree = typeDuree;
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
    public int getNbActivite() {
        return nbActivite;
    }
    public void setNbActivite(int nbActivite)
    throws Exception {
        if(nbActivite<=0) {
            throw new Exception("Veuillez entrer un nombre d'activite plus grand");
        }
        this.nbActivite = nbActivite;
    }
    public void insertNewVoyageActivite(Connection con)
    throws Exception {
        String query = "INSERT INTO voyage_activite (id_voyage, id_activite, nb_activite, id_type_duree) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.valueOf(this.getVoyage().getIdVoyage()));
            preparedStatement.setString(2, this.getActivite().getIdActivite());
            preparedStatement.setInt(3, this.getNbActivite());
            preparedStatement.setString(4, this.getTypeDuree().getIdTypeDuree());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static VoyageActivite[] findVoyageByPrix(Connection con, double prix_min, double prix_max) 
    throws Exception {
        List<VoyageActivite> voyagesActivite = new ArrayList<>();
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        String query = "SELECT * FROM v_voyage_activite WHERE prix_total >= ? and prix_total <= ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setDouble(1, prix_min);
            preparedStatement.setDouble(2, prix_max);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String idVoyage = resultSet.getString("id_voyage");
                    String typeDuree = resultSet.getString("id_type_duree");
                    int nbActivite=resultSet.getInt("nb_activite");

                    Voyage voyage = Voyage.findVoyageById(con, idVoyage);
                    VoyageActivite voyageActivite=new VoyageActivite(voyage, (TypeDuree) BddObject.findById(con, TypeDuree.class, typeDuree, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()), (Activite) BddObject.findById(con, Activite.class, resultSet.getString("id_activite"), Constante.getUser(), Constante.getMdp(), Constante.getDatabase()), nbActivite);
                    voyagesActivite.add(voyageActivite);
                }
                resultSet.close();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw e;  
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }

        return voyagesActivite.toArray(new VoyageActivite[0]);
    }


}
