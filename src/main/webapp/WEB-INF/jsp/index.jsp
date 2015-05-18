<%-- 
    Document   : home
    Created on : 15-mag-2015, 17.40.54
    Author     : a.podgoreanu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/login" var="login" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>

<!DOCTYPE html>

<html lang="en">
    <c:import url="template/head.jsp"/>
    <body>
        <c:import url="template/header.jsp"/>
        <c:import url="template/nav.jsp"/>

        <main class="container-fluid applybackground-grey row padding-top-20">

            <section class="col-md-6">

            </section>
            
            <section class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form class="form-horizontal" action='${login}'>
                            <div class="form-group">
                                <label for="inputUsername" class="col-sm-2 control-label">Username</label>
                                <div class="col-sm-10">
                                    <input type="text" name = "username" class="form-control" id="inputUsername" placeholder="Pick a username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="email" name  ="email" class="form-control" id="inputEmail3" placeholder="Log in">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                                <div class="col-sm-10">
                                    <input type="password" name ="password" class="form-control" id="inputPassword3" placeholder="Password">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class=" col-sm-5">
                                        <button type="submit" class="btn btn-default">Login</button>
                                    </div>
                                    <div class="col-sm-5">
                                        <button type="submit" class="btn btn-default">Sign up</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
            <section class="col-md-2">

            </section>
        </main>
        <c:import url="template/footer.jsp"/>
    </body>
</html>

