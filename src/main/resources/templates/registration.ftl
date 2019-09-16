<#import "parts/common.ftl" as c>

<@c.page>
Sing up
    <div>
        ${message?ifExists}
    </div>
    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-outline-primary" type="submit">Sign up</button>
    </form>
</@c.page>