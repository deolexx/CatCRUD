<%@page import="java.util.Calendar" %>
<%@taglib prefix="tag" uri="http://www.adehermawan.com/dateFormatter" %>
<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <!— Title —>
        <span class="mdl-layout-title">Cats Management System</span>
        <!— Add spacer, to align navigation to the right —>
        <div class="mdl-layout-spacer"></div>
        <!— Navigation. We hide it in small screens. —>

        <tag:formatDate date="<%=Calendar.getInstance().getTime()%>"
                        format="dd-MM-YYYY hh:mm"></tag:formatDate>


        <nav class="mdl-navigation mdl-layout—large-screen-only">
            <a class="mdl-navigation__link" href="/CMS/new">Add New cat</a>
            <a class="mdl-navigation__link" href="/CMS/list">List All Cats</a>
        </nav>
    </div>
</header>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">CMS</span>
    <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="/CMS/new">Add New Stuff</a>
        <a class="mdl-navigation__link" href="/CMS/list">List All Cats</a>
    </nav>
</div>