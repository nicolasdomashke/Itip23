import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.hash;

public class Main {

    public static void main(String[] args) {
        Book book1 = new Book("Crime and punishment", "Fedor Dostoevsky", 100000);
        Book book2 = new Book("Fahrenheit 451", "ray bradbury", 50000);
        Book book2_2 = new Book("Fahrenheit 451 different edition", "Ray Bradbury", 100000);
        Book book3 = new Book("Sweet Mia and the Supreme mentor", "Talya Sol", 2000);
        HashtableMy ht = new HashtableMy();
        ht.put("9785041567224", book1);
        ht.put("9785990866492", book2);
        ht.put("9785990866492", book2_2);
        ht.put("9785449675125", book3);
        System.out.println(ht.get("9785449675125").get(0).getTitle());
        ht.remove("9785041567224");
        System.out.println(ht.get("9785041567224").get(0).getTitle());
        System.out.println(ht.get("9785990866492").get(0).getTitle());
        System.out.println(ht.get("9785990866492").get(1).getTitle());
        System.out.println(ht.size());
        System.out.println(ht.isEmpty());
    }
}