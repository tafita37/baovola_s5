package controller.voyage;

import java.sql.Connection;

import annotation.DefaultParameter;
import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import database.ConnexionBdd;
import model.activite.Activite;
import model.bouquet.Bouquet;
import model.database.Constante;
import model.duree.TypeDuree;
import model.lieu.CategorieLieu;
import model.voyage.Voyage;
import model.voyage.VoyageActivite;
import url.ModelView;

public class Voyage_Controller {
    @Url(link = "newVoyage.htm")
    public ModelView formVoyage()
    throws Exception {
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/nouveauVoyage.jsp", "web/static/footer.jsp");
        try {   
            result.addItem("allBouquet", BddObject.selectAllFromBdd(con, Bouquet.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            result.addItem("allCategorieLieu", BddObject.selectAllFromBdd(con, CategorieLieu.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "traitementVoyage.htm")
    @Parameters(args = {"bouquet", "categorie"})
    public ModelView traitementVoyage(String idBouquet, String idCategorieLieu)
    throws Exception {
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        ModelView result=new ModelView();
        try {   
            Voyage voyage=new Voyage(con, idCategorieLieu, idBouquet);
            voyage.insert(con);
            result.setUrlRedirect("newVoyage.htm");
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "newVoyageActivite.htm")
    public ModelView newVoyageActivite()
    throws Exception {
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/formVoyageActivite.jsp", "web/static/footer.jsp");
        try {   
            result.addItem("allVoyage", Voyage.getAllVoyage(con));
            result.addItem("allActivite", BddObject.selectAllFromBdd(con, Activite.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            result.addItem("allTypeDuree", BddObject.selectAllFromBdd(con, TypeDuree.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "traitementVoyageActivite.htm")
    @Parameters(args = {"formuleVoyage", "activite", "typeDuree", "nbActivite"})
    public Object traitementVoyageActivite(String idFormuleVoyage, String idActivite, String idTypeDuree, int nbActivite)
    throws Exception {
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        ModelView result=new ModelView();
        try {   
            VoyageActivite voyageActivite=new VoyageActivite(con, idFormuleVoyage, idActivite, idTypeDuree, nbActivite);
            voyageActivite.insertNewVoyageActivite(con);
            result.setUrlRedirect("newVoyageActivite.htm");
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            con.close();
        }
        return result;
    }

    @Parameters(args = {"id_activite"})
    @Url(link = "listeVoyageByActivite.htm")
    public ModelView getListeVoyageByIdActivite(@DefaultParameter(defaultValue = "ACT000001") String idActivite)
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/listeVoyageByActivite.jsp", "web/static/footer.jsp");
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        try {
            result.addItem("allActivite", BddObject.selectAllFromBdd(con, Activite.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            result.addItem("voyage", Voyage.findVoyageByIdActivite(con, idActivite));
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }
}
