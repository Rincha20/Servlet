<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Dept"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Dept> listDept = (ArrayList<Dept>) request.getAttribute("listDept");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Liste des departements</h1>
        <% for(int i=0; i<listDept.size(); i++){ %>
            <p>
                Nom du departement: <% out.print(listDept.get(i).getNomDept()); %>
                Le manager: <% out.print(listDept.get(i).getManager()); %>
            </p>
        <% } %>
</body>
</html>