package src;

public enum StatesWinnieThePooh {
    STONESTILL,
    NOWAY;


    void printStates() {
        switch (this) {
            case STONESTILL:
                System.out.println("как вкопанный");
                return;
            case NOWAY:
                System.out.println("никак");
                return;
            default:
                System.out.println("как Винни Пух");
        }
    }


}
