package src;

public enum WinnieLove {
    LOVE,
    NOTLOVE;

    public void printLove() {
        switch (this) {
            case LOVE:
                System.out.print("любит ");
                return;
            case NOTLOVE:
                System.out.print("не любит ");
                return;
            default:
                System.out.println("не определился");
        }
    }
}
