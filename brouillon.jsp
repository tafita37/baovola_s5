<%@page import="model.bouquet.*"%>
<%
    Object[] listeBouquet=(Object[]) request.getAttribute("allBouquet");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h2>
        Insertion de voyage
    </h2>
    <form action="" method="post">
        <label for="duree">
            Duree : 
        </label>
        <input type="number" name="duree" id="duree">
        <br>
        <label for="bouquet">
            Bouquet : 
        </label>
        <select name="bouquet" id="bouquet">
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
        <br>
        <label for="prix_personne">
            Prix par personne : 
        </label>
        <input type="number" name="prix_personne" id="prix_personne">
        <br>
        <label for="categorie_lieu">
            Categorie de lieu : 
        </label>
        <select name="categorie_lieu" id="">
            <option value="">
                Choisissez une categorie
            </option>
        </select>
        <br>
        <label for="date_creation">
            Date : 
        </label>
        <input type="date" name="date_creation" id="date_creation">
        <br>
        <input type="submit" value="Valider">
    </form>
    <a href="newBouquet.htm">
        Nouveau bouquet
    </a>
    <br>
    <a href="newActivite.htm">
        Nouvelle Activite
    </a>
    <br>
    <a href="formActiviteBouquet.htm">
        Activite voyage
    </a>
    <br>
    <a href="allBouquet.htm">
        Liste des bouquets
    </a>
</body>
</html>