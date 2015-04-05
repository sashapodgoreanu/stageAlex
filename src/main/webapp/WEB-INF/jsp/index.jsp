<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>
<s:url value = "/helloWorld" var ="helloWorld" scope="application"/>

<!DOCTYPE html>

<html lang="en">
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
            });
        </script>

    </head>

    <body>
    <c:import url="template/header.jsp"/>
    <c:import url="template/nav.jsp"/>
    
    <article class="container-fluid">
        <div class="row">
            <div class="col-md-4">.col-md-4</div>
            <div class="col-md-4">
                <form class="form-horizontal tag-container">
                    <div class="form-group cloneable">
                        <label for="tag-1" class="col-sm-3 control-label label-tag">First Tag</label>
                        <div class="col-sm-9">
                            <input id="tag-1" class="input-tag form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-4">.col-md-4</div>
        </div>
    </article>
    <aside></aside>
    <footer></footer>
</body>
</html>
