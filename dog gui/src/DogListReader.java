import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class DogListReader {
	
	public static List<dog> readDogList(InputStream input) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		 
		JsonNode tree = mapper.readTree(input);
		
		List<dog> dogs = new ArrayList<dog>();
		
		
		for (int i = 0; i < tree.size(); i++) {
			
			JsonNode breedNode = tree.get(i);
			
			String bred_for = "";

			try {
				
				bred_for = breedNode.get("bred_for").asText();

				 
			} catch (NullPointerException e) {
				
				bred_for = "";

				
			}
			
			String breed_group = "";

			try {
				
				breed_group = breedNode.get("breed_group").asText();

				 
			} catch (NullPointerException e) {
				
				breed_group = "";

				
			}
			
			String height_metric = breedNode.get("height").get("metric").asText();
			
			String height_imperial = breedNode.get("height").get("imperial").asText();
			
			int id = breedNode.get("id").asInt();
			
			int imageHeight = breedNode.get("image").get("height").asInt();
			
			String imageID = breedNode.get("image").get("id").asText();
						
			int imageWidth = breedNode.get("image").get("width").asInt();
			
			String lifeSpan = breedNode.get("life_span").asText();
			
			String name = breedNode.get("name").asText();
			
			String temperament = "";

			try {
				
				temperament = breedNode.get("temperament").asText();

				 
			} catch (NullPointerException e) {
				
				temperament = "";

				
			}			
			String referenceID = breedNode.get("reference_image_id").asText();
			
			String origin = "";

			try {
				
				origin = breedNode.get("origin").asText();

				 
			} catch (NullPointerException e) {
				
				origin = "";

				
			}			
			String weight_metric = breedNode.get("weight").get("metric").asText();
			
			String weight_imperial = breedNode.get("weight").get("imperial").asText();
			
			String urlString = breedNode.get("image").get("url").asText();
						
			
			dogs.add(new dog(bred_for, breed_group, height_metric, height_imperial,
				      id, imageHeight, imageID, urlString, imageWidth, lifeSpan,
				      name, origin, referenceID, temperament, weight_imperial,
				      weight_metric));
			
		}
		
		return dogs;
	}
	
}
