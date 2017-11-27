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
                <th>Employees</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="company" items="${companies}">
                <tr>
                    <td>${company.companyID}</td>
                    <td>${company.companyName}</td>
                    <td>
                        <ul>
                        <c:forEach var="user" items="${company.userList}">
                            <li>${user.firstname}</li>
                        </c:forEach>
                        </ul>
                    </td>
                    <td>
                        <form action="company" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedCompanyName" value="${company.companyID}">
                        </form>
                    </td>
                    <td>
                        <form action="company" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedCompanyName" value="${company.companyID}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedCompany == null}">
            <h3>Add Company</h3>
            <form action="company" method="POST">
                Company Name: <input type="text" name="companyname"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedCompany != null}">
            <h3>Edit Company</h3>
            <form action="company" method="POST">
                Company Id <input type="text" name="id" value="${selectedCompany.companyID}" readonly><br>
                Company Name: <input type="text" name="companyname" value="${selectedNote.companyName}"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
            
            
            <hr>
            
            
    </body>
</html>
