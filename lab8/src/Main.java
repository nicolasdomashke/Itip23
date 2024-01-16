import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WordsCounter processer1 = new WordsCounter();
        ChainsCounter processer2 = new ChainsCounter();
        DataManager data1 = new DataManager(processer1, "C:/Users/NicolasDomashke/IdeaProjects/lab8/src/hamlet.txt");
        DataManager data2 = new DataManager(processer2, "C:/Users/NicolasDomashke/IdeaProjects/lab8/src/hamlet.txt");
        data1.process();
        data2.process();
        data1.saveData("C:/Users/NicolasDomashke/IdeaProjects/lab8/src/res1.txt");
        data2.saveData("C:/Users/NicolasDomashke/IdeaProjects/lab8/src/res2.txt");
    }
}