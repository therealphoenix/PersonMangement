<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <!-- Bootstrap -->
    <link href = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel = "stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

    <!--[if lt IE 9]>
    <script src = "https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src = "https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<center>
    <div class = "jumbotron">
    <div class = "container">
    <div align = "left">
        <span class = "label label-info">TAT</span>
            <h2>Phonebook Application</h2>

        </div>
        </div>
        </div>

</center>
<div align="center">
    <table class = "table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Middlename</th>
            <th>Phone numbers</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody id="myTable">
        <c:forEach var="person" items="${listPerson}">
            <tr class = "active">
                <td class = "success"><c:out value="${person.id}"/></td>
                <td class = "active"><c:out value="${person.name}"/></td>
                <td class = "active"><c:out value="${person.surname}"/></td>
                <td class = "active"><c:out value="${person.middlename}"/></td>
                <td class = "warning"><c:forEach var="phone" items="${listPhone}">
                    <c:if test="${phone.owner == person.id}">
                        <c:out value="${phone.number}"/> <br>
                    </c:if>

                </c:forEach>
                </td>
                <td>
                    <button type = "button" class = "btn btn-success" onclick="window.location.href='edit?id=<c:out value='${person.id}' />'">Edit</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type = "button" class = "btn btn-danger" onclick="window.location.href='delete?id=<c:out value='${person.id}' />'">Delete</button>

                </td>
            </tr>
        </c:forEach>
    </table>

    <a class = "btn btn-info btn-lg" role = "button" onclick="window.location.href='new'">Add Person</a>
</div>
<div class="col-md-12 text-center">
    <ul class="pagination pagination-lg pager" id="myPager"></ul>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<!--Pagination-->
<script>$.fn.pageMe = function(opts){
    var $this = this,
            defaults = {
                perPage: 5,
                showPrevNext: false,
                hidePageNumbers: false
            },
            settings = $.extend(defaults, opts);

    var listElement = $this;
    var perPage = settings.perPage;
    var children = listElement.children();
    var pager = $('.pager');

    if (typeof settings.childSelector!="undefined") {
        children = listElement.find(settings.childSelector);
    }

    if (typeof settings.pagerSelector!="undefined") {
        pager = $(settings.pagerSelector);
    }

    var numItems = children.size();
    var numPages = Math.ceil(numItems/perPage);

    pager.data("curr",0);

    if (settings.showPrevNext){
        $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
    }

    var curr = 0;
    while(numPages > curr && (settings.hidePageNumbers==false)){
        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
        curr++;
    }

    if (settings.showPrevNext){
        $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
    }

    pager.find('.page_link:first').addClass('active');
    pager.find('.prev_link').hide();
    if (numPages<=1) {
        pager.find('.next_link').hide();
    }
    pager.children().eq(1).addClass("active");

    children.hide();
    children.slice(0, perPage).show();

    pager.find('li .page_link').click(function(){
        var clickedPage = $(this).html().valueOf()-1;
        goTo(clickedPage,perPage);
        return false;
    });
    pager.find('li .prev_link').click(function(){
        previous();
        return false;
    });
    pager.find('li .next_link').click(function(){
        next();
        return false;
    });

    function previous(){
        var goToPage = parseInt(pager.data("curr")) - 1;
        goTo(goToPage);
    }

    function next(){
        goToPage = parseInt(pager.data("curr")) + 1;
        goTo(goToPage);
    }

    function goTo(page){
        var startAt = page * perPage,
                endOn = startAt + perPage;

        children.css('display','none').slice(startAt, endOn).show();

        if (page>=1) {
            pager.find('.prev_link').show();
        }
        else {
            pager.find('.prev_link').hide();
        }

        if (page<(numPages-1)) {
            pager.find('.next_link').show();
        }
        else {
            pager.find('.next_link').hide();
        }

        pager.data("curr",page);
        pager.children().removeClass("active");
        pager.children().eq(page+1).addClass("active");

    }
};

$(document).ready(function(){

    $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:5});

});</script>
    </div>
</body>
</html>
