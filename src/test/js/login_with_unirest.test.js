"use strict";

var baseDir = process.env.PWD;

var token = require(baseDir + "src/main/js/clarity/requests/token.js");
var settings = require(baseDir + "src/test/resources/settings.json");

describe('clarity', ()=> {

	describe('login', () => {

		describe ('with unirest', ()=> {

			var unirest = require('unirest')

			it ('succeeds with valid credentials', (done)=> {
				var email = settings.users.admin.email;
				var password = settings.users.admin.password;

				var request = token.request(clarity, email, password);
				unirest
					.post(request.url)
					.headers(request.headers)
					.send(request.body)
					.end( function(error, response) {
				
						if (error) {
							console.log('=== ERROR ===');
							console.log(error);
							error.should.not.exist;
		
							done();
						}

						console.log('=== RESPONSE ===');
						console.log(response);
						done();
					})
			})


		})
	})
})