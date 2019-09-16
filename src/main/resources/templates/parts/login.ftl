<#macro login path>
    <form action="${path}" method="post">
        <div><label> User Name :
                <div><input type="text" name="username"/></div> </label></div>
        <div><label> Password:
        <div><input type="password" name="password"/></div></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-outline-primary" type="submit">Sign in</button>
        <a href="/registration">Sign up</a>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Sign Out"/>
    </form>
</#macro>