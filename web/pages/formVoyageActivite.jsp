<%@page import="model.voyage.*"%>
<%@page import="model.activite.*"%>
<%@page import="model.duree.*"%>
<%@page import="java.util.*"%>
<%
    List<Voyage>  listeVoyage=(List<Voyage>) request.getAttribute("allVoyage");
    Object[] listeActivite=(Object[]) request.getAttribute("allActivite");
    Object[] listeTypeDuree=(Object[]) request.getAttribute("allTypeDuree");
%>
<section class="ftco-section contact-section ftco-no-pt">
    <div class="container">
        <div class="row block-9">
            <h2>
                Nouveau voyage avec activite
            </h2>
            <div class="col-md-12 order-md-last d-flex">
                <form action="traitementVoyageActivite.htm" class="bg-light p-5 contact-form" method="post">
                    
                    <div class="form-group">
                        <select name="formuleVoyage" id="bouquet" class="form-control">
                            <option value="">
                                Choisissez une formule de voyage
                            </option>
                            <%
                                for(int i=0; i<listeVoyage.size(); i++) {
                                    %>
                                    <option value=<% out.println(listeVoyage.get(i).getIdVoyage());%>>
                                        <%
                                            out.println(listeVoyage.get(i).getNomVoyage());
                                        %>
                                    </option>
                                <% }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <select name="activite" id="bouquet" class="form-control">
                            <option value="">
                                Activite
                            </option>
                            <%
                                for(int i=0; i<listeActivite.length; i++) {
                                    Activite activite=(Activite) listeActivite[i];
                                    %>
                                    <option value=<% out.println(activite.getIdActivite()); %>>
                                        <% out.println(activite.getNomActivite()); %>
                                    </option>
                                <% }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <select name="typeDuree" id="bouquet" class="form-control">
                            <option value="">
                                Type de duree
                            </option>
                            <%
                                for(int i=0; i<listeTypeDuree.length; i++) {
                                    TypeDuree typeDuree=(TypeDuree) listeTypeDuree[i];
                                    %>
                                    <option value=<% out.println(typeDuree.getIdTypeDuree()); %>>
                                        <% out.println(typeDuree.getNomTypeDuree()); %>
                                    </option>
                                <% }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="number" name="nbActivite" id="" class="form-control" placeholder="Nombre d'activite" >
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Inserer" class="btn btn-primary py-3 px-5">
                    </div>
                </form>

            </div>
        </div>
    </div>
</section>