<html>
<head>
</head>
<g:javascript library="jquery" plugin="jquery"/>
<body>
    <g:each var="user" in="${users}">

        ${user.key} <a href="#" onclick="${remoteFunction(controller:"demo", action:"kill", params:[id:user?.value?.sessionId])}; location.reload()">terminate</a> <br/>

    </g:each>
    <hr/>
    <g:each var="appsession" in="${sessions}">

        ${appsession} <a href="#" onclick="${remoteFunction(controller:"demo", action:"kill", params:[id:appsession.key])}; location.reload()">terminate</a> <br/>

    </g:each>
</body>
</html>