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
<s:url value ="/verifyLogin" var="verifyLogin" scope="application"/>
<s:url value ="/logout" var="logout" scope="application"/>

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
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form id="login-form" class="form-horizontal" action='${login}' method="POST">
                            <div class="form-group">
                                <label for="inputUsername" class="col-sm-2 control-label">Username</label>
                                <div class="col-sm-10">
                                    <input id = "username" type="text" name = "username" class="form-control" id="inputUsername" placeholder="Pick a username">
                                </div>
                            </div>
                            <!--div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="email" name  ="email" class="form-control" id="inputEmail3" placeholder="Log in">
                                </div>
                            </div-->
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                                <div class="col-sm-10">
                                    <input id="password" type="password" name ="password" class="form-control" id="inputPassword3" placeholder="Password">
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
                            <div class="form-group row">
                                <span id="signinButton">
                                    <span
                                        approval_prompt="auto"
                                        access_type="online "
                                        class="g-signin"
                                        data-callback="signinCallback"
                                        data-clientid="630129138206-mpttf2fj47g6milr9hlf6sfm8gijitue.apps.googleusercontent.com"
                                        data-cookiepolicy="single_host_origin"
                                        data-requestvisibleactions="http://schemas.google.com/AddActivity"
                                        data-scope="https://www.googleapis.com/auth/plus.login">
                                    </span>
                                </span>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
            <section class="col-md-2">  
                <button id ="revokeButton" type="button" onclick="disconnectUser();">LOG OUT</button>
                <form action='${logout}' method="POST">
                    <div>
                        <button type="submit" class="btn btn-default">Logout</button>
                    </div>
                </form>

            </section>
        </main>
        <c:import url="template/footer.jsp"/>
    </body>
</html>

