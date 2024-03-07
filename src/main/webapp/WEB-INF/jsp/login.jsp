<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 88vh;
}

form {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 300px;
}

h2 {
	text-align: center;
	color: #333;
}

label {
	display: block;
	margin-bottom: 8px;
	color: #555;
}

input {
	width: 100%;
	padding: 8px;
	margin-bottom: 16px;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: #4caf50;
	color: #fff;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #45a049;
}

.alert-message {
	color: green;
	display: block;
	padding: 15px;
	font-size: 20px;
	text-align: center;
	font-weight: 600;
}
</style>
</head>
<body>
	<div class="container">
	${SPRING_SECURITY_LAST_EXCEPTION.message }
		<div class="form">
			<form class="form" action="/login" method="POST" >
				<h2>Login Form</h2>

				<label for="email">Email Address:</label>
				<input type="email" name="email" required />

				<label for="password">Password:</label>
				<input type="password" name="password" required />

				<input type="submit" name="submit" value="submit">
			</form>
			<a href="/registration">Sign Up</a>
		</div>
		
	</div>
</body>
</html>
