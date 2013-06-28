<html>
<head>
</head>
<body>
    <g:if test="${session.user}">
        <g:form action="logout">
            you are logged in ${session.user}<br/>
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