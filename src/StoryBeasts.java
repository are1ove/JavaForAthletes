import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class StoryBeasts {
    int countbeasts = 0;
    private static String traces = "следы ";

    private class StoryTraces {
        void trace() {
            System.out.print("Потому что были " + traces);
        }

        void action() {
            System.out.println(traces + "немного путались ");
        }
    }

    StoryTraces storyTraces = new StoryTraces();

    private static class Paws {
        static int countpaws = 4;
        static String kit = " комплектов ";

        void was() {
            System.out.println("это были " + traces + countpaws + kit + "лап");
        }

        public void sure() {
            System.out.println("можно поставить под сомнение, что");
        }
    }

    public void becoming() throws IOException{
        Scanner scanner= new Scanner(System.in);

        FileInputStream fis = new FileInputStream(new File(scanner.nextLine()));
        InputStreamReader reader = new InputStreamReader(fis);

        ArrayList<String> inpStrings = new ArrayList<>();

        int data;
        StringBuilder tempString = new StringBuilder();
        while (true){
            data = reader.read();
            if (data == -1) break;
            char curChar = (char) data;
            switch (curChar) {
                case '\n': {
                    inpStrings.add(tempString.toString());
                    tempString = new StringBuilder();
                    break;
                }
                case '{': continue;
                case '}': continue;
                case ',': continue;
                case ' ': continue;
                default : {
                    tempString.append(curChar);
                    break;
                }
            }

        }
        reader.close();
        for (int i = 0; i<inpStrings.size(); i++) {
            if ((Boolean) inpStrings.get(i).contains()){
                continue;
            }
            System.out.println(inpStrings.get(i));
        }
        LinkedHashMap<Integer, Beasts> beasts = new LinkedHashMap<>();

        for (int i=0;i<=inpStrings.lastIndexOf(inpStrings);i++){
            if (inpStrings.get(i)=="name"){
                inpStrings.remove(i);
            }
            beasts.put(i,new ScaryBeast("ЖИВОТНОЕ"));
            inpStrings.get(i);
        }
        System.out.println(beasts);
       /* for (int i=0; i) {
            beasts.put(countbeasts, new ScaryBeast(name));//name-имя обьекта из файла
        }
        if (Math.random() > 0.1D) {
            System.out.print("Потому что " + beasts.get(3));
            System.out.println(" могли оказаться ");
            for (int i = 0; i < 3; i++) {
                System.out.println(beasts.get(0));
            }
        } else System.out.println("Потому что боялись ");
        */
    }

    public void steps() throws FearException {

        while (countbeasts < 4) {
            countbeasts++;
            storyTraces.trace();
            System.out.println(countbeasts + " зверей!");
        }
        System.out.println("Уже " + countbeasts + " зверя!!! ");
        storyTraces.action();
        Paws paws = new Paws() {
            public void sure() {
                System.out.print("Но совершенно несомненно ");
            }
        };
        if (countbeasts < 4) throw new FearException("ПЕРЕСЧИТАЙТЕ КОЛИЧЕСТВО ЗВЕРЕЙ!");
        paws.sure();
        paws.was();
    }
}
