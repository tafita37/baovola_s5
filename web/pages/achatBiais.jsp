<%@page import="model.voyage.*"%>
<%@page import="model.biais.*"%>
<%@page import="model.activite.*"%>
<%@page import="model.duree.*"%>
<%@page import="java.util.*"%>
<%
    List<Voyage>  listeVoyage=(List<Voyage>) request.getAttribute("allVoyage");
    Biais[] listeBiais=(Biais[]) request.getAttribute("allBiais");
    Object[] listeTypeDuree=(Object[]) request.getAttribute("allTypeDuree");
%>

<section class="ftco-section contact-section ftco-no-pt">
    <div class="container">
        <div class="row block-9">
            <h2>
                Nouvel achat de billet
            </h2>
            <div class="col-md-12 order-md-last d-flex">
                <form action="traitementAchatBiais.htm" class="bg-light p-5 contact-form" method="post">
                    <div class="form-group">
                        <select name="id_biais" id="" class="form-control">
                            <option value="">
                                Billet
                            </option>
                            <%
                                for(int i=0; i<listeBiais.length; i++) {
                                    %>
                                    <option value=<% out.println(listeBiais[i].getIdBiais()); %>>
                                        <% out.println(listeBiais[i].getActivite().getNomActivite()); %>
                                    </option>
                                <% }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="Nombre" name="nombre">
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