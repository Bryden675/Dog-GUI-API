import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class dogTest {

  /**
   * tests constructor.
   */
  @Test

  public void TestToCallConstructorForFullCoverage() {
    dog doggy = new dog("stuff", "dogs", "tall", "midget", 56, 69, "pixels", "website", 30,
        "forever", "bubba", "chickfila", "dne", "wild", "fat", "skinny");
    assertEquals("stuff", doggy.bred_for);
    assertEquals("dogs", doggy.breed_group);
    assertEquals("tall", doggy.height_imperial);
    assertEquals("midget", doggy.height_metric);
    assertEquals(56, doggy.id);
    assertEquals(69, doggy.imageHeight);
    assertEquals("pixels", doggy.imageID);
    assertEquals("website", doggy.urlString);
    assertEquals(30, doggy.imageWidth);
    assertEquals("forever", doggy.lifeSpan);
    assertEquals("bubba", doggy.name);
    assertEquals("chickfila", doggy.origin);
    assertEquals("dne", doggy.referenceID);
    assertEquals("wild", doggy.temperament);
    assertEquals("fat", doggy.weight_imperial);
    assertEquals("skinny", doggy.weight_metric);



  }

  /**
   * tests getter methods.
   */
  @Test

  public void TestsgetterMethodsForDog() {
    dog doggy = new dog("stuff", "dogs", "tall", "midget", 56, 69, "pixels", "website", 30,
        "forever", "bubba", "chickfila", "dne", "wild", "fat", "skinny");
    assertEquals("stuff", doggy.getBred_for());
    assertEquals("dogs", doggy.getBreed_group());
    assertEquals("tall", doggy.getimpHeight());
    assertEquals("midget", doggy.getMetHeight());
    assertEquals(56, doggy.getID());
    assertEquals(69, doggy.getImageHeight());
    assertEquals("pixels", doggy.getImageId());
    assertEquals("website", doggy.getUrlString());
    assertEquals(30, doggy.getImageWidth());
    assertEquals("forever", doggy.getLifeSpan());
    assertEquals("bubba", doggy.getName());
    assertEquals("dne", doggy.getRefernceID());
    assertEquals("wild", doggy.getTemperament());
    assertEquals("fat", doggy.getImpWeight());
    assertEquals("skinny", doggy.getMetWeight());
    assertEquals("chickfila", doggy.getOrigin());

  }
}
