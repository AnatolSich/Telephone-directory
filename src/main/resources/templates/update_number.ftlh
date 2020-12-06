<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Number</title>
</head>
<body>
<h1>Update number</h1>
<form action="/phoneNumber/update/${phoneNumber.number}" method="post" name="Update number">
    <div>User:</div>
    <input type="text" name="userId" value="${phoneNumber.user.id}"/>
    <div>Company:</div>
    <input type="text" name="companyId" value="${phoneNumber.company.id}"/>
    <input type="submit" value="Submit"/>
</form>

<div id="header">
    <H2>
        User List
    </H2>
</div>
<div id="content">
    <table class="datatable">
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.first_name}</td>
                <td>${user.last_name}</td>
            </tr>
        </#list>
    </table>
</div>


<div id="header">
    <H2>
        Company List
    </H2>
</div>
<div id="content">
    <table class="datatable">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Code</th>
        </tr>
        <#list companies as company>
            <tr>
                <td>${company.id}</td>
                <td>${company.name}</td>
                <td>${company.code}</td>
            </tr>
        </#list>
    </table>
</div>

</body>
</html>
