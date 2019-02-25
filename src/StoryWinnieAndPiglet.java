import java.util.ArrayList;

public class StoryWinnieAndPiglet {

    WinnieThePooh winnie = new WinnieThePooh("Винни Пух");
    Piglet piglet = new Piglet("Пятачок");
    ArrayList they = new ArrayList();

    public void telling() {
        System.out.print(piglet.getName());
        System.out.print(piglet.say() + " " + winnie.getName() + "у" + " " + "из жизни" + " ");

    }

    public void stopping() {
        StatesWinnieThePooh state = StatesWinnieThePooh.STONESTILL;
        System.out.print(winnie.getName() + ' ' + winnie.stop + ' ');
        state.printStates();
    }

    public void going() {
        they.add(0, piglet);
        they.add(1, winnie);
        DegreeOfExcitement degree = DegreeOfExcitement.EXCITED;
        if (they.size() > 0) {
            System.out.print("Они" + " " + piglet.go + ' ');
            degree.printDegree();
        }
    }

    public void wanting() {
        System.out.print(piglet.getName() + ' ');
        WishPiglet wish = WishPiglet.DESIRE;
        wish.printWish();

    }

    public void suddenlystop() {
        System.out.print(". Неожиданно " + winnie.getName() + " " + winnie.stop);
        class Counter {
            int count = 0;

            void MiniCounter() {
                while (count < 3)
                    count++;
                System.out.print(" в " + count + " раз ");
            }
        }
        Counter counter = new Counter();
        counter.MiniCounter();
        String nose = "нос";
        System.out.println("и облизал " + nose);
    }

}
