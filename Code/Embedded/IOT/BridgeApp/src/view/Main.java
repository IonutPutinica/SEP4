package view;

import model.Account;
import model.PlantProfile;
import presenter.Server;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * CLI for administration on the bridge application
 */
public class Main {
	public static void main(String[] args) {
		Server server = new Server(3001);
		Scanner scanner = new Scanner(System.in);

		String input;
		while(!(input = scanner.nextLine()).equals("exit")) {
			switch(input) {
				case "help":
					System.out.println("Commands: ");
					System.out.println(" help");
					System.out.println(" status");
					System.out.println(" getusers");
					System.out.println(" getplant");
					System.out.println(" exit");
					break;
				case "getplant":
					System.out.println("Enter id:");
					String ids = scanner.nextLine();
					int id;
					try {
						id = Integer.parseInt(ids);
					} catch(NumberFormatException e) {
						System.out.println("Invalid id");
						break;
					}

					PlantProfile p = server.databaseHandler.getPlantProfile(id);
					System.out.println(p.toJson());
					break;

				case "getaccount":
					System.out.println("Enter id");
					String accountIds = scanner.nextLine();

					Account acc = server.databaseHandler.getAccount(accountIds);
					System.out.println(acc.toJson());
					break;

				case "status":
					System.out.println(server.getStatus());
					break;
				default:
					System.out.println("? Try 'help'");
			}
		}

		System.out.println("Shutting down");
		exit(0);
	}
}
