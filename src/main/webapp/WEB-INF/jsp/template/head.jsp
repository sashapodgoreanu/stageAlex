<%-- 
    Document   : head
    Created on : 5-apr-2015, 23.49.31
    Author     : Alexandru Podgoreanu 
--%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--Bootstrap: The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Welocme</title>



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
            performTagging("${tag}");
            //enableMenu();
            //$('.dropdown-toggle').dropdown();
            var $contextMenu = $("#contextMenu");
            $("body").on("contextmenu", ".btn", function (e) {
                //prevent default menu on right click
                e.preventDefault();
                $.rightCliked = this;
                $contextMenu.css({
                    display: "block",
                    left: e.pageX,
                    top: e.pageY
                });
                return false;
            });

            $("body").on("click", function (e) {
                //prevent default menu on right click
                $contextMenu.hide();
            });

            $(".header").click(function () {

                $header = $(this);
                //getting the next element
                $content = $header.next();
                //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
                $content.slideToggle(500, function () {
                    //execute this after slideToggle is done
                    //change text of header based on visibility of content div
                    var spanArrow = $header.find("span");
                    if ($content.is(":visible")) {
                        spanArrow.attr("class", "glyphicon glyphicon-triangle-bottom");
                        spanArrow.text("P");
                    } else {
                        spanArrow.attr("class", "glyphicon glyphicon-triangle-right");
                        spanArrow.text("V");
                    }
                });

            });

            var position;
            $(window).scroll(function () {
                var top = $(window).scrollTop();
                console.log("top "+top);
                console.log("div top "+position);
                cpanel.css({
                    top: position-top
                });
            });


            var cpanel = $("#hiddenMenu");
            cpanel.hide();
            $("#propertiesMenu").on("click", function (e) {
                cpanel.show();
                cpanel.css({
                    display: "block",
                    left: e.pageX,
                    top: e.pageY
                });
                position = cpanel.position().top;
            });
        });

    </script>

</head>
