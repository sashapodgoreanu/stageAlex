<%-- 
    Document   : closable_panel
    Created on : Oct 7, 2015, 3:33:19 PM
    Author     : Podgoreanu Alexandru
    @param title - panel's tittle
    @param include - jsp to include in the body of then panel
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default no-padding-right no-padding-left">
    <div class="panel-heading panel-collapse"><span class="glyphicon glyphicon-triangle-right triangle" aria-hidden="true" ></span>
        ${param.title}
    </div>
    <div class="panel-body margin-15-left">
        <c:import url="${param.include}"/>
    </div>
</div>
