<%-- 
    Document   : template/nav.jsp
    Created on : 5-apr-2015, 21.45.22
    Author     : Alexandru Podgoreanu 
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()" var="isAuthenticated" />
<c:if test="${isAuthenticated}">
    <nav id="navbar-up" class="navbar navbar-default no-margin-bottom">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">SemT+</a>
            </div>
            <div>
                <ul class="nav navbar-nav tables-nav">
                    <li class="active"><a href="#">Home</a></li>
                        <c:forEach items="${openedTables}" var = "table">
                        <li><a id="${table.ID}" href="#">${table.name}</a></li>
                        </c:forEach>
                    <li><a id ="addTable" href="#"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></li>
                </ul>
            </div>
            
            <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        ...
                    </div>
                </div>
            </div>

            <!-- HIDDEN MODAL FOR ADDING NEW TABLE --> 
            <div id ="addTableModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Create new Work Table</h4>
                        </div>
                        
                        <div class="modal-body">
                            <label for="createNewTableInput" class="control-label">Name:</label>
                            <input id = "createNewTableInput" name ="createNewTableInput" type="input">
                        </div>
                   
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button id = "createNewTableButton" type="button" class="btn btn-primary">Create Table</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </div>
    </nav>
</c:if>