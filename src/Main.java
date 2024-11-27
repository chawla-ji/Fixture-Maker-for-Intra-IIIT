import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Welcome to the Khel 2023 Football Fixture Generator!");
            System.out.print("Enter the number of teams participating in the tournament: ");
            int teamCount = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (teamCount <= 0) {
                throw new IllegalArgumentException("Number of teams must be greater than zero.");
            }

            List<String> teams = new ArrayList<>();
            System.out.println("Enter the names of the teams:");
            for (int i = 0; i < teamCount; i++) {
                System.out.print("Team " + (i + 1) + ": ");
                String teamName = scanner.nextLine().trim();
                if (teamName.isEmpty()) {
                    throw new IllegalArgumentException("Team name cannot be empty.");
                }
                teams.add(teamName);
            }

            System.out.print("Do you want a full season (home and away matches)? (yes/no): ");
            String seasonChoice = scanner.nextLine().trim();
            boolean fullSeason = seasonChoice.equalsIgnoreCase("yes");

            // Pass the list of teams and fullSeason to the FixtureBuilder
            FixtureBuilder fixtureBuilder = new FixtureBuilder(teams, fullSeason);
            Map<Integer, Set<FootballMatch>> soccerMap = fixtureBuilder.build();  // Updated to use FootballMatch

            System.out.println("\nGenerated Fixtures for Khel 2023:");
            for (Map.Entry<Integer, Set<FootballMatch>> entry : soccerMap.entrySet()) {
                System.out.println("Round " + entry.getKey());
                for (FootballMatch match : entry.getValue()) {
                    System.out.println(match);  // Updated to use FootballMatch
                }
                System.out.println("-------------------------------");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
