<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:url value ="/tag" var="tag" scope="application"/>
<s:url value ="/tagPost" var="tagPost" scope="application"/>
<s:url value = "/helloWorld" var ="helloWorld" scope="application"/>
<sec:authorize access="isAuthenticated()" var="isAuthenticated" />
<!DOCTYPE html>

<html lang="en">
    <c:import url="template/head.jsp"/>
    <body>
        <c:import url="template/header.jsp"/>
        <c:import url="template/nav.jsp"/>

        <c:choose>
            <c:when test="${isAuthenticated}"></c:when>
            <c:otherwise></c:otherwise>
        </c:choose>

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
            <section class="col-md-5">
                <div class="row">
                    <div class="col-md-8">
                        <c:import url="template/table_base.jsp"/>
                    </div>
                </div>
            </section>
            <section class="col-md-3">
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <!-- Single button -->
                        <button id="idButton" type="button" class="btn btn-default">
                            Action
                        </button>

                    </div>
                    <div class="col-md-4"></div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <!-- Single button -->
                        <button id="idButton" type="button" class="btn btn-default">
                            Action
                        </button>
                    </div>
                    <div class="col-md-4">
                        <!-- Single button -->
                        <button id="idButton" type="button" class="btn btn-default">
                            Action
                        </button>
                    </div>
                    <div class="col-md-4">
                    </div>
                </div>

            </section>
            <aside class="col-md-2">
                
                <c:import url="template/wardrobe.jsp"/>
            </aside>

            <!----------------- hidden context -------------------------------->

            <div id="contextMenu" class="dropdown clearfix" style="z-index: 50;">
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:block;position:static;margin-bottom:5px;">
                    <li><a tabindex="-1" href="#">Action</a></li>
                    <li><a tabindex="-1" href="#">Another action</a></li>
                    <li><a tabindex="-1" href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li id="propertiesMenu"><a tabindex="-1" href="#hiddenMenu">Properties</a></li>
                </ul>
            </div>

            <div id="hiddenMenu" class="panel panel-default" style="z-index: 40;">
                <div id="propBar"><span>Properties</span> <span style="float: right;" class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
                </div>

                <div class="panel panel-default no-padding-right no-padding-left">
                    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>Main topic
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default no-padding-right no-padding-left">
                    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>Type
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default no-padding-right no-padding-left">
                    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>Objects of discourse
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal tag-container margin-15-top margin-20-right">
                            <div class="form-group cloneable">
                                <label for="autocomplete" class="col-sm-3 control-label label-tag">Mine: </label>
                                <div class="col-sm-9">
                                    <div id="autocompleteContainer" class="autocompleteContainer">
                                        <input id="autocompleteMine" class="autocomplete-hidden input-tag form-control">
                                    </div>
                                </div>
                                <label for="tagContainer" class="col-sm-3 control-label label-tag">Others: </label>
                                <div class="col-sm-9">
                                    <div id="tagContainer" class="tagContainer">
                                        <input id="autocompleteOthers" class="autocomplete-hidden input-tag form-control">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel panel-default no-padding-right no-padding-left">
                    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>Language
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default no-padding-right no-padding-left">
                    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>Formats
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default no-padding-right no-padding-left">
                    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>Authors
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default no-padding-right no-padding-left">
                    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>Contains
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                            <li>This is just some random content.</li>
                        </ul>
                    </div>
                </div>
            </div>
        </main>
        <c:import url="template/footer.jsp"/>
    </body>
</html>
