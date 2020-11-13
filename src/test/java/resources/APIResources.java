package resources;

public enum APIResources {
//Enum is a special class in java which has collection of constants or methods. Below is methods seperated by comma(,)
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	//constructor should be similar as method
	APIResources(String resource){
		this.resource = resource;
	}
	public String getResource() {
		return resource;
	}
}
