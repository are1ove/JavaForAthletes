public class LoveStory {
    WinnieThePooh winnieThePooh = new WinnieThePooh("Винни Пух");
    ChristopherRobin christopherRobin = new ChristopherRobin("Кристофер Робин");


    public void loving() {
        System.out.print("потому что " + winnieThePooh.getName() + " ");
        WinnieLove love = WinnieLove.LOVE;
        love.printLove();
        System.out.print(christopherRobin.getName());
    }
}

