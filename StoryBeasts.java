public class StoryBeasts {
    int countbeasts = 1;
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

    public void becoming() {
        ScaryBeast[] sbeast = new ScaryBeast[3];
        sbeast[0] = new ScaryBeast("Страшный зверь");
        sbeast[1] = new ScaryBeast("Страшный зверь");
        sbeast[2] = new ScaryBeast("Страшный зверь");
        UnknownBeast[] ubeast = new UnknownBeast[3];
        ubeast[0] = new UnknownBeast("Неизвестный зверь");
        ubeast[1] = new UnknownBeast("Неизвестный зверь");
        ubeast[2] = new UnknownBeast("Неизвестный зверь");
        if (Math.random() > 0.1D) {
            System.out.print("Потому что " + ubeast[0]);
            System.out.println(" могли оказаться ");
            for (int i = 0; i < 3; i++) {
                System.out.println(sbeast[i]);
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
