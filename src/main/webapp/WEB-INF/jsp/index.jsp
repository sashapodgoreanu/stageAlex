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
                <link href="css/bootstrap.min.css" rel="stylesheet">
                        <link href="/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body>
        <div class="row">
            <div class="col-md-4">.col-md-4</div>
            <div class="col-md-4">
                <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">Name</label>
                <input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">Email</label>
                <input type="email" class="form-control" id="exampleInputEmail2" placeholder="jane.doe@example.com">
            </div>
            <button type="submit" class="btn btn-default">Send invitation</button>
        </form>
            </div>
            <div class="col-md-4">.col-md-4</div>
        </div>

        
    </body>
</html>
