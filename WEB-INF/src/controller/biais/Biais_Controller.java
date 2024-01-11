package controller.biais;

import java.sql.Connection;
import java.sql.Date;

import annotation.DefaultParameter;
import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import model.database.ConnexionBdd;
import model.database.Constante;
import model.biais.Biais;
import model.biais.mouvement.BiaisMouvement;
import model.voyage.Voyage;
import model.activite.Activite;
import model.activite.ActivitePrix;
import model.bouquet.Bouquet;
import model.bouquet.BouquetActivite;
import url.ModelView;

public class Biais_Controller {
    @Url(link = "newBiais.htm")
    public ModelView formBiais()
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/formBiais.jsp", "web/static/footer.jsp");
        result.addItem("allActivite", BddObject.selectAllFromBdd(null, Activite.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
        return result;
    }


    @Url(link = "traitementNewBiais.htm")
    @Parameters(args = {"id_activite", "date_creation"})
    public ModelView newBiais(String id_activite, Date date_creation) 
    throws Exception {
        ModelView result=new ModelView();
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        try {
            Biais biais=new Biais(con, id_activite, date_creation);
            biais.newBiais(con);
            result.setUrlRedirect("listeActivite.htm");
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    @Url(link = "newAchatBiais.htm")
    public ModelView formAchatBiais()
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/achatBiais.jsp", "web/static/footer.jsp");
        result.addItem("allBiais", Biais.getAllBiais(null));
        return result;
    }

    
    @Url(link = "traitementAchatBiais.htm")
    @Parameters(args = {"id_biais", "nombre", "date_creation"})
    public ModelView newAchatBiais(String id_biais, int nombre, Date date_creation) 
    throws Exception {
        ModelView result=new ModelView();
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        try {
            BiaisMouvement biaisMouvement=new BiaisMouvement(Biais.findBiaisById(con, id_biais), nombre, 0, date_creation);
            biaisMouvement.newMouvement(con);
            result.setUrlRedirect("newAchatBiais.htm");   
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }

    

    @Url(link = "listeBiais.htm")
    @Parameters(args = {"id_activite"})
    public ModelView getListeBiais( @DefaultParameter(defaultValue = "ACT000001") String idActivite)
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/listeBiais.jsp", "web/static/footer.jsp");
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        try {
            result.addItem("allActivite", BddObject.selectAllFromBdd(con, Activite.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
            result.addItem("biais", BiaisMouvement.getResteBiaisEnStockByActivite(con, idActivite));
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }
}
