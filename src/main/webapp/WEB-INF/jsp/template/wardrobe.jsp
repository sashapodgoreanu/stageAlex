<%-- 
    Document   : wardrobe
    Created on : 11-lug-2015, 16.52.26
    Author     : SashaAlexandru
--%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<s:url value = "/addElement" var ="addElement" scope="application"/>
<script type="text/javascript">
    $(document).ready(function () {
        var saveModalWardrobe = new SaveModal("#addElementModal","#createNewElementButton","A");
        //listPanel, addElement, modal, addURL
        var wardrobe = new ListPanel("#wardrobe", "#addElement","#addElementModal", "${addElement}");
        saveModalWardrobe.addObserver(wardrobe);
        saveModalWardrobe.init();
        wardrobe.init();
    });
</script>
<div id="wardrobe" class="panel panel-default">
    <!--panel content-->
    <div class="panel-heading">Wardrobe<span id="addElement" table-id="${idTable}" class="glyphicon glyphicon-plus" aria-hidden="true"></span></div>
    <div class="panel-body">
        <p>Drag and drop elements to and from table</p>
    </div>

    <!-- List group with elements-->
    <ul class="list-group">
        <li class="list-group-item">Cras justo odio</li>
        <li class="list-group-item">Dapibus ac facilisis in</li>
        <li class="list-group-item">Morbi leo risus</li>
        <li class="list-group-item">Porta ac consectetur ac</li>
        <li class="list-group-item">Vestibulum at eros</li>
    </ul>
</div>


<!-- HIDDEN MODAL FOR ADDING NEW ELEMENT --> 
<div id ="addElementModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add New Element</h4>
            </div>

            <div class="modal-body form-horizontal"">

                <div class="form-group">
                    <label for="nameNewElementInput" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input id = "nameNewElementInput" name ="name" type="input">
                    </div>
                </div>
                <div class="form-group">
                    <label for="urlNewElementInput" class="col-sm-2 control-label">URL</label>
                    <div class="col-sm-10">
                        <input id = "urlNewElementInput" name ="url" type="input">
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id = "createNewElementButton" type="button" class="btn btn-primary">Add Element</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    
</div><!-- /.modal -->
