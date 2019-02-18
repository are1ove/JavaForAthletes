public enum DadsDisease {
    RHEUMATISM,
    ARTHROSIS,
    BURSITIS,
    DYSPNEA;


    void printDesease() {
        switch (this) {
            case RHEUMATISM:
                System.out.println("от ревматизма");
                return;
            case ARTHROSIS:
                System.out.println("от артроза");
                return;
            case BURSITIS:
                System.out.println("от бурсита");
                return;
            case DYSPNEA:
                System.out.println("одышкой");
                return;
            default:
                System.out.println("от гриппа");
        }
    }
}
