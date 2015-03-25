<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                fillSelect();
                $('#uno').change(function () {
                    var val = this.value;
                    $('p').append(val);
                    // findSelected();
                });
            });
        </script>

    </head>


    <p>HELLLO </p>
    <div class="row">
        <div class="col-md-4">.col-md-4</div>
        <div class="col-md-4">
            <fieldset>
                <select id="uno" class="form-control" name="speed">
                </select>
            </fieldset>
        </div>
        <div class="col-md-4">.col-md-4</div>
    </div>


</body>
</html>
