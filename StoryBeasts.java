import java.util.LinkedHashMap;

public class StoryBeasts {
    int countbeasts = 1;
    private static String traces = "следы ";
    public ScaryBeast zerobeast = new ScaryBeast("Первый страшный зверь");
    public ScaryBeast firstbeast = new ScaryBeast("Второй страшный зверь");
    public ScaryBeast secondbeast = new ScaryBeast("Третий страшный зверь");
    public UnknownBeast thirdbeast = new UnknownBeast("Первый неизвестный зверь");
    public UnknownBeast fourthbeast = new UnknownBeast("Второй неизвестный зверь");
    public UnknownBeast fifthbeast = new UnknownBeast("Третий неизвестный зверь");


    public creation(int a){
        for (int i = 0; i<a; i++){
            Beasts beasts = new Be\
        }
    }
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

    public void becoming() {
        LinkedHashMap<Integer, Beasts> beasts = new LinkedHashMap<>();
        beasts.put(0, zerobeast);
        beasts.put(1, firstbeast);
        beasts.put(2, secondbeast);
        beasts.put(3, thirdbeast);
        beasts.put(4, fourthbeast);
        beasts.put(5, fifthbeast);
        if (Math.random() > 0.1D) {
            System.out.print("Потому что " + beasts.get(3));
            System.out.println(" могли оказаться ");
            for (int i = 0; i < 3; i++) {
                System.out.println(beasts.get(0));
            }
        } else System.out.println("Потому что боялись ");
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
