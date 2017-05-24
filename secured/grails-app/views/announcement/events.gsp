<!doctype html>
<html>
    <head>
        <meta name="layout" content="template"/>
        <title>Rasp-TV : Event Log</title>
        <g:set var="entityName" value="${message(code: 'announcement.label', default: 'Event')}" />
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    </head>
    <body>
        <a href="#list-pointOfInterest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="list-pointOfInterest" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${announcementList}" properties="['title','postedDate','endDate', 'block', 'author']"/>
            <div class="pagination">
                <g:paginate total="${announcementCount ?: 0}" />
            </div>
        </div>
    </body>
</html>