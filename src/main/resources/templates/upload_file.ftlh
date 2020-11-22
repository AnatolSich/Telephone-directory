<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spring Boot upload multiple file with freemarker</title>
    </head>

    <body>
        <form name="upload" enctype="multipart/form-data" action="/upload" method="POST">
            <p>
                <div><input id="file" type="file" name="file"></div>
                <div style="padding-top:20px" id="selectedFile"></div>
            </p>
            <p>
                <input type="submit" value="Upload file">
            </p>
        </form>
    </body>
</html>