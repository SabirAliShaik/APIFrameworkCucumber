Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being Successfully added using AddPLaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in reponse body is "OK"      
	And "scope" in reponse body is "APP" 
	And verify the place_id created maps to "<name>" using "getPlaceAPI"
	
	Examples:
	|name | language |address|
	|SKSSS |English |World class center |     
#	|Renault |French |Salt Lake City|                 //comment anything in feature file using #           

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload             
	When  User calls "deletePlaceAPI" with "Post" http request             
	Then the API call got success with status code 200
	And "status" in reponse body is "OK"                     