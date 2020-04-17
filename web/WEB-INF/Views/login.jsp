<%--
  Created by IntelliJ IDEA.
  User: gusiy
  Date: 2020/3/19
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!~</title>
</head>
<script language="JavaScript">
    function doRegister() {
       this.formZ.action="/user/register";
       this.formZ.submit();
    }
    function doLogin() {
        this.formZ.action="/user/login";
        this.formZ.submit();
    }
</script>
<body>
    <Center>
        <h1>Welcome to Login/Register</h1>
        <form method="post" name="formZ">
            <table>
                <tr>
                    <td>Username :</td>
                    <td><input name="name" type="text" value="user"/></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><input name="password" type="password"/></td>
                </tr>
                <tr>
                    <td><input type="button" value="Register" onclick="doRegister()"></td>
                    <td><input type="button" value="Login" onclick="doLogin()"></td>
                </tr>
        </table>
        </form>
                <h4><%
                    String text1=(String) request.getAttribute("isWrong");
                    if(text1!=null)
                        out.println(text1);
                    String text2=(String) request.getAttribute("register");
                    if(text2!=null)
                        out.println(text2);
                %></h4>
    </Center>
</body>
</html>
