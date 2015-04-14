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

            //listener that hides rightclikMenu
            $("body").on("click", function (e) {
                rightclickMenu.hide();
            });

            $thisSlide = null;
            $lastSlide = null;
            //listener that slide up and down panels from Properties-rightclikMenu
            $(".panel-heading").click(function () {
                $lastSlide = $thisSlide;
                $thisSlide = $(this);
                console.log($lastSlide);
                console.log($thisSlide);
                
                
                //getting the next element, that need to be slided
                $content1 = $thisSlide.next();
                if($lastSlide !== null){
                    $content2 = $lastSlide.next();
                    $content2.slideToggle(500);
                }
                
                //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
                $content1.slideToggle(500, function () {
                    //execute this after slideToggle is done
                    //change text of header based on visibility of content div
                    var spanArrow = $thisSlide.find("span");
                    if ($content1.is(":visible")) {
                        spanArrow.attr("class", "glyphicon glyphicon-triangle-bottom");
                    } else {
                        spanArrow.attr("class", "glyphicon glyphicon-triangle-right");
                    }
                });
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
            
            //listener of scrolling bar
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
                    });
                });
                e.preventDefault();
            }).on('mouseup', function () {
                $('.draggable').removeClass('draggable');
            });
        });

    </script>

</head>
