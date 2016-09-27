console.log("status test!")

var https = require("https")

var clarity = { 
	"baseUrl" : "https://clarity-tst.hart.com/",
	"apiPath" : "api/v1"
}

describe("clarity", ()=>{
	
	describe("status", ()=> {

		it("should return the current endpoint status", ()=> {

			var statusUrl = clarity.baseUrl + clarity.apiPath + '/status'
			console.log('statusUrl', statusUrl)

		})
	})
})