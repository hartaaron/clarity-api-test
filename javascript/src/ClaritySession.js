class ClaritySession {

	constructor(baseUrl, session) {
		this._baseUrl = baseUrl;
		this._session = session;
		this.userAgent = "Clarity Test Automation";
	}	
	
	get baseUrl() { return this._baseUrl; }
	set baseUrl(baseUrl) { this._baseUrl = baseUrl; }

	get userId() { return this.session['user_id']; }
	set userId(userId) { this.session['user_id'] = userId; }

	get accessToken() { return this.session['x-access-token']; }
	set accessToken(accessToken) { this.session['x-access-token'] = accessToken; }

	get bgToken() { return this.session['x-bg-token']; }
	set bgToken(bgToken) { this.session['x-bg-token'] = bgToken; }

	get cookie() { return this.session.cookie; }
	set cookie(cookie) { this.session.cookie = cookie; }
}

module.exports = ClaritySession
