package com.bridgelabz.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Contact> person = new ArrayList<>();

	public void mainMenuAddressBook() {
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
				addContacts();
				break;
			case 2:
				editContacts();
				break;
			case 3:
				searchContacts();
				break;
			case 4:
				showAddressBook();
				break;
			case 5:
				deleteContacts();
				break;
			case 6:
				quite = true;
				break;
			}
		}

	}

	public void printDetails() {
		System.out.println("Welcome to Address Book.");
		System.out.println("\t Press 0 to Print Choice Options : ");
		System.out.println("\t Press 1 to Add the contact details : ");
		System.out.println("\t Press 2 to Edit the contact details : ");
		System.out.println("\t Press 3 see the Details of Specific person : ");
		System.out.println("\t Press 4 to see Full Address Book : ");
		System.out.println("\t Press 5 to Delete Person Details from Address Book : ");
		System.out.println("\t Press 6 to Exit Address Book App : ");
	}

	public void addContacts() {

		System.out.println("Enter First Name : ");
		String fName = scanner.nextLine();
		if (person.stream().anyMatch(n -> n.getFirstName().equals(fName))) {
			System.out.println("Person Details already present, You can't add details of same person.");
			mainMenuAddressBook();
		}
		System.out.println("Enter Last Name : ");
		String lName = scanner.nextLine();
		System.out.println("Enter your City : ");
		String city = scanner.nextLine();
		System.out.println("Enter your State : ");
		String state = scanner.nextLine();
		System.out.println("Enter your Email-ID : ");
		String email = scanner.nextLine();
		System.out.println("Enter your Zip code : ");
		String zip = scanner.nextLine();
		System.out.println("Enter your Mobile Number : ");
		String mNumber = scanner.nextLine();
		System.out.println();

		person.add(new Contact(fName, lName, city, state, email, zip, mNumber));
	}

	private void editContacts() {
		System.out.println("Enter the Name of the person whose details you want to Edit : ");
		String name = scanner.nextLine();
		for (int i = 0; i < person.size(); i++) {
			if (person.get(i).getFirstName().equalsIgnoreCase(name)) {

				System.out.println("Choose What you want to Update : ");
				System.out.println("\t Press 1 to update First Name : ");
				System.out.println("\t Press 2 to update Last Name : ");
				System.out.println("\t Press 3 to update City : ");
				System.out.println("\t Press 4 to update State : ");
				System.out.println("\t Press 5 to update Email-ID : ");
				System.out.println("\t Press 6 to update Zip code : ");
				System.out.println("\t Press 7 to update Mobile Number : ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					System.out.println("Enter Updated First Name : ");
					person.get(i).setFirstName(scanner.nextLine());
					break;
				case 2:
					System.out.println("Enter Updated Last Name : ");
					person.get(i).setLastName(scanner.nextLine());
					break;
				case 3:
					System.out.println("Enter Updated City : ");
					person.get(i).setCity(scanner.nextLine());
					break;
				case 4:
					System.out.println("Enter Updated State : ");
					person.get(i).setState(scanner.nextLine());
					break;
				case 5:
					System.out.println("Enter Updated Email ID : ");
					person.get(i).setEmail(scanner.nextLine());
					break;
				case 6:
					System.out.println("Enter Updated Zip Code : ");
					person.get(i).setZip(scanner.nextLine());
					scanner.nextLine();
					break;
				case 7:
					System.out.println("Enter Updated Mobile Number : ");
					person.get(i).setPhoneNumber(scanner.nextLine());
					scanner.nextLine();
					break;
				}
			} else {
				System.out.println("Contact Details are not present. :( ");
			}
		}
	}

	public void searchContacts() {
		System.out.println("Enter your name to search for your details : ");
		String name = scanner.nextLine();
		for (int i = 0; i < person.size(); i++) {
			if (person.get(i).getFirstName().equalsIgnoreCase(name)) {
				System.out.println(person.get(i));
				break;
			} else
				System.out.println("Person Details are not present.");
		}
	}

	public void showAddressBook() {
		for (Contact c : person) {
			System.out.println(c);
		}
	}

	public void deleteContacts() {
		System.out.println("Enter Person Name to delete all the data related to that Person : ");
		String name = scanner.nextLine();
		for (int i = 0; i < person.size(); i++) {
			if (person.get(i).getFirstName().equalsIgnoreCase(name)) {
				person.remove(i);
				break;
			} else {
				System.out.println("Entered Name is not matching wih any Persons details.");
			}
		}
	}

	public void searchContactByState(String stateName) {
		person.stream().filter(p -> p.getState().equalsIgnoreCase(stateName)).forEach(System.out::println);
	}

	public void searchContactByCity(String cityName) {
		person.stream().filter(p -> p.getCity().equalsIgnoreCase(cityName)).forEach(System.out::println);
	}

	public long viewCountOfContactsFromSpecificCity(String cityName) {
		return person.stream().filter(p -> p.getCity().equalsIgnoreCase(cityName)).count();
	}

	public long viewCountOfContactsFromSpecificState(String stateName) {
		return person.stream().filter(p -> p.getState().equalsIgnoreCase(stateName)).count();
	}

	public void sortContactByPersonName() {
		person.stream().sorted((s1, s2) -> s1.getFirstName().compareTo(s2.getFirstName())).forEach(System.out::println);
	}

	public void sortContactByPersonCity() {
		person.stream().sorted((s1, s2) -> s1.getCity().compareTo(s2.getCity())).forEach(System.out::println);
	}

	public void sortContactByPersonState() {
		person.stream().sorted((s1, s2) -> s1.getState().compareTo(s2.getState())).forEach(System.out::println);
	}

	public void sortContactByPersonZipCode() {
		person.stream().sorted((s1, s2) -> s1.getZip().compareTo(s2.getZip())).forEach(System.out::println);
	}

	public void writeToFile() {

		String addressBookPath = "addressBook_file.txt";
		StringBuffer addressBookBuffer = new StringBuffer();
		person.forEach(contact -> {
			String addressBookString = contact.toString().concat("\n");
			addressBookBuffer.append(addressBookString);
		});
		try {
			Files.write(Paths.get(addressBookPath), addressBookBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
