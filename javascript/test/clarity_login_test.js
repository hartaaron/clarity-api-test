"use strict";

var ClarityApiDriver = require("../src/ClarityApiDriver.js");

describe ( "Clarity", ()=> {

	describe ( "Login", ()=> {
		
		// it ( "with valid email and password", ()=> {
		// 	var user = settings.users.admin;
			
		// 	var clarity = new ClarityApiDriver(settings);

		// 	clarity.login(user.email, user.password)

		// 	assert(true)
		// });



		var user_id;
		var x_access_token;

		it ( "using supertest", (done)=> {


			var supertest = require("supertest");
			var api = supertest(settings.baseUrl);

			var user = settings.users.admin;
			var body = `{"email":"${user.email}","password":"${user.password}"}`;


			api.post("/token")
				.set("Accept", "application/json")
				.set("Content-type", "application/json")
				.send(body)
				.expect(200)
				.expect("Content-type", /json/)
				.end( (err, res)=> {
					expect(err).to.not.exist;
					expect(res).to.exist;
					expect(res.body).to.exist;
					console.log(res.body);
				
					expect(res.body.success).to.be.true;
					expect(res.body.code).to.equal(200);
					expect(res.body.data).to.not.be.null;

					expect(res.body.data["user_id"]).to.exist;
					expect(res.body.data["x-access-token"]).to.exist;
					
					var data = res.body.data

					var USER_ID_LENGTH = 32;
					var X_ACCESS_TOKEN_LENGTH = 191;

					res.body.data["user_id"].length.should.equal(USER_ID_LENGTH);
					res.body.data["x-access-token"].length.should.equal(X_ACCESS_TOKEN_LENGTH);
				
					user_id = res.body.data["user_id"];
					x_access_token = res.body.data["x-access-token"];

					// console.log("user_id: " + user_id);
					// console.log("x_access_token: " + x_access_token);

					done();

				})

		});

		it ( "should have set the user_id and x_access_token", ()=> {
			expect(user_id).to.not.be.null;
			expect(x_access_token).to.not.be.null;
			console.log("user_id: " + user_id);
			console.log("x_access_token: " + x_access_token);

		});
	});
});