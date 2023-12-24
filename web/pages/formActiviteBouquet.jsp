<%@page import="model.bouquet.*"%>
<%@page import="model.activite.*"%>
<%
    Object[] allBouquet=(Object[]) request.getAttribute("allBouquet");
    Object[] allActivite=(Object[]) request.getAttribute("allActivite");
%>

<section class="ftco-section contact-section ftco-no-pt">
    <div class="container">
        <div class="row block-9">
            <h2>
                Bouquet activite
            </h2>
            <div class="col-md-12 order-md-last d-flex">
                <form action="traitementActiviteBouquet.htm" class="bg-light p-5 contact-form" method="post">
                    <div class="form-group">
                        <select name="id_bouquet" id="id_bouquet" class="form-control">
                            <option value="">
                                Choisissez un bouquet
                            </option>
                            <%
                                for(int i=0; i<allBouquet.length; i++) {
                                    Bouquet bouquet=(Bouquet) allBouquet[i];
                                    %>
                                    <option value=<% out.println(bouquet.getIdBouquet()); %>>
                                        <% out.println(bouquet.getNomBouquet()); %>
                                    </option>
                                <% }
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <select name="id_activite" id="id_activite" class="form-control">
                            <option value="">
                                Choisissez une activite
                            </option>
                            <%
                                for(int i=0; i<allActivite.length; i++) {
                                    Activite activite=(Activite) allActivite[i];
                                    %>
                                    <option value=<% out.println(activite.getIdActivite()); %>>
                                        <% out.println(activite.getNomActivite()); %>
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