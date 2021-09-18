
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>

        <div class="container">
            <h2>LIST STAFFS</h2>
            <p>
                <a class="btn btn-primary" href="InforServlet?action=AddOrEdit">ADD STAFF</a>
            </p>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Photo</th>
                        <th>ID Staff</th>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${LIST_INFOR}">
                        <tr>
                            <td width="100"><img src="uploads/${item.photo}" width="80" height="70" /></td>
                    
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>
						<a class="btn btn-primary btn-sm"href="InforServlet?action=AddOrEdit&id=${item.id}">Edit</a> | 
						<a class="btn btn-danger btn-sm" href="InforServlet?action=delete&id=${item.id}">Del</a></td>
                        </tr>
                        
                    </c:forEach>
					
                    
                    
                </tbody>
            </table>
        </div>
    </body>
</html>
