<!doctype html>
<html lang="en">
<head class="v_scrollbar">
    <meta charset="UTF-8"/>
    <title>Simple Form</title>
    <style>
        html.v_scrollbar {
            overflow-y: scroll;
        }
        html {
            height: 100%;
            font-size: 100%;
        }
        body {
            font-size: 14px;
            min-width: initial;
            background: none repeat scroll 0 0 #fff;
            color: #000;
            font-family: "Microsoft YaHei",WenQuanYi Micro Hei,"Heiti SC","Lucida Sans Unicode","Myriad Pro","Hiragino Sans GB",Verdana,simsun;
            font-size: 13px;
            min-height: 100%;
            min-width: 1080px;
            position: relative;
            margin: 0px;
        }

        .container {
            width: 90%;
            max-width: 1170px;
            margin-left: auto;
            margin-right: auto;
        }

        .main{
            background: none repeat scroll 0 0 #fff;
            border-radius: 0;
            padding: 30px;
            position: relative;
            border: 1px solid #e3e3e3;
            box-sizing: border-box;
            margin-bottom: 30px;
            text-shadow: 0 1px 0 #fff;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: inline-block;
            max-width: 100%;
            margin-bottom: 5px;
            font-weight: 700;
        }

        button, input, optgroup, select, textarea {
            margin: 0;
            font: inherit;
            color: inherit;
        }
        input {
            line-height: normal;
        }
        button, input, select, textarea {
            font-family: inherit;
            font-size: inherit;
            line-height: inherit;
        }
        .form-control {
            display: block;
            width: 100%;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        }

        p {
            margin: 0 0 10px;
        }
        .help-block {
            display: block;
            margin-top: 5px;
            margin-bottom: 10px;
            color: #737373;
        }


        .checkbox, .radio {
            position: relative;
            display: block;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        button, input, optgroup, select, textarea {
            margin: 0;
            font: inherit;
            color: inherit;
        }
        button {
            overflow: visible;
        }
        button, select {
            text-transform: none;
        }
        button, html input[type=button], input[type=reset], input[type=submit] {
            -webkit-appearance: button;
            cursor: pointer;
        }
        button, input, select, textarea {
            font-family: inherit;
            font-size: inherit;
            line-height: inherit;
        }
        .btn {
            display: inline-block;
            padding: 6px 12px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border: 1px solid transparent;
            border-radius: 4px;
        }
        .btn-default {
            color: #333;
            background-color: #fff;
            border-color: #ccc;
        }

        /*table*/
        table {
            border-spacing: 0;
            border-collapse: collapse;
        }
        table {
            background-color: transparent;
        }
        .table {
            width: 100%;
            max-width: 100%;
            margin-bottom: 20px;
        }
        caption {
            display: table-caption;
            text-align: -webkit-center;
        }

        td, th {
            padding: 0;
        }
        th {
            text-align: left;
        }
        .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
            padding: 8px;
            line-height: 1.42857143;
            vertical-align: top;
            border-top: 1px solid #ddd;
        }
        .table>thead>tr>th {
            vertical-align: bottom;
            border-bottom: 2px solid #ddd;
        }
        .table>caption+thead>tr:first-child>td, .table>caption+thead>tr:first-child>th, .table>colgroup+thead>tr:first-child>td, .table>colgroup+thead>tr:first-child>th, .table>thead:first-child>tr:first-child>td, .table>thead:first-child>tr:first-child>th {
            border-top: 0;
        }

        .simple-form .form-group label{
            float:left;
            width: 80px;
        }
        .simple-form .form-group span.content-line{
            display: block;
            margin-left: 80px;
            width: 300px;
            border-bottom: 1px solid #444;
            border-radius: 3px;
            text-align: center;
        }
        .content-line.underline{
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div id="header">
    <H2>
        FreeMarker Spring MVC
    </H2>
</div>

<h1>Error Page</h1>
<p>Application has encountered an error. Please contact support </p>

    Failed URL: ${url} <br/>
    Exception: <br/> ${exception.message}

</div>
</body>
</html>