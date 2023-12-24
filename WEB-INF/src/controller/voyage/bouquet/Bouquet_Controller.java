package controller.voyage.bouquet;

import java.sql.Date;

import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import model.bouquet.Bouquet;
import model.database.Constante;
import url.ModelView;

public class Bouquet_Controller {
    @Url(link = "newBouquet.htm")
    public ModelView formBouquet()
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/formBouquet.jsp", "web/static/footer.jsp");
        return result;
    }

    @Url(link = "traitementNewBouquet.htm")
    @Parameters(args = {"nom_bouquet", "date_creation"})
    public ModelView newBouquet(String nom_bouquet, Date dateCreation)
    throws Exception {
        ModelView result=new ModelView();
        result.setUrlRedirect("listeActivite.htm");
        Bouquet bouquet=new Bouquet(nom_bouquet, nom_bouquet, dateCreation);
        BddObject.insert(null, bouquet, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        return result;
    }

    @Url(link = "allBouquet.htm")
    public ModelView getAllBouquet()
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/listeBouquet.jsp", "web/static/footer.jsp");
        result.addItem("allBouquet", BddObject.selectAllFromBdd(null, Bouquet.class, Constante.getUser(), Constante.getMdp(), Constante.getDatabase()));
        return result;
    }
}
