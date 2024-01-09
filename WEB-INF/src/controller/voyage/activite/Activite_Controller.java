package controller.voyage.activite;

import java.sql.Connection;
import java.sql.Date;

import annotation.DefaultParameter;
import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import model.database.ConnexionBdd;
import model.database.Constante;
import model.activite.Activite;
import model.bouquet.Bouquet;
import model.bouquet.BouquetActivite;
import url.ModelView;

public class Activite_Controller {
    @Url(link = "newActivite.htm")
    public ModelView formActivite()
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/formActivite.jsp", "web/static/footer.jsp");
        return result;
    }

    @Url(link = "formActiviteBouquet.htm")
    public ModelView formActiviteBouquet()
    throws Exception {
        Connection con=null;
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/formActiviteBouquet.jsp", "web/static/footer.jsp");
        try {
            con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            result.addItem("allBouquet", BddObject.selectAllFromBdd(con, Bouquet.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            result.addItem("allActivite", BddObject.selectAllFromBdd(con, Activite.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "traitementNewActivite.htm")
    @Parameters(args = {"nom_activite", "prix_activite", "date_creation"})
    public ModelView newActivite(String nom_activite, double prix_activite, Date date_creation) 
    throws Exception {
        ModelView result=new ModelView();
        Activite activite=new Activite(nom_activite, prix_activite, date_creation);
        BddObject.insert(null, activite, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        result.setUrlRedirect("listeActivite.htm");
        return result;
    }

    @Url(link = "traitementActiviteBouquet.htm")
    @Parameters(args = {"id_bouquet", "id_activite", "date_creation"})
    public Object newActiviteBouquet(String id_bouquet, String id_activite, Date date_creation)
    throws Exception {
        ModelView result=new ModelView();
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        try {
            BouquetActivite bouquetActivite=new BouquetActivite(con, id_bouquet, id_activite, date_creation);
            bouquetActivite.newBouquetActivite(con);
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            con.close();
        }
        result.setUrlRedirect("listeActivite.htm");
        return result;
    }

    @Url(link = "listeActivite.htm")
    @Parameters(args = {"id_bouquet"})
    public ModelView getListeActivite( @DefaultParameter(defaultValue = "BOUQ000001") String idBouquet)
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/listeActivite.jsp", "web/static/footer.jsp");
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        try {
            Bouquet bouquet = (Bouquet) BddObject.findById(con, Bouquet.class, idBouquet, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            if(bouquet!=null) {
                bouquet.setListeBouquetActivite(bouquet.getListeBouquetActivite(con));
            }
            result.addItem("bouquet", bouquet);
            result.addItem("allBouquet", BddObject.selectAllFromBdd(con, Bouquet.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }
}
