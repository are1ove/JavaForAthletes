public class StoryGrandFather {

    class Story1  {
        DadsDisease desease = DadsDisease.RHEUMATISM;
        private String treat = " лечился ";
    }

    class Story2 {
        DadsDisease desease = DadsDisease.DYSPNEA;
        private String begintosuffer = " начал страдать ";
    }

    GrandFatherUnauthorisedV dad = new GrandFatherUnauthorisedV("Дедушка Посторонним В.");
    Story1 story1 = new Story1();
    Story2 story2 = new Story2();

    public void settingposition() {
        dad.setPosition("тут");
        if (dad.position == dad.here) {
            System.out.println("чтобы" + " " + dad.getName() + " " + dad.was() + " " + dad.getPosition() + " ");
        }
    }

    public void interestingstory() throws DeseaseException{
        System.out.println(dad.getName());
        System.out.print("Например, как " + dad.getName() + story1.treat);
        story1.desease.printDesease();
        System.out.print("и как " + dad.getName() + story2.begintosuffer);
        story2.desease.printDesease();
        if (story1.desease != DadsDisease.RHEUMATISM) throw new DeseaseException("Истории Дедушки неправдивые");

    }
}
