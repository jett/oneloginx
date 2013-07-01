<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${session.userName}">
        <g:form action="logout">
            you are logged in ${session.userName}<br/>
            <g:submitButton name="logout"/>
        </g:form >
    </g:if>
    <g:else>

        <g:form action="authenticate">
            <g:if test="${flash.message}">
                ${flash.message}<br/>
            </g:if>
            userid: <g:textField name="userid"/><br/>
            <g:submitButton name="login"/>
        </g:form>

    </g:else>

</body>
</html>