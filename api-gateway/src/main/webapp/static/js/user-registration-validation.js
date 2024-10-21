$(document).ready(function() {
	var debounceTimer;

	// Username validation with debounce
	$('#username').on('keyup', function() {
		var username = $(this).val();
		$('#username-status').text('Checking...');

		clearTimeout(debounceTimer);

		debounceTimer = setTimeout(function() {
			if (username.length > 0) {
				$.ajax({
					url: '/check-username', // Your backend URL for checking username availability
					type: 'GET',
					data: { username: username },
					success: function(response) {
						if (response === 'valid') {
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
				$('#username-status').text('');
			}
		}, 300); // 300ms debounce time
	});

	// Mobile number validation
	$('#mobileNumber').on('input', function() {
		var mobile = $(this).val();
		var regex = /^[0-9]{10}$/; // Example pattern: 10 digits for US numbers, modify as per your requirement

		if (regex.test(mobile)) {
			$('#mobile-status').text('Valid mobile number').removeClass('invalid').addClass('valid');
		} else {
			$('#mobile-status').text('Invalid mobile number').removeClass('valid').addClass('invalid');
		}
	});

	function fetchCountries() {
		$.ajax({
			url: '/api/countries', // Internal endpoint to fetch country codes
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
			const option = $('<option></option>')
				.val(country.idd.root + country.idd.suffixes[0]) // Use root and suffix for dialing code
				.text(country.name.common);
			countryDropdown.append(option);
		});
	}
});
