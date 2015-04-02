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
        <script>
            $(document).ready(function () {
                initializeField("${tag}");
                onChange("${tag}");
                $('#button').click(function () {
                    var ajaxTag = {'id': 15, 'value': 'dog'};
                    $.ajax({
                        url: "${tagPost}",
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(ajaxTag),
                        dataType: 'json'
                    });
                });
            });

        </script>

    </head>



    <p>HELLLO </p>
    <div class="row">
        <div class="col-md-4">.col-md-4</div>
        <div class="col-md-4">
            <fieldset id ="field">
                <select class="form-control tag">
                </select>
            </fieldset>
        </div>
        <div class="col-md-4">.col-md-4</div>
    </div>
    <button id = "button">Send an HTTP GET request to a page and get the result back</button>


    <a href="${tag}/Register">Register</a>
    <a href="${helloWorld}">Hello</a>

</body>
</html>
