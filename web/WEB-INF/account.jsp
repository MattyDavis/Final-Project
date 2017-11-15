<%@ include file="/WEB-INF/header.jsp" %>
    <body>
        <a href="notes?notes">Back to Notes</a>
        <h1>Manage Users</h1>
        <h2>Users</h2>
        <p>${errorMessage}</p>
        <table>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Role</th>
                <th>Notes</th>
            </tr>
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.role.roleName}</td>
                    <td>
                        <ul>
                        <c:forEach var="note" items="${user.noteList}">
                            <li>${note.title}</li>
                        </c:forEach>
                        </ul>
                    </td>
                </tr>
        </table>
        <c:if test="${selectedUser != null}">
            <h3>Edit User</h3>
            <form action="login" method="POST">
                username: <input type="text" name="username" value="${selectedUser.username}" readonly><br>
                first name: <input type="text" name="firstname" value="${selectedUser.firstname}"><br>
                last name: <input type="text" name="lastname" value="${selectedUser.lastname}"><br>
                password: <input type="password" name="password" value="${selectedUser.password}"><br>
                email: <input type="email" name="email" value="${selectedUser.email}"><br>
                active: <input type="checkbox" name="active" ${selectedUser.active ? "checked" : ""}><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>
