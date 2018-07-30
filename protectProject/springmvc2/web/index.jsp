<html>
<body>
        <a href="springMvc/testRedirect">Test Redirect</a>
        <br><br>

        <a href="springMvc/testView">Test View</a>
        <br><br>

        <form action="/springMvc/testPojo" method="post">
            <input type="hidden" name="id" value="1">
            age:<input type="text" name="age"><br>
            username:<input type="text" name="username"/><br>
            <input type="submit" name="submit"/>

        </form>
        <br><br>



        <a href="springMvc/testSessionAttribute">Test SessionAttribute</a>
        <br><br>

        <a href="springMvc/testMap">Test Map</a>
        <br><br>

        <a href="springMvc/testModelAndView">Test ModelAndView</a>
        <br><br>

        <a href="springMvc/testServletAPI">Test ServletAPI</a>
        <br><br>

        <form action="/springMvc/testPojo" method="post">
            username:<input type="text" name="username"/><br>
            password:<input type="password" name="password"/><br>
            city:<input type="text" name="address.city"/><br>
            province:<input type="text" name="address.province"/><br>
            <input type="submit" name="submit"/>

        </form>




        <a href="springMvc/testCookieValue">Test CookieValue</a>
        <br><br>

        <form action="springMvc/testRest/1" method="POST">
            <input type="hidden" name="_method" value="PUT">
            <input type="submit" value="TestRest PUT">
        </form>
        <br><br>

        <form action="springMvc/testRest/1" method="POST">
            <input type="hidden" name="_method" value="DELETE">
            <input type="submit" value="TestRest DELETE">
        </form>
        <br><br>

        <form action="springMvc/testRest" method="POST">
            <input type="submit" value="TestRest Post">
        </form>
        <br><br>

        <a href="springMvc/testRequestHeader">Test RequestHeader</a>
        <br><br>

        <a href="springMvc/testRequestParam?username=qinglin&age=19">Test RequestParam</a>
        <br><br>

        <a href="springMvc/testRest/1">Test Rest Get</a>
        <br><br>

        <a href="springMvc/testPathVariable/1">Test PathVariable</a>
        <br><br>
        <a href="springMvc/testAnt/*/abc">TestAnT</a>
        <br><br>
        <a href="springMvc/testParamsAndHeaders">TestParamsAndHeaders</a>
        <br><br>
        <form action="springMvc/testMethod" method="POST">
            <input type="submit" value="submit">
        </form>
        <br><br>
        <a href="springMvc/springMvcServlet">SpringMvcServlet</a>
        <br><br>
        <a href="helloworld">HelloWorld</a>
</body>
</html>
