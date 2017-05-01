<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="template" />
        <g:set var="entityName" value="${message(code: 'announcement.label', default: 'Announcement')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-pointOfInterest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-pointOfInterest" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="announcement"/>
            <g:form resource="${this.announcement}" method="DELETE">
                <fieldset class="buttons">
                    <g:if test="${this.announcement.mediaFileUrl == null}">
                        <g:link class="edit" action="editMediaFile" resource="${this.announcement}"><g:message code="announcement.featuredImageUrl.edit.label" default="Upload Media file" /></g:link>
                    </g:if>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
