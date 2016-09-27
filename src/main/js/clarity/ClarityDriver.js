"use strict";

class ClarityDriver {

	constructor(settings) {
		this.log("creating Clarity driver instance with settings: ");
		this.log(settings);
		this.settings = settings;
	}

	log(message) {
		console.log(message);
	}

	login(email, password) {
		this.log(`logging in to Clarity with email: ${email} and password: ${password}`);
	}

	logout() {
		this.log(`logging out of Clarity`);
	}

	get_user_settings() {}



};

module.exports = ClarityDriver;