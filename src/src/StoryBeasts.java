package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;


public class StoryBeasts {
    int countbeasts;
    public final Date CREATE_DATE;
    private static String traces = "следы ";

    public StoryBeasts() {
        CREATE_DATE = new Date();
    }

    private class StoryTraces {
        void trace() {
            System.out.print("Потому что были " + traces);
        }

        String action() {
            return traces + "немного путались ";
        }
    }

    StoryTraces storyTraces = new StoryTraces();


    ConcurrentHashMap<String, Beasts> beasts = new ConcurrentHashMap<>();
    ArrayList<String> keys = new ArrayList<>();
    ArrayList<String> inpStrings = new ArrayList<>();

    public void beginning() {
        for (int i = 0; i < inpStrings.size(); i++) {
            if (inpStrings.get(i).contains("name")) {
                String str = inpStrings.get(i).substring(inpStrings.get(i).indexOf(":") + 2, inpStrings.get(i).length() - 1);
                inpStrings.set(i, str);
                if (str.contains("Страшный зверь")) {
                    beasts.put("Зверь" + i, new ScaryBeast(str));
                    countbeasts += 1;
                    keys.add("Зверь" + i);
                } else {
                    beasts.put("Зверь" + i, new UnknownBeast(str));
                    countbeasts += 1;
                    keys.add("Зверь" + i);
                }
            }

        }
    }
    public void becoming(){
        if (Math.random() > 0.1D) {
            System.out.print("Потому что ");
            for (int i = 0; i < beasts.size() / 2; i++) {
                System.out.println(beasts.get("Зверь" + (i)));
            }
            System.out.println("Могли оказаться ");
            for (int i = beasts.size() / 2; i < beasts.size(); i++) {
                System.out.println(beasts.get("Зверь" + (i)));
            }
        } else System.out.println("Потому что боялись ");
    }
  /*  public void createMap(String str){
            JSONParser parser = new JSONParser();
        try{
            JSONArray a = (JSONArray) parser.parse(str);
            for (Object o : a) {
                JSONObject thing = (JSONObject) o;
                String name = (String) thing.get("name");
                if (name.startsWith("С"){
                    beasts.put("Зверь" + i, new ScaryBeast(name));
                }else {
                    beasts.put("Зверь" + i, new UnknownBeast(name));
                }
            }
        }catch (IOException){
        }
        }
*/


    private static class Paws {

        StoryBeasts storyBeasts = new StoryBeasts();
        public int countpaws = storyBeasts.keys.size();
        static String kit = " комплектов ";

        String was() {
            return " это были " + traces + "их" + kit + "лап";
        }

        public String sure() {
            return "можно поставить под сомнение, что";
        }
    }

    /**
     *
     * @return
     */
    public String steps() {
        Paws paws = new Paws() {
            @Override
            public String sure() {
                return "Но совершенно несомненно ";
            }
        };
        return "Уже " + beasts.size() + " зверя!!! " + "\n" + storyTraces.action() + "\n" + paws.sure() + paws.was();
    }
}