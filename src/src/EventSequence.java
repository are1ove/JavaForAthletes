package src;


public enum EventSequence {
    GOOD,
    BAD;

    void printSequence() {
        switch (this) {
            case GOOD:
                System.out.print(" ужасно хотел ");
                return;
            case BAD:
                System.out.print(" не хотел ");
                return;
            default:
                System.out.print(" не знал он ");
        }
    }
}
