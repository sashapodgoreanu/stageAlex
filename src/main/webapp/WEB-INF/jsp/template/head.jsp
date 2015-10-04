<%-- 
    Document   : head
    Created on : 5-apr-2015, 23.49.31
    Author     : Alexandru Podgoreanu 
--%>

<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%-- 
    Set up URLS
--%>
<s:url value ="/save-table" var="savetable" scope="application"/>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/login" var="login" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>
<s:url value ="/verifyLogin" var="verifyLogin" scope="application"/>
<s:url value ="/logout" var="logout" scope="application"/>
<s:url value ="/save-table" var="savetable" scope="application"/>
<s:url value ="/discoversemt" var="discoversemt"  scope="application"/>
<s:url value ="/about" var="about"  scope="application"/>
<s:url value ="/close-table" var="closetable" scope="application"/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--Bootstrap: The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>${title}</title>



    <script src="${pageContext.request.getContextPath()}/js/jquery-2.1.3.min.js" type="text/javascript"></script>
    
   
    
    
    <!-- Bootstrap -->
    <link href="${pageContext.request.getContextPath()}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.getContextPath()}/js/bootstrap.min.js" type="text/javascript"></script>
    
    <!--SemT CSS -->
    <link href="${pageContext.request.getContextPath()}/css/semtpp-style.css" rel="stylesheet">
    <script src="${pageContext.request.getContextPath()}/js/functions.js" type="text/javascript"></script>
    
    <!-- jQuery UI Autocomplete -->
    <script src="${pageContext.request.getContextPath()}/jquery-ui/jquery-ui.js"></script>
    <link href="${pageContext.request.getContextPath()}/jquery-ui/jquery-ui.min.css" rel="stylesheet">
    
    <style>
        .ui-autocomplete-loading {
            background: white url("${pageContext.request.getContextPath()}/jquery-ui/images/ui-anim_basic_16x16.gif") right center no-repeat;
        }
    </style>
    <script>
        $(function () {
            initTablesNav("${savetable}");
            var instanceTable = new Table();
            instanceTable.init("${savetable}","${closetable}");
            //performTagging();

            //Menu for right click
            var rightclickMenu = $("#contextMenu");
            
            //listener for right click for elements
            //jquery.on(event = "contextmenu","...")
            //contextmenu = rightclick
            $("body").on("contextmenu",".tipo1", function (e) {
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

            var objProperties1 = new ObjProperties("#hiddenMenu");
            objProperties1.init();
            
            
            a = "${tag}";
             /*
             * Show Object Properties
             */
            $("#propertiesMenu").on("click", function (e) {
                objProperties1.show(e);
            });
            
            var mineTag = new TagArea("#autocompleteContainer","#autocompleteMine","urltodo");
            mineTag.init();
        });
    </script>

</head>
