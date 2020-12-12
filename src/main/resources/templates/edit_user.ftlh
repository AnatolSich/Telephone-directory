<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit user</title>
</head>
<body>
<h1>Edit user</h1>
<form action="/user/edit/${user.id}" method="post" name="Edit user">
    <div>First Name:</div>
    <input type="text" name="first_name" value="${user.first_name}"/>
    <div>Last name:</div>
    <input type="text" name="last_name" value="${user.last_name}"/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
