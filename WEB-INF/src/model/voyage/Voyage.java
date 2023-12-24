package model.voyage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdd.BddObject;
import model.database.ConnexionBdd;
import model.database.Constante;
import model.duree.TypeDuree;
import model.lieu.CategorieLieu;
import model.activite.Activite;
import model.bouquet.Bouquet;

public class Voyage {
    private String idVoyage;
    private CategorieLieu categorieLieu;
    private Bouquet bouquet;

    public Voyage(String idVoyage, CategorieLieu categorieLieu, Bouquet bouquet)
    throws Exception {
        this.setIdVoyage(idVoyage);
        this.setCategorieLieu(categorieLieu);
        this.setBouquet(bouquet);
    }

    public Voyage() {
    }

    public Voyage(Connection con, String categorieLieu, String bouquet)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        try {
            this.setCategorieLieu((CategorieLieu) BddObject.findById(con, CategorieLieu.class, categorieLieu, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            this.setBouquet((Bouquet) BddObject.findById(con, Bouquet.class, bouquet, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public static Voyage findVoyageById(Connection con, String idVoyage)
    throws Exception {
        Voyage result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        String query = "SELECT * from voyage where id_voyage=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.valueOf(idVoyage));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result=new Voyage(resultSet.getString("id_voyage"), (CategorieLieu) BddObject.findById(con, CategorieLieu.class, resultSet.getString("id_categorie_lieu"), Constante.getUser(), Constante.getMdp(), Constante.getDatabase()), (Bouquet) BddObject.findById(con, Bouquet.class, resultSet.getString("id_bouquet"), Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
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
        return result;
    }

    public static VoyageActivite[] findVoyageByIdActivite(Connection con, String idActivite) 
    throws Exception {
        List<VoyageActivite> voyagesActivite = new ArrayList<>();
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        String query = "SELECT * FROM voyage_activite WHERE id_activite = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, idActivite);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String idVoyage = resultSet.getString("id_voyage");
                    String typeDuree = resultSet.getString("id_type_duree");
                    int nbActivite=resultSet.getInt("nb_activite");

                    Voyage voyage = Voyage.findVoyageById(con, idVoyage);
                    VoyageActivite voyageActivite=new VoyageActivite(voyage, (TypeDuree) BddObject.findById(con, TypeDuree.class, typeDuree, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()), (Activite) BddObject.findById(con, Activite.class, idActivite, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()), nbActivite);
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

    public static List<Voyage> getAllVoyage(Connection con) throws Exception {
        List<Voyage> voyages = new ArrayList<>();
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        String query = "SELECT * from voyage";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String idVoyage = resultSet.getString("id_voyage");
                    String id_categorie_lieu = resultSet.getString("id_categorie_lieu");
                    String id_bouquet = resultSet.getString("id_bouquet");
                    Voyage voyage=new Voyage(idVoyage, (CategorieLieu) BddObject.findById(con, CategorieLieu.class, id_categorie_lieu, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()), (Bouquet) BddObject.findById(con, Bouquet.class, id_bouquet, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
                    voyages.add(voyage);
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

        return voyages;
    }


    public String getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(String idVoyage)
    throws Exception {
        if(idVoyage==null||idVoyage.length()==0) {
            throw new Exception("Veuillez entrer un id de voyage");
        }
        this.idVoyage = idVoyage;
    }

    public CategorieLieu getCategorieLieu() {
        return categorieLieu;
    }

    public void setCategorieLieu(CategorieLieu categorieLieu)
    throws Exception {
        if(categorieLieu==null) {
            throw new Exception("Veuillez entrer une categorie de lieu");
        }
        this.categorieLieu = categorieLieu;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet)
    throws Exception {
        if(bouquet==null) {
            throw new Exception("Veuillez entrer une bouquet");
        }
        this.bouquet = bouquet;
    }

   

    public void insert(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        }
        String query = "INSERT INTO voyage (id_categorie_lieu, id_bouquet) VALUES (?, ?)";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, this.getCategorieLieu().getIdCategorieLieu());
            preparedStatement.setString(2, this.getBouquet().getIdBouquet());
            

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

    public String getNomVoyage() {
        return "Voyage "+this.getBouquet().getNomBouquet()+" "+this.getCategorieLieu().getNomCategorieLieu();
    }

    
}
