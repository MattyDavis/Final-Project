<%@ include file="/WEB-INF/header.jsp" %>
    <body>
        <h1>Manage Notes</h1>
        <a href="login?account">Manage Account</a>
        <h2>Notes:</h2>
        <p>${errorMessage}</p>
        <table border =>
            <tr>
                <th>Note ID</th>
                <th>Date Created</th>
                <th>Title</th>
                <th>Contents</th>
            </tr>
            <c:forEach var="note" items="${notes}">
                <tr>
                    <td>${note.noteID}</td>
                    <td>${note.dateCreated}</td>
                    <td>${note.title}</td>
                    <td>${note.contents}</td>
                    <td>
                        <form action="notes" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedNote" value="${note.noteID}">
                        </form>
                    </td>
                    <td>
                        <form action="notes" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedNote" value="${note.noteID}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedNote == null}">
            <h3>Add Note</h3>
            <form action="notes" method="POST">
                Note Title : <input type="text" name="title"><br>
                Note : <input type="text" name="note"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add Note">
            </form>
        </c:if>
        <c:if test="${selectedNote != null}">
            <h3>Edit Note</h3>
            <form action="notes" method="POST">
                Note ID: <input type="text" name="id" value="${selectedNote.noteID}" readonly><br>
                Note Title : <input type="text" name="title" value="${selectedNote.title}"><br>
                Note : <input type="text" name="note" value="${selectedNote.contents}"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>