<%@ include file="/WEB-INF/header.jsp" %>
    <body>
        <h1>Manage Users</h1>
        <hr>
        <h2>Users for ${companyName}</h2>
        <p>${errorMessage}</p>
        <table>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Role</th>
                <th>Notes</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <c:if test = "${user.role.roleName!= 'admin'}">
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
                    <td>
                        <form action="admin" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${user.username}">
                        </form>
                    </td>
                    <td>
                        <form action="admin" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${user.username}">
                        </form>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
        <c:if test="${selectedUser == null}">
            <h3>Add User</h3>
            <form action="admin" method="POST">
                username: <input type="text" name="username"><br>
                first name: <input type="text" name="firstname"><br>
                last name: <input type="text" name="lastname"><br>
                password: <input type="password" name="password"><br>
                email: <input type="email" name="email"><br>
                active: <input type="checkbox" name="active"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedUser != null}">
            <h3>Edit User</h3>
            <form action="admin" method="POST">
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
            
            
            <hr>
            
            
    </body>
</html>
