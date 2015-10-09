<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>
<s:url value = "/helloWorld" var ="helloWorld" scope="application"/>
<sec:authorize access="isAuthenticated()" var="isAuthenticated" />
<!DOCTYPE html>

<html lang="en">
    <c:import url="template/head.jsp"/>
    <body>
        <c:import url="template/header.jsp"/>
        <c:import url="template/nav.jsp"/>

        <c:choose>
            <c:when test="${isAuthenticated}"></c:when>
            <c:otherwise></c:otherwise>
        </c:choose>

        <main id="main" class="container-fluid applybackground-grey row padding-top-20">
            <aside class="col-md-2">
                <div class="list-group">
                    <a href="#" class="list-group-item active">
                        Cras justo odio
                    </a>
                    <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                    <a href="#" class="list-group-item">Morbi leo risus</a>
                    <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                    <a href="#" class="list-group-item">Vestibulum at eros</a>
                </div>
            </aside>
            <section class="col-md-1"></section>
            <section class="col-md-7">
                <c:import url="template/table_base.jsp"/>
            </section>
            <aside class="col-md-2">
                <c:import url="template/wardrobe.jsp"/>
                <c:import url="template/users_on_table.jsp"/>
            </aside>
        </main>
        <c:import url="template/hidden_menu.jsp"/>   

        <c:import url="template/footer.jsp"/>
    </body>
</html>
