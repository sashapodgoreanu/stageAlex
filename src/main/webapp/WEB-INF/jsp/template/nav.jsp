<%-- 
    Document   : template/nav.jsp
    Created on : 5-apr-2015, 21.45.22
    Author     : Alexandru Podgoreanu 
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()" var="isAuthenticated" />
<c:if test="${isAuthenticated}">
    <nav class="navbar navbar-default no-margin-bottom">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">SemT+</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">Tabella1</a></li>
                    <li><a href="#">Tabella2</a></li>
                    <li><a href="#">Tabella3</a></li>
                </ul>
            </div>
        </div>
    </nav>
</c:if>