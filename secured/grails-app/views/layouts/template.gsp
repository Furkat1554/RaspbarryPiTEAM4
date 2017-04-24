<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Rasp-TV"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>
    <div id="wrapper">
        <div id="wrapper2">
            <div id="wrapper3">
                <div id="top-menu">
                </div>
                <div id="header">
                    <h1>Raspberry TV</h1>
                    <div id="main-menu">
                        <ul>
                            <li><a href="${createLink(uri:'/')}">Overview</a></li>
                            <li><a href="${createLink(controller: 'secured.announcement')}">Post Announcement</a></li>
                            <li><a href="${createLink(uri:'/control')}" class="controlpi">Control Pis</a></li>
                            <li><a href="${createLink(uri:'/event')}" class="event">Event Log</a></li>
                            <li><a href="${createLink(uri:'/configure')}" class="configure">App Configuration</a></li>
                            <li><g:link controller="logout">Logout</g:link></li>
                        </ul>
                    </div>
                </div>
                <div id="main">
                    <div id="sidebar">
                        <h1>Sidebar</h1>
                    </div>
                    <div id="content">
                        <g:layoutBody/>
                    </div>
                </div>
            </div>
            <div id="footer">
                <div class="bgl"><div class="bgr">
                    Powered by <a href="">COM-14b</a> © 2017
                </div></div>
            </div>
        </div>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
