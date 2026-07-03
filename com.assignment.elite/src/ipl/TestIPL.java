package ipl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TestIPL {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IplJDBCImplementation dao = new IplJDBCImplementation();

        int choice;

        do {
            System.out.println("\n===== IPL MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Player");
            System.out.println("2. Find Player");
            System.out.println("3. Update Team");
            System.out.println("4. Find By Team");
            System.out.println("5. Display All Players");
            System.out.println("6. Find By Winning Amount");
            System.out.println("7. Find By Role");
            System.out.println("8. Delete Player");
            System.out.println("9. Exit");
            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

            case 1:

                System.out.print("Enter Jersey No : ");
                int jersey = sc.nextInt();

                sc.nextLine();

                System.out.print("Enter Player Name : ");
                String pname = sc.nextLine();

                System.out.print("Enter Role : ");
                String role = sc.nextLine();

                System.out.print("Enter Team Name : ");
                String team = sc.nextLine();

                System.out.print("Enter Winning Amount : ");
                double amount = sc.nextDouble();

                Ipl player = new Ipl(jersey, role, pname, amount);
                player.setTeamname(team);

                if (dao.addPlayer(player))
                    System.out.println("Player Added Successfully.");
                else
                    System.out.println("Failed.");

                break;

            case 2:

                System.out.print("Enter Jersey No : ");
                jersey = sc.nextInt();

                Ipl p = dao.findByPlayer(jersey);

                if (p != null)
                    System.out.println(p);
                else
                    System.out.println("Player Not Found");

                break;

            case 3:

                System.out.print("Enter Jersey No : ");
                jersey = sc.nextInt();

                sc.nextLine();

                System.out.print("Enter New Team : ");
                team = sc.nextLine();

                try {
                    if (dao.addToTeam(team, jersey))
                        System.out.println("Team Updated.");
                } catch (PlayerNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case 4:

                sc.nextLine();

                System.out.print("Enter Team Name : ");
                team = sc.nextLine();

                List<Ipl> teamPlayers = dao.findByTeam(team);

                if (teamPlayers.isEmpty())
                    System.out.println("No Players Found");
                else
                    teamPlayers.forEach(System.out::println);

                break;

            case 5:

                List<Ipl> all = dao.findAllTeam();

                all.forEach(System.out::println);

                break;

            case 6:

                System.out.print("Enter Winning Amount : ");
                amount = sc.nextDouble();

                Optional<Ipl> op = dao.findByWinning(amount);

                if (op.isPresent())
                    System.out.println(op.get());
                else
                    System.out.println("No Player Found");

                break;

            case 7:

                sc.nextLine();

                System.out.print("Enter Team : ");
                team = sc.nextLine();

                System.out.print("Enter Role : ");
                role = sc.nextLine();

                List<Ipl> roles = dao.sortByRole(team, role);

                if (roles.isEmpty())
                    System.out.println("No Players Found");
                else
                    roles.forEach(System.out::println);

                break;

            case 8:

                System.out.print("Enter Jersey No : ");
                jersey = sc.nextInt();

                if (dao.deletePlayer(jersey))
                    System.out.println("Deleted Successfully.");
                else
                    System.out.println("Player Not Found.");

                break;

            case 9:

                System.out.println("Thank You");
                break;

            default:
                System.out.println("Invalid Choice");
            }

        } while (choice != 9);

        sc.close();
    }
}