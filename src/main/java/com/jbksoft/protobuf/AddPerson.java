package com.jbksoft.protobuf;

import com.jbksoft.protobuf.protobufferData.PersonAccountsBookProtos.PersonAccountsBook;
import com.jbksoft.protobuf.protobufferData.PersonAccountsBookProtos.Person;

import java.io.*;

/**
 * Created by pooja on 10/8/15.
 */
public class AddPerson {

    // This function fills in a Person message based on user input.
    static Person PromptForAddress(BufferedReader stdin,
                                   PrintStream stdout) throws IOException {
        Person.Builder person = Person.newBuilder();

        stdout.print("Enter person ID: ");
        person.setId(Integer.valueOf(stdin.readLine()));

        stdout.print("Enter name: ");
        person.setName(stdin.readLine());

        stdout.print("Enter email address (blank for none): ");
        String email = stdin.readLine();
        if (email.length() > 0) {
            person.setEmail(email);
        }

        while (true) {
            stdout.print("Enter a Account Number (or leave blank to finish): ");
            String number = stdin.readLine();
            if (number.length() == 0) {
                break;
            }

            Person.AccountNumber.Builder accountNumber =
                    Person.AccountNumber.newBuilder().setNumber(number);

            stdout.print("Is this a saving,checking,visa account? ");
            String type = stdin.readLine();
            if (type.equals("saving")) {
                accountNumber.setType(Person.AccountType.SAVING);
            } else if (type.equals("visa")) {
                accountNumber.setType(Person.AccountType.VISA);
            } else if (type.equals("checking")) {
                accountNumber.setType(Person.AccountType.CHECKING);
            } else {
                stdout.println("Unknown account type.  Using default.");
            }

            person.addAccounts(accountNumber);
        }

        return person.build();
    }

    // Main function:  Reads the entire address book from a file,
    //   adds one person based on user input, then writes it back out to the same
    //   file.
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage:  AddPerson ACCOUNT_BOOK_FILE");
            System.exit(-1);
        }

        PersonAccountsBook.Builder accountBook = PersonAccountsBook.newBuilder();

        // Read the existing address book.
        try {
            accountBook.mergeFrom(new FileInputStream(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println(args[0] + ": File not found.  Creating a new file.");
        }

        // Add an address.
        accountBook.addPerson(
                PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                        System.out));

        // Write the new address book back to disk.
        FileOutputStream output = new FileOutputStream(args[0]);
        accountBook.build().writeTo(output);
        output.close();
    }
}

