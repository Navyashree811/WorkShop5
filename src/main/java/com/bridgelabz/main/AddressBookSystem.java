package com.bridgelabz.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookSystem {
	Scanner scanner = new Scanner(System.in);
	HashMap<String, AddressBook> masterBook = new HashMap<>();

	public static void main(String[] args) {
		AddressBookSystem masterBook = new AddressBookSystem();
		masterBook.mainMenuMasterBook();
	}

	public void mainMenuMasterBook() {
		boolean quite = false;
		int choice = 0;
		printDetails();
		while (!quite) {
			System.out.println("Enter Your Choice : ");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 0:
				printDetails();
				break;
			case 1:
				addAddressBook();
				break;
			case 2:
				addContactsInAddressBook();
				break;
			case 3:
				searchContactsByCityName();
				break;
			case 4:
				searchContactByStateName();
				break;
			case 5:
				viewCountOfContactsFromSpecificCityName();
				break;
			case 6:
				sortContact();
				break;
			case 7:
				writeToTheFile();
				break;
			case 8:
				readFromFile();
				break;
			case 9:
				quite = true;
			}
		}
	}

	public void printDetails() {
		System.out.println("Welcome to the Master Book.");
		System.out.println("Press : ");
		System.out.println("\t 0 to Print these Choice Options again : ");
		System.out.println("\t 1 to Add new Address Book to the Master Book : ");
		System.out.println("\t 2 to Add contact in the Existing Address Book : ");
		System.out.println("\t 3 to search contact via city name : ");
		System.out.println("\t 4 to search contact via state name : ");
		System.out.println("\t 5 to view how many contacts are from specific city : ");
		System.out.println("\t 6 to sort contacts : ");
		System.out.println("\t 7 to save the Address Book to the file : ");
		System.out.println("\t 8 to read data from addressBook_file.txt : ");
		System.out.println("\t 9 to Quite : ");
	}

	public void addAddressBook() {
		System.out.println("Enter the Name of the City for which you want to add Address Book : ");
		String cityName = scanner.nextLine();
		masterBook.put(cityName, new AddressBook());
		System.out.println("Address Book Added : ");
		System.out.println("Would You like to add contact in this Address Book : ");
		System.out.println("Press 1 to Add contact in this Address Book : ");
		System.out.println("Press 2 to for the main menu : ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		if (choice == 1) {
			masterBook.get(cityName).mainMenuAddressBook();
		} else {
			mainMenuMasterBook();
		}
	}

	public void addContactsInAddressBook() {
		System.out.println("Enter the name of address Book in which you want to add contact : ");
		String addressBookName = scanner.nextLine();
		if (masterBook.get(addressBookName) == null) {
			System.out.println("Address Book Not Exist. Create new Address Book.");
		} else {
			masterBook.get(addressBookName).mainMenuAddressBook();
		}
	}

	public void searchContactsByCityName() {
		System.out.println("Enter the name of the city : ");
		String cityName = scanner.nextLine();
		for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
			entry.getValue().searchContactByCity(cityName);
		}
	}

	public void searchContactByStateName() {
		System.out.println("Enter the name of State : ");
		String stateName = scanner.nextLine();
		for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
			entry.getValue().searchContactByState(stateName);
		}
	}

	public void viewCountOfContactsFromSpecificCityName() {
		long totalCount = 0;
		long count = 0;
		System.out.println("Enter the name of the City : ");
		String cityName = scanner.nextLine();
		for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
			count = entry.getValue().viewCountOfContactsFromSpecificCity(cityName);
			totalCount += count;
		}
		System.out.println("Total contacts from " + cityName + " city is " + totalCount);
	}

	public void viewCountOfContactFromSpecificStateName() {
		long totalCount = 0;
		long count = 0;
		System.out.println("Enter the name of the State : ");
		String stateName = scanner.nextLine();
		for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
			count = entry.getValue().viewCountOfContactsFromSpecificCity(stateName);
			totalCount += count;
		}
		System.out.println("Total contacts from " + stateName + " state is " + totalCount);
	}

	public void sortContact() {

		System.out.println("Press 1 If you want to sort the contact by their Name : ");
		System.out.println("Press 2 If you want to sort the contact by their City : ");
		System.out.println("Press 3 If you want to sort the contact by their State : ");
		System.out.println("Press 4 If you want to sort the contact by Zip Code : ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
				entry.getValue().sortContactByPersonName();
			}
			break;
		case 2:
			for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
				entry.getValue().sortContactByPersonCity();
			}
			break;
		case 3:
			for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
				entry.getValue().sortContactByPersonState();
			}
			break;
		case 4:
			for (Map.Entry<String, AddressBook> entry : masterBook.entrySet()) {
				entry.getValue().sortContactByPersonZipCode();
			}
		default:
			mainMenuMasterBook();
		}

	}

	public void writeToTheFile() {
		System.out.println("Enter the Address Book name which you want to save in the file : ");
		String name = scanner.nextLine();
		masterBook.get(name).writeToFile();
	}

	public void readFromFile() {
		String addressBookFileName = "addressBook_file.txt";
		try {
			Files.lines(new File(addressBookFileName).toPath()).map(line -> line.trim()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
