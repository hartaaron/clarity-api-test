console.log("====================");
console.log(" SETUP ");
console.log("====================");

process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

var path = require('path');

var chai = require('chai');

global.assert = chai.assert;
global.expect = chai.expect;
global.should = chai.should();
chai.use(require('chai-string'));
chai.use(require('chai-http'));

global.settings = require('./settings.js');
// console.log("=== SETTINGS ===");
// console.log(settings);

console.log("=== INITIALIZE CLARITY API DRIVER ===");

var ClarityApiDriver = require("../src/ClarityApiDriver.js");
global.clarity = new ClarityApiDriver( settings.environments.STG );

