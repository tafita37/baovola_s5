<%@page import="model.bouquet.*"%>
<%
    Object[] allBouquet=(Object[]) request.getAttribute("allBouquet");
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
    <table border="1">
        <tr>
            <th>
                Bouquet
            </th>
            <th>
                Activite
            </th>
        </tr>
        <%
            for(int i=0; i<allBouquet.length; i++) {
                Bouquet bouquet=(Bouquet) allBouquet[i];
                %>
                <tr>
                    <td>
                        <% 
                            out.println(bouquet.getNomBouquet());
                        %>
                    </td>
                    <td>
                        <a href=<% out.println("listeActivite.htm?id_bouquet="+bouquet.getIdBouquet()); %>>
                            Liste des activites
                        </a>
                    </td>
                </tr>
            <% }
        %>
    </table>
</body>
</html>