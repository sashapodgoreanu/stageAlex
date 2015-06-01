<%-- 
    Document   : home
    Created on : 15-mag-2015, 17.40.54
    Author     : a.podgoreanu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/login" var="login" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>
<s:url value ="/verifyLogin" var="verifyLogin" scope="application"/>
<s:url value ="/logout" var="logout" scope="application"/>
<sec:authorize access="isAuthenticated()" var="isAuthenticated" />

<!DOCTYPE html>

<html lang="en">
    <c:import url="template/head.jsp"/>

    <!-- Posiziona questo JavaScript asincrono appena prima del tag </body> -->
    <script type="text/javascript">
        (function () {
            var po = document.createElement('script');
            po.type = 'text/javascript';
            po.async = true;
            po.src = 'https://apis.google.com/js/client:plusone.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(po, s);
        })();
    </script>
    <body>
        <c:import url="template/header.jsp"/>
        <c:import url="template/nav.jsp"/>

        <main class="container-fluid applybackground-grey row padding-top-20">
            <section class="col-md-6">
            </section>
            <section class="col-md-4">
                <c:import url="template/form.jsp">
                    <c:param name="type" value="biglogin"/>
                </c:import>
            </section>
            <section class="col-md-2">  
            </section>
        </main>
        <c:import url="template/footer.jsp"/>
    </body>
</html>

