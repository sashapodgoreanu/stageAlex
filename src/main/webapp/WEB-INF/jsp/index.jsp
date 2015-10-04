<%-- 
    Document   : home
    Created on : 15-mag-2015, 17.40.54
    Author     : a.podgoreanu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()" var="isAuthenticated" />

<!DOCTYPE html>

<html lang="en">
    <c:import url="template/head.jsp">
        
    </c:import>
    
    <body>
        <c:import url="template/header.jsp"/>
        <c:import url="template/nav.jsp"/>
        <main class="container-fluid applybackground-grey row padding-top-20">
            <section class="col-md-7">
            </section>
            <section class="col-md-4 margin-60-top">
                <c:import url="template/form.jsp">
                    <c:param name="type" value="biglogin"/>
                </c:import>
            </section>
            <section class="col-md-1">  
            </section>
        </main>
        <c:import url="template/footer.jsp"/>
    </body>
</html>

