import java.util.*;

public class FixtureBuilder {
    private final List<String> teams;
    private final boolean fullSeason;
    private final Set<String> playedMatches;
    private final List<FootballMatch> rematch;
    private final Map<Integer, Set<FootballMatch>> footballMap;

    public FixtureBuilder(List<String> teams, boolean fullSeason) {
        this.teams = new ArrayList<>(teams);
        this.fullSeason = fullSeason;

        if (this.teams.size() % 2 != 0) {
            this.teams.add("Bye"); // Handle odd number of teams
        }

        this.playedMatches = new HashSet<>();
        this.rematch = new ArrayList<>();
        this.footballMap = new HashMap<>();
    }

    public Map<Integer, Set<FootballMatch>> build() {
        int totalRounds = (fullSeason ? 2 : 1) * (teams.size() - 1);
        List<String> tempTeams = new ArrayList<>(teams);
        int matchCounter = 0;

        for (int round = 1; round <= totalRounds; round++) {
            Set<FootballMatch> currentRound = new HashSet<>();

            // Handle rematches for the second half of the season
            if (round > totalRounds / 2 && fullSeason) {
                for (int i = 0; i < rematch.size(); i++) {
                    FootballMatch originalMatch = rematch.get(matchCounter++);
                    currentRound.add(new FootballMatch(originalMatch.getAwayTeam(), originalMatch.getHomeTeam()));
                }
                footballMap.put(round, currentRound);
                continue;
            }

            // Scheduling the matches for the round
            while (tempTeams.size() > 1) {
                String home = tempTeams.remove(0);
                String away = tempTeams.remove(0);

                FootballMatch match = new FootballMatch(home, away);
                while (playedMatches.contains(match.toString())) {
                    tempTeams.add(home);
                    tempTeams.add(away);
                    Collections.shuffle(tempTeams);
                    home = tempTeams.remove(0);
                    away = tempTeams.remove(0);
                    match = new FootballMatch(home, away);
                }

                currentRound.add(match);
                playedMatches.add(match.toString());
                rematch.add(match);
            }

            tempTeams.addAll(teams);
            footballMap.put(round, currentRound);
        }

        return footballMap;
    }
}
