<%@page import="model.bouquet.*"%>
<%@page import="model.lieu.*"%>
<%
    Object[] listeBouquet=(Object[]) request.getAttribute("allBouquet");
    Object[] listeCategorieLieu=(Object[]) request.getAttribute("allCategorieLieu");
%>
<section class="ftco-section contact-section ftco-no-pt">
    <div class="container">
        <div class="row block-9">
            <h2>
                Nouveau voyage
            </h2>
            <div class="col-md-12 order-md-last d-flex">
                <form action="traitementVoyage.htm" class="bg-light p-5 contact-form" method="post">
                    
                    <div class="form-group">
                        <select name="bouquet" id="bouquet" class="form-control">
                            <option value="">
                                Choisissez un bouquet
                            </option>
                            <%
                                for(int i=0; i<listeBouquet.length; i++) {
                                    Bouquet bouquet=(Bouquet) listeBouquet[i];
                                    %>
                                    <option value=<% out.println(bouquet.getIdBouquet()); %>>
                                        <% out.println(bouquet.getNomBouquet()); %>
                                    </option>
                                <% }
                            %>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <select name="categorie" id="categorie" class="form-control">
                            <option value="">
                                Categorie de lieu
                            </option>
                            <%
                                for(int i=0; i<listeCategorieLieu.length; i++) {
                                    CategorieLieu categorieLieu=(CategorieLieu) listeCategorieLieu[i];
                                    %>
                                    <option value=<% out.println(categorieLieu.getIdCategorieLieu()); %>>
                                        <% out.println(categorieLieu.getNomCategorieLieu()); %>
                                    </option>
                                <% }
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="date" class="form-control" placeholder="Date de creation" name="date_creation">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Inserer" class="btn btn-primary py-3 px-5">
                    </div>
                </form>

            </div>
        </div>
    </div>
</section>