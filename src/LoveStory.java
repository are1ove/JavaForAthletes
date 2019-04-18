package src;

public class LoveStory {
    public WinnieThePooh winnieThePooh = new WinnieThePooh("Винни Пух");
    public ChristopherRobin christopherRobin = new ChristopherRobin("Кристофер Робин");


    public void loving() {
        System.out.print("потому что " + winnieThePooh.getName() + " ");
        WinnieLove love = WinnieLove.LOVE;
        love.printLove();
        System.out.print(christopherRobin.getName());
    }
}

