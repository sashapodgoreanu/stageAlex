<%-- 
    Document   : hidden_menu
    Created on : Oct 5, 2015, 10:48:04 PM
    Author     : Alexandru Podgoreanu
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!----------------- hidden context -------------------------------->


<!----------------- right click menu context of an object-------------------------------->
<div id="contextMenu" class="dropdown clearfix" style="z-index: 50;">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:block;position:static;margin-bottom:5px;">
        <li><a tabindex="-1" href="#">Action</a></li>
        <li><a tabindex="-1" href="#">Another action</a></li>
        <li><a tabindex="-1" href="#">Something else here</a></li>
        <li class="divider"></li>
        <li id="propertiesMenu"><a tabindex="-1" href="#hiddenMenu">Properties</a></li>
    </ul>
</div>

<!----------------- click show/hide tags v1 -------------------------------->
<div id="contextMenuShowHideTagsv1" class="dropdown clearfix" style="z-index: 51;">
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" style="display:block;position:static;margin-bottom:5px; text-align: right;">
        <li><a><label for="seeSharedv1">
                    <input id ="seeSharedv1" type="checkbox" value ="">
                    See Shared &nbsp;&nbsp;<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                </label></a>
        </li>
        <li><a><label for="seePersonalv1">
                    <input id ="seePersonalv1" type="checkbox" value ="">
                    See Personal &nbsp;&nbsp;<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                </label></a>
        </li>
        <li><a><label for="seeTrash">
                    <input id ="seeTrash" type="checkbox" value ="">
                    See Trash &nbsp;&nbsp;<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </label></a>
        </li>
    </ul>
</div>

<div id="hiddenMenu" class="panel panel-default" style="z-index: 40;">
    <div id="propBar"><span>Resource Properties</span> <span style="float: right;" class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
    </div>
    
    <c:import url="template/closable_panel.jsp">
        <c:param name="title" value="Object of discourse"/>
        <c:param name="include" value="template/object_of_discourse.jsp"/>
    </c:import>
    
    <c:import url="template/closable_panel.jsp">
        <c:param name="title" value="Main Topic"/>
        <c:param name="include" value="template/default_container.jsp"/>
    </c:import>

    <c:import url="template/closable_panel.jsp">
        <c:param name="title" value="Type"/>
        <c:param name="include" value="template/default_container.jsp"/>
    </c:import>

    <c:import url="template/closable_panel.jsp">
        <c:param name="title" value="Language"/>
        <c:param name="include" value="template/default_container.jsp"/>
    </c:import>

    <c:import url="template/closable_panel.jsp">
        <c:param name="title" value="Formats"/>
        <c:param name="include" value="template/default_container.jsp"/>
    </c:import>

    <c:import url="template/closable_panel.jsp">
        <c:param name="title" value="Authors"/>
        <c:param name="include" value="template/default_container.jsp"/>
    </c:import>

    <c:import url="template/closable_panel.jsp">
        <c:param name="title" value="Contains"/>
        <c:param name="include" value="template/default_container.jsp"/>
    </c:import>
</div>