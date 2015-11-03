<%-- 
    Document   : table_base
    Created on : 27-giu-2015, 18.40.19
    Author     : Sasha Alexandru Podgoreanu
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="table-base" id="centerTable">

</div>
<div id="tableTagContainer">
    <c:forEach items="${elementsOnTable}" var="element">
        <c:if test="${element.inWardrobe == false}">
            <p id="${element.url}" class="btn btn-default tipo1 draggable">
                ${element.name}
            </p>
            <script>
                $(function () {
                    //400 = size of #centerTable - 100
                    var random1 = Math.floor((Math.random() * 400) + 50);
                    var random2 = Math.floor((Math.random() * 400) + 50);
                    $("#${element.url}").position({
                        my: "center",
                        at: "left+" + random1 + " top+" + random2,
                        of: "#centerTable"
                    });
                });
            </script>
        </c:if>
    </c:forEach>
</div>
