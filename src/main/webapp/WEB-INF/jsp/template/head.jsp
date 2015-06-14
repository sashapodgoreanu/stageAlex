<%-- 
    Document   : head
    Created on : 5-apr-2015, 23.49.31
    Author     : Alexandru Podgoreanu 
--%>

<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<s:url value ="/save-table" var="savetable" scope="application"/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--Bootstrap: The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>${title}</title>



    <script src="${pageContext.request.getContextPath()}/js/jquery-2.1.3.min.js" type="text/javascript"></script>

    <!--SemT CSS -->
    <link href="${pageContext.request.getContextPath()}/css/semtpp-style.css" rel="stylesheet">
    <script src="${pageContext.request.getContextPath()}/js/functions.js" type="text/javascript"></script>

    <!-- Bootstrap -->
    <link href="${pageContext.request.getContextPath()}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.getContextPath()}/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- jQuery UI Autocomplete -->
    <link rel="stylesheet" href="${pageContext.request.getContextPath()}/jquery-ui/jquery-ui.css">
    <script src="${pageContext.request.getContextPath()}/jquery-ui/jquery-ui.js"></script>
    <style>
        .ui-autocomplete-loading {
            background: white url("${pageContext.request.getContextPath()}/jquery-ui/images/ui-anim_basic_16x16.gif") right center no-repeat;
        }
    </style>
    <script>
        $(function () {
            initTablesNav("${savetable}");
            performTagging("${tag}");

            //Menu for right click
            var rightclickMenu = $("#contextMenu");

            //listener for right click for elements
            $("body").on("contextmenu", ".btn", function (e) {
                //prevent default menu on right click
                e.preventDefault();
                //Variable that contains all insormation about right clicked element
                $.rightCliked = this;
                //show rightclickMenu
                rightclickMenu.css({
                    display: "block",
                    left: e.pageX,
                    top: e.pageY
                });
            });

            //listener that hides #contextMenu
            $("body").on("click", function (e) {
                rightclickMenu.hide();
            });

            //Adds id to section of Properties-rightclikMenu
            $(function () {
                var elem = $("#hiddenMenu").find(".panel-collapse");
                var sections = elem.parent().siblings();
                var L = 0;
                $.each(sections, function (i, elem) {
                    if (L !== 0)
                        $(this).addClass('closed');
                    L++;
                });
                console.log(L);
            });

            //title bar for sliding elements
            $thisSlide = null;
            $lastSlide = null;

            //listener that slide up and down panels from Properties-rightclikMenu
            $(".panel-collapse").click(function () {

                $thisSlide = $(this).parent();
                if ($thisSlide.hasClass("open")) {
                    $content1 = $thisSlide.children().next();

                    $thisSlide.removeClass("open");
                    $thisSlide.addClass("closed");
                    $content1.slideToggle(300, function () {
                        //execute this after slideToggle is done

                        var spanArrow = $thisSlide.find("span");

                        if ($content1.is(":visible")) {
                            spanArrow.attr("class", "glyphicon glyphicon-triangle-bottom");
                        } else {
                            spanArrow.attr("class", "glyphicon glyphicon-triangle-right");
                        }
                    });
                } else {
                    //close the one that are open
                    $openSlide = $("#hiddenMenu").find(".open");
                    if ($openSlide !== null) {
                        $openSlide.removeClass("open");
                        $openSlide.addClass("closed");
                        $content1 = $openSlide.children().next();
                        $content1.slideUp(300, function () {
                            //execute this after slideUp is done
                            var spanArrow = $openSlide.find(".glyphicon");
                            spanArrow.attr("class", "glyphicon glyphicon-triangle-right");
                        });
                    }
                    //open the clicked one
                    $content1 = $thisSlide.children().next();
                    $thisSlide.removeClass("closed");
                    $thisSlide.addClass("open");
                    $content1.slideDown(300, function () {
                        //execute this after slideToggle is done
                        var spanArrow = $thisSlide.find(".glyphicon");
                        spanArrow.attr("class", "glyphicon glyphicon-triangle-bottom");
                    });
                }
            });

            //Position of Properties-rightclickMenu
            var position;
            //Propertie-rightclickeMenu 
            var cpanel = $("#hiddenMenu");
            cpanel.hide();

            //Listener that shows Properties-rightclickMenu
            $("#propertiesMenu").on("click", function (e) {
                cpanel.show();
                cpanel.css({
                    display: "block",
                    left: e.pageX - 50,
                    top: e.pageY - 130
                });
                //set the position of the Properties-rightclickMenu
                position = cpanel.position().top;
            });

            //Listener that shows Properties-rightclickMenu
            $("#hiddenMenu").on("click", ".glyphicon-remove-circle", function (e) {
                cpanel.hide();
            });
            

            //listener of scrolling bar - Updates position for Properties-rightclickMenu
            $(window).scroll(function () {
                var top = $(window).scrollTop();
                //
                cpanel.css({
                    top: position - top
                });
            });

            //Draggable
            $('body').on('mousedown', '#propBar', function () {

                $(this).parent().addClass('draggable').parents().on('mousemove', function (e) {
                    $('.draggable').offset({
                        top: e.pageY - $('#propBar').outerHeight() / 2,
                        left: e.pageX - $('#propBar').outerWidth() / 2
                    }).on('mouseup', function () {
                        $(this).removeClass('draggable');

                        //Update position of Properties-rightclickMenu for window scroll
                        position = cpanel.position().top;
                    });
                    e.preventDefault();
                });
            }).on('mouseup', function () {
                $('.draggable').removeClass('draggable');
            });
        });

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
                        userDetails = response;
                        userDetails.idtoken = id_token;
                        userDetails.access_token = access_token;
                        console.log("from google: " + userDetails);
                        //userDetails = response;
                        $.ajax({
                            url: "${verifyLogin}",
                            type: 'POST',
                            contentType: 'application/json',
                            dataType: 'json',
                            //data to be sent
                            data: JSON.stringify(userDetails),
                            success: function (data) {
                                console.log("success: POST /verifyLogin " + userDetails);
                                submit_login(data, userDetails);
                            },
                            error: function (error) {
                                console.error(error);
                            }
                        });
                    },
                    error: function (error) {
                        console.error(error);
                    }
                });
            } else if (authResult['error']) {
                // Si è verificato un errore.
                // Possibili codici di errore:
                //   "access_denied" - L'utente ha negato l'accesso alla tua app
                //   "immediate_failed" - Impossibile eseguire l'accesso automatico dell'utente
                console.log('There was an error: ' + authResult['error']);
                //alert("ERROR sign in google");
            }
        }
        function submit_login(data, userDetails){
            if (data){
                $("#username").val(userDetails.id);
                $("#password").val(userDetails.idtoken);
                $("#inputEmail").val(userDetails.emails[0].value);
                $("#login-form").submit();
            }
        }
        function disconnectUser() {
            console.log("access token logout: " + access_token);
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
    </script>

</head>
