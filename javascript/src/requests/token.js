function token_request(clarity, email, password) {
	var request = {
		method: "POST",
		url: clarity.baseUrl + "/mm/v2/token",
		headers: {
			"User-agent": clarity.userAgent,
			"Content-type": "application/json",
			"Accept": "application/json",
		},
		body: JSON.stringify({email:email, password: password})
	}

	return request;
}

module.exports.request = token_request
