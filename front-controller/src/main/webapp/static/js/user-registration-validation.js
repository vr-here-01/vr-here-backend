$(document).ready(function() {
    // Username validation without debounce
    $('#username').on('keyup', function() {
        var username = $(this).val();
        if (username.length > 0) {
            $('#username-status').text('Checking...');

            $.ajax({
                url: '/api/username/' + encodeURIComponent(username),
                type: 'GET',
                success: function(response) {
                    if (!response) {
                        $('#username-status').text('Username already taken').removeClass('valid').addClass('invalid');
                    } else {
                        $('#username-status').text('').removeClass('valid');
                    }
                },
                error: function() {
                    $('#username-status').text('Error checking username').removeClass('valid').addClass('invalid');
                }
            });
        } else {
            $('#username-status').text('');
        }
    });

    // Mobile number validation
    $('#mobileNumber').on('input', function() {
        var mobile = $(this).val();
        var regex = /^[0-9]{10}$/;

        if (regex.test(mobile)) {
            $('#mobile-status').text('Valid mobile number').removeClass('invalid').addClass('valid');
        } else {
            $('#mobile-status').text('Invalid mobile number').removeClass('valid').addClass('invalid');
        }
    });

    // Email validation as user types
    $('#email').on('input', function() {
        var email = $(this).val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailRegex.test(email)) {
            $('#email-status').text('Invalid email address').removeClass('valid').addClass('invalid');
        } else {
            $('#email-status').text('').removeClass('invalid');
        }
    });

    // Check if email exists after the user finishes typing
    $('#email').on('blur', function() {
        var email = $(this).val();
        if (email) {
            $('#email-status').text('Checking...');

            $.ajax({
                url: '/api/email/' + encodeURIComponent(email),
                type: 'GET',
                success: function(response) {
                    if (!response) {
                        $('#email-status').text('Email already registered').removeClass('valid').addClass('invalid');
                    } else {
                        $('#email-status').text('').removeClass('valid');
                    }
                },
                error: function() {
                    $('#email-status').text('Error checking email').removeClass('valid').addClass('invalid');
                }
            });
        }
    });

    // Password visibility toggle
    $('#togglePassword').on('click', function() {
        const passwordInput = $('#password');
        const type = passwordInput.attr('type') === 'password' ? 'text' : 'password';
        passwordInput.attr('type', type);
        $(this).text(type === 'password' ? '👁️' : '🙈');
    });

    $('#toggleConfirmPassword').on('click', function() {
        const confirmPasswordInput = $('#confirmPassword');
        const type = confirmPasswordInput.attr('type') === 'password' ? 'text' : 'password';
        confirmPasswordInput.attr('type', type);
        $(this).text(type === 'password' ? '👁️' : '🙈');
    });

    // Password strength validation
    const passwordCriteria = {
        minLength: 8,
        uppercase: /[A-Z]/,
        lowercase: /[a-z]/,
        numbers: /[0-9]/,
        specialChars: /[!@#$%^&*(),.?":{}|<>]/
    };

    function calculatePasswordStrength(password) {
        let strength = 0;
        if (password.length >= passwordCriteria.minLength) strength++;
        if (passwordCriteria.uppercase.test(password)) strength++;
        if (passwordCriteria.lowercase.test(password)) strength++;
        if (passwordCriteria.numbers.test(password)) strength++;
        if (passwordCriteria.specialChars.test(password)) strength++;

        return strength;
    }

    $('#password').on('input', function() {
        const password = $(this).val();
        const strength = calculatePasswordStrength(password);
        const strengthBar = $('#strength-bar');
        const strengthText = $('#strength-text');

        strengthBar.removeClass();

        switch (strength) {
            case 1:
            case 2:
                strengthBar.css('width', '25%').addClass('weak').css('background-color', 'red');
                strengthText.text('Weak').css('color', 'red');
                break;
            case 3:
                strengthBar.css('width', '50%').addClass('medium').css('background-color', 'orange');
                strengthText.text('Medium').css('color', 'orange');
                break;
            case 4:
                strengthBar.css('width', '75%').addClass('strong').css('background-color', 'yellowgreen');
                strengthText.text('Strong').css('color', 'yellowgreen');
                break;
            case 5:
                strengthBar.css('width', '100%').addClass('very-strong').css('background-color', 'green');
                strengthText.text('Very Strong').css('color', 'green');
                break;
        }
    });

    // Fetch and populate country codes based on user location
    getUserCountryCode();

    function getUserCountryCode() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;

                // Get the country code from the backend API with latitude and longitude
                $.get(`/api/location?lat=${latitude}&lng=${longitude}`, function(data) {
                    if (data && data.countryCode) {
                        const countryCode = data.countryCode; // Get the country code
                        // Set the selected country code in the dropdown
                        $('#countryCode').val(countryCode).change(); // Ensure this triggers a change event
                    }
                }).fail(function() {
                    console.error('Failed to retrieve country code from the backend API');
                    populateDefaultCountries(); // Fallback if geolocation fails
                });
            }, function(error) {
                console.error('Error getting location:', error);
                populateDefaultCountries(); // Fallback if user denies location access
            });
        } else {
            console.error('Geolocation is not supported by this browser.');
            populateDefaultCountries(); // Fallback if geolocation is not supported
        }
    }

    function fetchCountries() {
        $.ajax({
            url: 'https://restcountries.com/v3.1/all',
            method: 'GET',
            success: function(countries) {
                populateCountryDropdown(countries);
            },
            error: function(error) {
                console.error('Error fetching countries:', error);
                populateDefaultCountries(); // Fallback if fetching fails
            }
        });
    }

    function populateCountryDropdown(countries) {
        const countryDropdown = $('#countryCode');
        countryDropdown.empty(); // Clear existing options
        countryDropdown.append('<option value="">Select your country</option>'); // Default option

        countries.forEach(country => {
            if (country.idd) {
                const root = country.idd.root;
                const suffix = country.idd.suffixes.length > 0 ? country.idd.suffixes[0] : '';
                const code = root + suffix;

                countryDropdown.append(`<option value="${code}">${country.name.common} (${code})</option>`);
            }
        });
    }

    function populateDefaultCountries() {
        const defaultCountries = [
            { name: 'United States', code: '+1' },
            { name: 'India', code: '+91' },
            { name: 'United Kingdom', code: '+44' },
            { name: 'Canada', code: '+1' },
            { name: 'Australia', code: '+61' }
        ];

        const countryDropdown = $('#countryCode');
        countryDropdown.empty(); // Clear existing options
        countryDropdown.append('<option value="">Select your country</option>'); // Default option

        defaultCountries.forEach(country => {
            countryDropdown.append(`<option value="${country.code}">${country.name} (${country.code})</option>`);
        });
    }

    // Prevent form submission if validations fail
    $('#registrationForm').on('submit', function(event) {
        let valid = true;
        $('.status').each(function() {
            if ($(this).hasClass('invalid')) {
                valid = false;
                return false; // Exit the loop
            }
        });
        if (!valid) {
            event.preventDefault(); // Prevent form submission
            alert('Please fix the errors in the form before submitting.');
        }
    });
});
