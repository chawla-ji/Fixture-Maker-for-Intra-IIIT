public class FootballMatch {
    private final String firstMatch;  // First match (formerly home team)
    private final String secondMatch; // Second match (formerly away team)

    public FootballMatch(String firstMatch, String secondMatch) {
        this.firstMatch = firstMatch;
        this.secondMatch = secondMatch;
    }

    public String getFirstMatch() {
        return firstMatch;
    }

    public String getSecondMatch() {
        return secondMatch;
    }

    @Override
    public String toString() {
        return firstMatch + " vs " + secondMatch;
    }
}
