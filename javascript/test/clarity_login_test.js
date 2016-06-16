"use strict";

describe ( "Clarity", ()=> {

	var ClarityApiDriver = require("../src/ClarityApiDriver.js");
	console.log(settings.environments.STAGE);
	var clarity = new ClarityApiDriver(settings.environments.STAGE);

	describe ( "Login", ()=> {

		var user = settings.users.stageadmin;
		var body = `{"email":"${user.email}","password":"${user.password}"}`;
		var session = {};
		
		describe ( "using supertest", ()=> {

			var supertest = require("supertest");
			var client = supertest(settings.baseUrl);

			it ( "should succeed with valid credentials", (done)=> {

				client
					.post("/token")
					.set("Accept", "application/json")
					.set("Content-type", "application/json")
					.send(body)
					.expect(200)
					.expect("Content-type", /json/)
					.end( (error, response)=> {
						expect(error).to.not.exist;
						expect(response).to.exist;
						expect.response.type.to.equal('json');
						expect(response.body).to.exist;
						console.log(response.body);
					
						expect(response.body.success).to.be.true;
						expect(response.body.code).to.equal(200);
						expect(response.body.data).to.not.be.null;

						expect(response.body.data["user_id"]).to.exist;
						expect(response.body.data["x-access-token"]).to.exist;
						
						var USER_ID_LENGTH = 32;
						var X_ACCESS_TOKEN_LENGTH = 191;

						response.body.data["user_id"].length.should.equal(USER_ID_LENGTH);
						response.body.data["x-access-token"].length.should.equal(X_ACCESS_TOKEN_LENGTH);
					
						clarity.session["user_id"] = response.body.data["user_id"];
						clarity.session["x-access-token"] = response.body.data["x-access-token"];
						clarity.session["Cookie"] = response.header["cookie"]

						console.log(this.fullTitle());
						done();
					});
			});
		}); // end using superagent

		describe ( "using superagent", ()=> {

			var superagentPromisePlugin = require("superagent-promise-plugin");
			var client = superagentPromisePlugin.patch(require("superagent"));

		});

		describe ( "using unirest", ()=> {

			var unirest = require("unirest")
			var token = require("../src/requests/token.js");


			it ( "should succeed with valid credentials", (done)=> {

				var email = settings.users.admin.email;
				var password = settings.users.admin.password;
				
				var request = token.request(clarity, email, password);

				console.log("=== REQUEST ===");
				console.log(request);

				console.log()
				console.log(request.url)
				console.log(request.body)

				unirest
					.post(request.url)
					.headers(request.headers)
					.send(JSON.stringify(request.body))
					.end( function(error, response) {
				
						if (error) {
							console.log("=== ERROR ===");
							console.log(error.code);
							console.log(error.body)
							console.log(error.error)
							console.log("=== ERROR.REQ ===");
							console.log(error.req.href);
							console.log(error.req.method);
							console.log(error.req.path);
							console.log(error.req._headers);
							console.log(error.req.body);
							error.error.should.not.exist;
		
							done();
						}

						console.log("=== RESPONSE ===");
						console.log(response);
						done();
					});
			});
		});	// end using unirest
	}); // end login
});