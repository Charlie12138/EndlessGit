<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <head>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#testJson").click(function(){
                var url = this.href;
                var args = {};
                $.post(url, args, function(data){
                    for(var i = 0; i < data.length; i++){
                        var id = data[i].id;
                        var lastName = data[i].lastName;

                        alert(id + ": " + lastName);
                    }
                });
                return false;
            });
        })
    </script>
</head>
<body>
<h2>Hello World!</h2>
<form action="testFileUpload" method="POST" enctype="multipart/form-data">
    File: <input type="file" name="file"/>
    Desc: <input type="text" name="desc"/>
    <input type="submit" value="Submit"/>
</form>

<br><br>
<a href="emps">list employees</a>
<br><br>
<a href="testJson">Test Json</a>

</body>
</html>
