import java.util.*;

public class FixtureBuilder {
    private final List<String> teams;
    private final List<String> tempTeams;
    private final Set<FootballMatch> matches;
    private final Set<String> playedMatches;
    private final List<FootballMatch> rematch;
    private final Map<Integer, Set<FootballMatch>> soccerMap;

    public FixtureBuilder(List<String> teams, boolean fullSeason) {
        this.teams = new ArrayList<>(teams);
        this.tempTeams = new ArrayList<>(teams);

        // If the number of teams is odd, add a "Bye" team
        if (this.teams.size() % 2 != 0) {
            this.teams.add("Bye");
            this.tempTeams.add("Bye");
        }

        this.matches = new HashSet<>();
        this.rematch = new ArrayList<>();
        this.soccerMap = new HashMap<>();
        this.playedMatches = new HashSet<>();
    }

    public Map<Integer, Set<FootballMatch>> build() {
        int week = (this.teams.size() - 1) * 2; // Total rounds including rematches
        int firstMatchIndex, secondMatchIndex;
        int k = 0;

        for (int i = 1; i <= week; i++) {
            Set<FootballMatch> currentWeekMatches = new HashSet<>();

            // Handle rematches for the second half of the season
            if (i > week / 2) {
                int l = this.teams.size() / 2;
                while (l > 0) {
                    currentWeekMatches.add(new FootballMatch(rematch.get(k).getSecondMatch(), rematch.get(k).getFirstMatch()));
                    l--;
                    k++;
                }
                this.soccerMap.put(i, currentWeekMatches);
                continue;
            }

            // Generate matches for the current week
            for (int j = 0; j < this.teams.size() / 2; j++) {
                firstMatchIndex = randomIndex(tempTeams.size());
                secondMatchIndex = randomIndex(tempTeams.size());
                FootballMatch match = new FootballMatch(tempTeams.get(firstMatchIndex), tempTeams.get(secondMatchIndex));

                // Ensure valid matches (no duplicate pairings)
                while (firstMatchIndex == secondMatchIndex || playedMatches.contains(match.toString())) {
                    firstMatchIndex = randomIndex(tempTeams.size());
                    secondMatchIndex = randomIndex(tempTeams.size());
                    match = new FootballMatch(tempTeams.get(firstMatchIndex), tempTeams.get(secondMatchIndex));
                }

                currentWeekMatches.add(match);
                playedMatches.add(match.toString());

                // Add the reverse match for rematches
                FootballMatch reverseMatch = new FootballMatch(tempTeams.get(secondMatchIndex), tempTeams.get(firstMatchIndex));
                rematch.add(reverseMatch);

                // Remove teams from the list for the next match
                tempTeams.remove(firstMatchIndex);
                tempTeams.remove(secondMatchIndex > firstMatchIndex ? secondMatchIndex - 1 : secondMatchIndex);
            }

            // Restore the temporary team list for the next round
            tempTeams.addAll(teams);

            // Store matches for the current week
            this.soccerMap.put(i, currentWeekMatches);
        }

        return this.soccerMap;
    }

    private int randomIndex(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }
}
