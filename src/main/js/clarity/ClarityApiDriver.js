"use strict";

var ClarityDriver = require("./ClarityDriver.js");

class ClarityApiDriver extends ClarityDriver {

	constructor(settings, session ) {
		super(settings);
		this.baseUrl = settings.baseUrl;
		this.session = { "user_id": null, "x-access-token": null, "x-bg-token": null, }
		this.cookie = { "__cfduid": null }
		this.userAgent = "Clarity API driver";

		if (session) { this.session = session; }
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
	
	get baseURL() { return this._baseURL; }
	set baseURL(baseURL) { this._baseURL = baseURL; }

	get userId() { return this.session['user_id']; }
	set userId(userId) { this.session['user_id'] = userId; }

	get accessToken() { return this.session['x-access-token']; }
	set accessToken(accessToken) { this.session['x-access-token'] = accessToken; }

	get bgToken() { return this.session['x-bg-token']; }
	set bgToken(bgToken) { this.session['x-bg-token'] = bgToken; }

	get cookie() { return this.session.cookie; }
	set cookie(cookie) { this.session.cookie = cookie; }
}

module.exports = ClarityApiDriver