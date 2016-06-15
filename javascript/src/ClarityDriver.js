"use strict";

class ClarityDriver {

	constructor(settings) {
		log("creating Clarity driver instance with settings: ");
		log(settings);
		this.settings = settings;
	}

	log(message) {
		console.log(message);
	}

	login(email, password) {
		log(`logging in to Clarity with email: ${email} and password: ${password}`);
	}

	logout() {
		log(`logging out of Clarity`);
	}

	get_user_settings() {}



};

module.exports = ClarityDriver;