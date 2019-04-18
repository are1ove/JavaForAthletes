
public enum DegreeOfExcitement {
    UNSETTLED,
    EXCITED,
    VERYEXCITED;


    void printDegree() {
        switch (this) {
            case UNSETTLED:
                System.out.println("не волнуясь");
                return;
            case EXCITED:
                System.out.println("начиная немного волноваться");
                return;
            case VERYEXCITED:
                System.out.println("начиная сильно волноваться");
            default:
                System.out.println("начиная ничего не делать");
        }
    }
}

