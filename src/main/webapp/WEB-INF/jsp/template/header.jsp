<%-- 
    Document   : template/header.jsp
    Created on : 5-apr-2015, 21.51.12
    Author     : Alexandru Podgoreanu 
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()" var="isAuthenticated" />

<header class="container-fluid" >
    <div class="page-header row">
        <!--Logo-->
        <section class="col-md-2 margin-15-top"></section>
        <!--Search Bar-->
        <section class="col-md-3 margin-15-top">
        </section>
        <!--Search Bar-->
        <section class="col-md-3 margin-15-top">
            <c:if test="${!isAuthenticated}">  
                <ul class="list-inline">
                    <li><a href="${discoversemt}">Discover SemT</a></li>
                    <li><a href="${discoversemt}">About</a></li>
                </ul>
            </c:if>
        </section>
        <!--User Details-->
        <section class="col-md-3 user-details">
            <c:import url="template/form.jsp">
                <c:param name="type" value="smalllogin"/>
            </c:import>
        </section>
        <!--News-->
        <section class="col-md-1 margin-15-topt"></section>
    </div>
</header>

