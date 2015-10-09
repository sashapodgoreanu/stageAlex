<%-- 
    Document   : users_on_table
    Created on : Oct 7, 2015, 4:58:37 PM
    Author     : Sasha
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="userOnTable" class="panel panel-default">
    <!--panel content-->
    <div class="panel-heading">Users</div>

    <!-- List group with users-->
    <ul class="media-list">
        <c:forEach items="${usersOnTable}" var = "user" varStatus="status">
            <li class="media">
                <div class="media-left">
                    <a href="#">
                        <!-- <img class="media-object" src="..." alt="..." -->
                        <span class="glyphicon glyphicon-user userIcon1" 
                              aria-hidden="true" 
                              data-user-id="${user.id}"
                              data-color="${color[status.index]}"></span>
                    </a>
                </div>
                <div class="media-body">
                    ${user.id}
                </div>
            </li>
        </c:forEach>
    </ul>
</div>

<script>
    function getRandomColor() {
        var letters = '0123456789ABCDEF'.split('');
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
</script>