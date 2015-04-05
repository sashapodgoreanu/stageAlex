<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>
<s:url value = "/helloWorld" var ="helloWorld" scope="application"/>

<!DOCTYPE html>

<html lang="en">
    <c:import url="template/head.jsp"/>
    <body>
        <c:import url="template/header.jsp"/>
        <c:import url="template/nav.jsp"/>

        <main class="container-fluid applybackground-grey row padding-top-20">
            <aside class="col-md-2">
                <div class="list-group">
                    <a href="#" class="list-group-item active">
                        Cras justo odio
                    </a>
                    <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                    <a href="#" class="list-group-item">Morbi leo risus</a>
                    <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                    <a href="#" class="list-group-item">Vestibulum at eros</a>
                </div>
            </aside>
            <section class="col-md-8">
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <form class="form-horizontal tag-container">
                            <div class="form-group cloneable">
                                <label for="tag-1" class="col-sm-3 control-label label-tag">First Tag</label>
                                <div class="col-sm-9">
                                    <input id="tag-1" class="input-tag form-control" value="">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-4"></div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <button type="button" class="btn btn-default" aria-label="Left Align">
                            <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
                        </button>
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-default" aria-label="Left Align">
                            <span class="glyphicon glyphicon glyphicon-envelope" aria-hidden="true"></span> New Mail
                        </button>
                    </div>
                    <div class="col-md-4"></div>
                </div>

            </section>
            <aside class="col-md-2">
                <div class="list-group">
                    <a href="#" class="list-group-item active">
                        Cras justo odio
                    </a>
                    <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
                    <a href="#" class="list-group-item">Morbi leo risus</a>
                    <a href="#" class="list-group-item">Porta ac consectetur ac</a>
                    <a href="#" class="list-group-item">Vestibulum at eros</a>
                </div>
            </aside>
        </main>
        <c:import url="template/footer.jsp"/>
    </body>
</html>
