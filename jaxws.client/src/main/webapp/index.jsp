<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu page to call JAX-WS Service</title>
    </head>
    <body>
        <h1>Menu page to call JAX-WS Service</h1>
        <table>
        	<form action="client" method="get">
        	<tr><td>Mode:</td><td><select name="mode">
        		<option value="proxy">proxy</option>
        		<option value="noproxy">noproxy</option></select></td></tr>
            <tr><td>Proxy Host:</td><td>
            	<input type="text" name="porxyhost" value="localhost" size="20"></td></tr>
            <tr><td>Proxy Port:</td><td>
            	<input type="text" name="porxyport" value="8080" size="20"></td></tr>
            <tr><td>Sleep Time(ms):</td><td>
            	<input type="text" name="time" value="20000" size="20"></td></tr>
            <tr><td>JAX-WS Host:</td><td>
            	<input type="text" name="host" value="localhost" size="20"></td></tr>
            <tr><td>JAX-WS Port:</td><td>
            	<input type="text" name="port" value="9443" size="20"></td></tr>
             <tr><td></td><td><input type="submit" value="Send request"></td></tr>
            </form>
        </table>
    </body>
</html>
