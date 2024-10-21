<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/styles.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/user-registration-validation.js"></script>
<%-- <script
	src="${pageContext.request.contextPath}/static/js/countryFetcher.js"></script> --%>
</head>
<body>
	<h1>Registration Page</h1>
	<form action="/register" method="post">
		Username: <input type="text" id="username" name="username"
			placeholder="Enter your Username" /> <span id="username-status"></span><br />

		Email: <input type="text" name="email" pattern="[^ @]*@[^ @]*"
			placeholder="Enter your email" /><br /> Mobile:<select
			id="countryCode" name="countryCode"></select> <input type="text"
			id="mobileNumber" name="mobileNumber"
			placeholder="Enter your Mobile Number" /> <span id="mobile-status"></span>
		<br /> Password: <input type="password" name="password"
			placeholder="Enter your Password" /> <br /> <input type="submit"
			value="Register" />
	</form>
</body>
</html>
