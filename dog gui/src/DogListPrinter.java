import java.util.Comparator;
import java.util.List;

public class DogListPrinter {
	
	public static void print(List<dog> dogList) {
		
		dogList.sort(new Comparator<dog>() {

			public int compare(dog o1, dog o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		for (dog temp : dogList) {
			System.out.print(temp.getName());
		}
		
	}
	
}
