<%@ include file="/WEB-INF/header.jsp" %>
    <body>
        <a href="admin?admin">Manage Users</a>
        <h1>Manage Companies</h1>
        <hr>
        <h2>Companies</h2>
        <p>${errorMessage}</p>
        <table>
            <tr>
                <th>Company ID</th>
                <th>Company Name</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="company" items="${companies}">
                <tr>
                    <td>${company.CompanyID}</td>
                    <td>${company.CompanyName}</td>
                    <td>
                        <ul>
                        <c:forEach var="user" items="${company.userList}">
                            <li>${user.firstname}</li>
                        </c:forEach>
                        </ul>
                    </td>
                    <td>
                        <form action="admin" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedCompanyName" value="${company.CompanyName}">
                        </form>
                    </td>
                    <td>
                        <form action="admin" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedCompanyName" value="${company.CompanyName}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedCompany == null}">
            <h3>Add Company</h3>
            <form action="admin" method="POST">
                Company Name: <input type="text" name="companyname"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedCompany != null}">
            <h3>Edit Company</h3>
            <form action="admin" method="POST">
                Company Name: <input type="text" name="username" value="${selectedCompany.CompanyName}" readonly><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
            
            
            <hr>
            
            
    </body>
</html>
