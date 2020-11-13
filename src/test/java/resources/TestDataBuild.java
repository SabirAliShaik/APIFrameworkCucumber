package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlace;

public class TestDataBuild {

	public addPlace addPlacePayLoad(String name, String language, String address) {
		addPlace ap = new addPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		List<String> myList = new ArrayList<String>(); //setList expects List of Strings, so creating that.
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		Location location = new Location(); //setLocation expects object of Location Java class. so creating that and passing
		location.setLat(-38.383494);
		location.setLng(33.427362);
		ap.setLocation(location);
		return ap;
	}
	public String deletePlacePayload(String placeId) {
		return "{\r\n \"place_id\": \""+placeId+"\"\r\n}";
	}
}
