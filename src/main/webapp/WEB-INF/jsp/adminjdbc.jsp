<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:url value ="/adminjdbc" var="adminjdbc" scope="application"/>

<!DOCTYPE html>

<html lang="en">

    <body>
        <select name ="action" form="jdbccontrols">
            <option value="0">Create database</option>
            <option value="1">reset database</option>
            <option value="2">addvalues</option>
            <option value="3">to be implemented</option>
        </select>
        <form name="jdbccontrols" id ="jdbccontrols" action="${adminjdbc}">
            <input type ="submit" value="Submit"/>
        </form>
        <h1>${result}</h1>
    </body>
    ${val}
</html>
