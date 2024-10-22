<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/user-registration-validation.js"></script>
</head>
<body>
    <div class="registration-container">
        <h1>Registration Page</h1>
        <form action="/register" method="post" class="registration-form">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter your Username" required />
            <span id="username-status" class="status"></span><br />

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" pattern="[^ @]*@[^ @]*" placeholder="Enter your email" required />
            <span id="email-status" class="status"></span><br />

            <label for="countryCode">Country Code:</label>
            <select id="countryCode" name="countryCode" required></select><br />

            <label for="mobileNumber">Mobile:</label>
            <input type="text" id="mobileNumber" name="mobileNumber" placeholder="Enter your Mobile Number" required />
            <span id="mobile-status" class="status"></span><br />

            <label for="password">Password:</label>
            <div class="input-container">
                <input type="password" id="password" name="password" placeholder="Enter your Password" required />
                <span class="eye-icon" id="togglePassword">👁️</span>
            </div>
            <span id="password-status" class="status"></span><br />

            <label for="confirmPassword">Confirm Password:</label>
            <div class="input-container">
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your Password" required />
                <span class="eye-icon" id="toggleConfirmPassword">👁️</span>
            </div>
            <span id="confirm-password-status" class="status"></span><br />

            <input type="submit" value="Register" class="submit-btn" />
        </form>
    </div>
</body>
</html>
