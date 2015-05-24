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

<!DOCTYPE html>

<html lang="en">
    <c:import url="template/head.jsp"/>

    <script type="text/javascript">
        var access_token;
        var id_token;
        function signinCallback(authResult) {
            if (authResult['access_token']) {
                // Autorizzazione riuscita
                // Nascondi il pulsante di accesso ora che l'utente è autorizzato. Ad esempio: 
                document.getElementById('signinButton').setAttribute('style', 'display: none');
                access_token = authResult['access_token'];
                id_token = authResult['id_token'];

                //get userDetails from Google
                var userDetails;
                $.ajax({
                    url: 'https://www.googleapis.com/plus/v1/people/me',
                    headers: {
                        'Authorization': 'Bearer ' + access_token
                    },
                    success: function (response) {
                        console.log("Received user info", response);
                        userDetails = response;
                        userDetails.idtoken = id_token;
                        alert("recived" + userDetails);
                        //userDetails = response;
                        $.ajax({
                            url: "${verifyLogin}",
                            type: 'POST',
                            contentType: 'application/json',
                            dataType: 'json',
                            //data to be sent
                            data: JSON.stringify(userDetails),
                            success: function (data) {
                                alert("sending" + userDetails);
                                //fill autocomplete suggestions box
                                response(data);
                            },
                            error: function (error) {
                                alert("error" + error + " userdetails " + userDetails);
                            }
                        });
                    },
                    error: function (error) {
                        console.error(error);
                    }
                });
/*
                //Send userDetails to ower server
                $.ajax({
                    url: "${verifyLogin}",
                    type: 'POST',
                    contentType: 'application/json',
                    dataType: 'json',
                    //data to be sent
                    data: userDetails,
                    success: function (data) {
                        alert("sending" + userDetails);
                        //fill autocomplete suggestions box
                        response(data);
                    },
                    error: function (error) {
                        alert("error" + error + " userdetails " + userDetails);
                    }
                });*/


            } else if (authResult['error']) {
                // Si è verificato un errore.
                // Possibili codici di errore:
                //   "access_denied" - L'utente ha negato l'accesso alla tua app
                //   "immediate_failed" - Impossibile eseguire l'accesso automatico dell'utente
                alert('There was an error: ' + authResult['error']);
                //alert("ERROR sign in google");
            }
        }
        function disconnectUser(/*access_token*/) {
            alert(access_token);
            var revokeUrl = 'https://accounts.google.com/o/oauth2/revoke?token=' +
                    access_token;

            // Esecuzione di una richiesta GET asincrona.
            $.ajax({
                type: 'GET',
                url: revokeUrl,
                async: false,
                contentType: "application/json",
                dataType: 'jsonp',
                success: function (nullResponse) {
                    // Esegui un'azione, l'utente è disconnesso
                    // La risposta è sempre indefinita.
                },
                error: function (e) {
                    // Gestione dell'errore
                    // console.log(e);
                    // Puoi indirizzare gli utenti alla disconnessione manuale in caso di esito negativo
                    // https://plus.google.com/apps
                }
            });
        }
        // È possibile attivare la disconnessione con un clic del pulsante
        $('#revokeButton').click(disconnectUser);
    </script>
</script>

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
                    <form class="form-horizontal" action='${login}' method="POST">
                        <div class="form-group">
                            <label for="inputUsername" class="col-sm-2 control-label">Username</label>
                            <div class="col-sm-10">
                                <input type="text" name = "username" class="form-control" id="inputUsername" placeholder="Pick a username">
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
                        <div class="form-group row">
                            <span id="signinButton">
                                <span
                                    class="g-signin"
                                    data-callback="signinCallback"
                                    data-clientid="630129138206-mpttf2fj47g6milr9hlf6sfm8gijitue.apps.googleusercontent.com"
                                    data-cookiepolicy="single_host_origin"
                                    data-requestvisibleactions="http://schemas.google.com/AddActivity"
                                    data-scope="https://www.googleapis.com/auth/plus.login">
                                </span>
                            </span>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
            </div>
        </section>
        <section class="col-md-2">  
            <button id ="revokeButton" type="button" onclick="disconnectUser();">LOG OUT</button>

        </section>
    </main>
    <c:import url="template/footer.jsp"/>
</body>
</html>

