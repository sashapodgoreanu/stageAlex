<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>
<s:url value = "/helloWorld" var ="helloWorld" scope="application"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--Bootstrap: The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Welcome to Spring Web MVC project</title>

        <!-- Bootstrap -->
        <link href="${pageContext.request.getContextPath()}/css/bootstrap.min.css" rel="stylesheet">
        <script src="${pageContext.request.getContextPath()}/js/functions.js" type="text/javascript"></script>
        <script src="${pageContext.request.getContextPath()}/js/jquery-2.1.3.min.js" type="text/javascript"></script>

        <!-- jQuery UI Autocomplete - Remote datasource -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <style>
            .ui-autocomplete-loading {
                background: white url("${pageContext.request.getContextPath()}/jquery-ui/images/ui-anim_basic_16x16.gif") right center no-repeat;
            }
        </style>
        <script>
            $(function () {
                function log(message) {
                    $("<div>").text(message).prependTo("#log");
                    $("#log").scrollTop(0);
                }

                $(".tag").autocomplete({
                    source: function (request, response) {
                        $.ajax({
                            url: "${tag}",
                            type: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify({'id':request.term, 'value':request.term}),
                            dataType: 'json',
                                    success: function (data) {
                                        response(data);
                                    }
                        });
                    },
                    minLength: 1,
                    select: function (event, ui) {
                        alert("selected"+this.value+" "+ui.item.label);
                    },
                    open: function () {
                        $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
                    },
                    close: function () {
                        $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
                    }
                });
            });
        </script>

    </head>



    <p>HELLLO </p>
    <div class="row">
        <div class="col-md-4">.col-md-4</div>
        <div class="col-md-4">
            <div>
                <label for="city">Your city: </label>
                <input id="city" class="tag">
                Powered by <a href="http://geonames.org">geonames.org</a>
            </div>

            <div style="margin-top:2em; font-family:Arial">
                Result:
                <div id="log" style="height: 200px; width: 300px; overflow: auto;" class="ui-widget-content"></div>
            </div>
        </div>
        <div class="col-md-4">.col-md-4</div>
    </div>
    <button id = "button">Send an HTTP GET request to a page and get the result back</button>


    <a href="${tag}/Register">Register</a>
    <a href="${helloWorld}">Hello</a>

</body>
</html>
