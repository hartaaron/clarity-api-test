console.log("======================");
console.log(`start of ${__filename}`);
console.log("======================");


describe ("token", ()=> {

	var token = require("../src/requests/token.js");

	it ("should generate a token request JSON", ()=> {
		var email = 'aaron.evans+devclarityadmin@hart.com';
		var password = 'Secret!1';
		var request = token.request(clarity, email, password);

		request.url.should.startWith(clarity.baseUrl);

	});

	it ("should send a token request using superagent-promise-plugin", (done)=> {
		// var Promise = this.Promise || require('promise');
		var superagentPromisePlugin = require("superagent-promise-plugin");
		var superagent = superagentPromisePlugin.patch(require("superagent"));

		var user = settings.users.stageadmin;
		var request = token.request(clarity, user.email, user.password);
		
		superagent
			.post(request.url)
			.set("Accept", "application/json")
			.set("Content-Type", "application/json")
			.send(request.body)
			.then( (response) => {
				console.log("RESPONSE");
				console.log(response);
				done();
			})
			.catch( (error) => {
				console.log("ERROR");
				console.log(error.error);
				throw error;
			})
	})
});