package controller.duree;

import java.sql.Connection;

import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import database.ConnexionBdd;
import model.database.Constante;
import model.duree.TypeDuree;
import url.ModelView;

public class TypeDuree_Controller {
    @Url(link = "formTypeDuree.htm")
    public ModelView getFormTypeDuree()
    throws Exception {
        ModelView result=new ModelView("web/static/header.jsp", "web/pages/formTypeDuree.jsp", "web/static/footer.jsp");
        return result;
    }

    @Url(link = "traitementNewTypeDuree.htm")
    @Parameters(args = {"nom_type_duree", "debut_duree", "fin_duree"})
    public ModelView traitementNewTypeDuree(String nomTypeDuree, int debutDuree, int finDuree)
    throws Exception {
        ModelView result=new ModelView();
        Connection con=ConnexionBdd.connexionPostgress(Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
        try {
            TypeDuree typeDuree=new TypeDuree(nomTypeDuree, debutDuree, finDuree);
            BddObject.insert(con, typeDuree, Constante.getUser(), Constante.getMdp(), Constante.getDatabase());
            result.setUrlRedirect("formTypeDuree.htm");
        } catch (Exception e) {
            throw e;
        } finally {
            con.close();
        }
        return result;
    }
}
