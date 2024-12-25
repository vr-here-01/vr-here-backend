$(document).ready(function() {
	// Fetch countries on page load
	fetchCountries();

	// Mobile number validation on input
	$('#mobileNumber').on('input', function() {
		validateMobileNumber($(this).val());
	});
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

function validateMobileNumber(mobileNumber) {
	const mobileStatus = $('#mobile-status');
	const mobilePattern = /^[0-9]{10}$/; // Example pattern for 10-digit mobile numbers

	if (mobilePattern.test(mobileNumber)) {
		mobileStatus.text('Valid mobile number').css('color', 'green');
	} else {
		mobileStatus.text('Invalid mobile number').css('color', 'red');
	}
}