<%-- 
    Document   : template/header.jsp
    Created on : 5-apr-2015, 21.51.12
    Author     : Alexandru Podgoreanu 
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="container-fluid" >
    <div class="page-header row">
        <section class="col-md-4 margin-15-top">.col-md-4</section>
        <section class="col-md-5 margin-15-top">.col-md-3</section>
        <!--User Details-->
        <section class="col-md-2 user-details">
            <c:import url="template/form.jsp">
                    <c:param name="type" value="smalllogin"/>
            </c:import>
        </section>
        <section class="col-md-1 margin-15-top">.col-md-1</section>
    </div>
</header>

