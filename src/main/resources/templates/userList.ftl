<#import "parts/common.ftl" as c>
<@c.page>
    Users' list
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/admin_control/${user.id}">Edit profile</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>