package controller.voyage.lieu;

import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import model.database.Constante;
import model.lieu.CategorieLieu;
import url.ModelView;

public class Lieu_Controller {
    @Url(link = "newCategorie.htm")
    public ModelView newCategorie()
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/formCategorieLieu.jsp", "web/static/footer.jsp");
        return result;
    }

    @Url(link = "traitementCategorie.htm")
    @Parameters(args = {"nom_categorie_lieu"})
    public Object newCategorie(String nom_categorie_lieu)
    throws Exception {
        ModelView result=new ModelView();
        try {
            CategorieLieu categorieLieu=new CategorieLieu(nom_categorie_lieu);
            BddObject.insert(null, categorieLieu, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            result.setUrlRedirect("newCategorie.htm");
        } catch (Exception e) {
            return e.getMessage();
        }
        return result;
    }
}
