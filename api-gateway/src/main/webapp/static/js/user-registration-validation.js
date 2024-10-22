$(document).ready(function() {

    // Username validation without debounce
    $('#username').on('keyup', function() {
        var username = $(this).val();
        if (username.length > 0) {
            $('#username-status').text('Checking...');

            $.ajax({
                url: '/username/' + encodeURIComponent(username), // Updated URL to include username as a path variable
                type: 'GET',
                success: function(response) {
                    if (response) {
                        $('#username-status').text('Username available').removeClass('invalid').addClass('valid');
                    } else {
                        $('#username-status').text('Username already taken').removeClass('valid').addClass('invalid');
                    }
                },
                error: function() {
                    $('#username-status').text('Error checking username').removeClass('valid').addClass('invalid');
                }
            });
        } else {
            $('#username-status').text(''); // Clear the status if no input
        }
    });

    // Mobile number validation
    $('#mobileNumber').on('input', function() {
        var mobile = $(this).val();
        var regex = /^[0-9]{10}$/; // 10 digits for example, modify as per requirement

        if (regex.test(mobile)) {
            $('#mobile-status').text('Valid mobile number').removeClass('invalid').addClass('valid');
        } else {
            $('#mobile-status').text('Invalid mobile number').removeClass('valid').addClass('invalid');
        }
    });

    // Email validation as user types
    $('#email').on('input', function() {
        var email = $(this).val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Basic email regex

        if (emailRegex.test(email)) {
            $('#email-status').text('Valid email address').removeClass('invalid').addClass('valid');
        } else {
            $('#email-status').text('Invalid email address').removeClass('valid').addClass('invalid');
        }
    });

    // Check if email exists after the user finishes typing
    $('#email').on('blur', function() {
        var email = $(this).val();
        if (email) {
            $('#email-status').text('Checking...');

            $.ajax({
                url: '/email/' + encodeURIComponent(email), // Updated URL to include email as a path variable
                type: 'GET',
                success: function(response) {
                    if (response) { // API returns true if email exists
                        $('#email-status').text('Email already registered').removeClass('valid').addClass('invalid');
                    } else {
                        $('#email-status').text('Email is available').removeClass('invalid').addClass('valid');
                    }
                },
                error: function() {
                    $('#email-status').text('Error checking email').removeClass('valid').addClass('invalid');
                }
            });
        }
    });

    // Password validation as user types
    $('#password, #confirmPassword').on('input', function() {
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();

        // Check if the passwords match
        if (password === confirmPassword && password.length > 0) {
            $('#password-status').text('Passwords match').removeClass('invalid').addClass('valid');
        } else {
            $('#password-status').text('Passwords do not match').removeClass('valid').addClass('invalid');
        }
    });

    // Toggle password visibility for password field
    $('#togglePassword').on('click', function() {
        const passwordInput = $('#password');
        const type = passwordInput.attr('type') === 'password' ? 'text' : 'password';
        passwordInput.attr('type', type);
        $(this).text(type === 'password' ? '👁️' : '🙈'); // Change icon
    });

    // Toggle password visibility for confirm password field
    $('#toggleConfirmPassword').on('click', function() {
        const confirmPasswordInput = $('#confirmPassword');
        const type = confirmPasswordInput.attr('type') === 'password' ? 'text' : 'password';
        confirmPasswordInput.attr('type', type);
        $(this).text(type === 'password' ? '👁️' : '🙈'); // Change icon
    });

    // Fetch and populate country codes
    fetchCountries();

    function fetchCountries() {
        $.ajax({
            url: 'https://restcountries.com/v3.1/all', // Internal endpoint to fetch country codes
            method: 'GET',
            success: function(countries) {
                populateCountryDropdown(countries);
            },
            error: function(error) {
                console.error('Error fetching countries:', error);
            }
        });
    }

    function populateCountryDropdown(countries) {
        const countryDropdown = $('#countryCode');
        countryDropdown.append('<option value="">Select Country</option>'); // Default option

        countries.forEach(country => {
            if (country.idd) { // Check if idd is available
                const root = country.idd.root;
                const suffix = country.idd.suffixes.length > 0 ? country.idd.suffixes[0] : ''; // Use first suffix if available
                const code = root + suffix;

                countryDropdown.append(`<option value="${code}">${country.name.common} (${code})</option>`);
            }
        });
    }
});
