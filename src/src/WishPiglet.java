package src;

public enum WishPiglet {
    DESIRE,
    RELUCTANCE;

    void printWish() {
        switch (this) {
            case DESIRE:
                System.out.print("ужасно хотел ");
                return;
            case RELUCTANCE:
                System.out.print("не хотел ");
                return;
            default:
                System.out.print("не очень то и хотел ");
        }
    }
}
