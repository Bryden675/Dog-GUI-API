
public class dog {
  String bred_for;
  String breed_group;
  String height_metric;
  String height_imperial;
  int id;
  int imageHeight;
  String imageID;
  String urlString;
  int imageWidth;
  String lifeSpan;
  String name;
  String origin;
  String referenceID;
  String temperament;
  String weight_imperial;
  String weight_metric;



  public dog(String bred_for, String breed_group, String height_imperial, String height_metric,
      int id, int imageHeight, String imageID, String urlString, int imageWidth, String lifeSpan,
      String name, String origin, String referenceID, String temperament, String weight_imperial,
      String weight_metric) {
	  
    this.bred_for = bred_for;
    this.breed_group = breed_group;
    this.height_imperial = height_imperial;
    this.height_metric = height_metric;
    this.id = id;
    this.imageHeight = imageHeight;

    this.imageID = imageID;
    this.urlString = urlString;
    this.imageWidth = imageWidth;
    this.lifeSpan = lifeSpan;
    this.name = name;
    this.origin = origin;
    this.referenceID = referenceID;
    this.temperament = temperament;

    this.weight_imperial = weight_imperial;
    this.weight_metric = weight_metric;

  }

  public String getBred_for() {
    return this.bred_for;
  }

  public String getBreed_group() {
    return this.breed_group;
  }

  public String getimpHeight() {
    return this.height_imperial;
  }

  public String getMetHeight() {
    return this.height_metric;
  }

  public int getID() {
    return this.id;
  }

  public int getImageHeight() {
    return this.imageHeight;
  }

  public String getUrlString() {
    return this.urlString;
  }

  public String getImageId() {
    return this.imageID;
  }

  public int getImageWidth() {
    return this.imageWidth;
  }

  public String getLifeSpan() {
    return this.lifeSpan;
  }

  public String getName() {
    return this.name;
  }

  public String getOrigin() {
    return this.origin;
  }

  public String getRefernceID() {
    return this.referenceID;
  }

  public String getTemperament() {
    return this.temperament;
  }

  public String getImpWeight() {
    return this.weight_imperial;
  }

  public String getMetWeight() {
    return this.weight_metric;
  }



}