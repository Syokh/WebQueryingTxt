<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet Page</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<script>
    function Button() {
        $('.content').hide();
        var param = {
            limit: $("#limit").val(),
            q: $("#q").val(),
            length: $("#length").val(),
            includeMetaData: $("#includeMetaData").val()
        };
        $.ajax({

            url: "servletapi",
            contentType: 'application/json',
            data: JSON.stringify(param),
            type: 'POST',
            success: Parameter,

            error: function (xhr, status, errorThrown) {
                alert('  status: ' + status + '. ' + errorThrown);
            }
        });
    }
    function Parameter(output) {
        $.each(output, function (index, value) {
            $("#" + index).html(value);
        });
        $('.content').show();
    }
</script>

<form action="" method="post">
    <input type="hidden" name="j_spring_security_check" value="true">

    <p id="loginPage">Servlet Page</p>

    <p>
        <label for="limit">limit:</label>
        <input type="text" id="limit" name="limit"/>
    </p>

    <p>
        <label for="q">q:</label>
        <input type="text" id="q" name="q"/>
    </p>

    <p>
        <label for="length">length:</label>
        <input type="text" id="length" name="length"/>
    </p>

    <p>
        <label for="includeMetaData">includeMetaData:</label>
        <input type="text" id="includeMetaData" name="includeMetaData"/>
    </p>

    <p>
        <input type="button" value="Search" id="loginButton" class="button" onclick="Button();"/>
        <input type="reset" value="Reset">
    </p>
</form>
<div class="content" style="color: blue">
    <div id="0"></div>
    <div id="1"></div>
    <div id="2"></div>
    <div id="3"></div>
    <div id="4"></div>
    <div id="5"></div>
    <div id="6"></div>
</div>
</body>
</html>
