"use strict";

var ClarityDriver = require("./ClarityDriver.js");

class ClarityApiDriver extends ClarityDriver {

	constructor(settings) {
		super(settings);
		this.settings = settings;
		this.baseUrl = settings.baseUrl;
	}

	login(email, password) {
		super.login(email, password);

		var request = {
			url: "https://clarity-stg.hart.com/mm/v2/token",
			method: "GET",
			headers: {
				"Content-type" : "application/json"
			},
			data: `{"email":"${email}","password":"${password}"}`
		}


	}
}

module.exports = ClarityApiDriver