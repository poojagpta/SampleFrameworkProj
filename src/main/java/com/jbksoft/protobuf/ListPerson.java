package com.jbksoft.protobuf;

import com.jbksoft.protobuf.protobufferData.PersonAccountsBookProtos.PersonAccountsBook;
import com.jbksoft.protobuf.protobufferData.PersonAccountsBookProtos.Person;

import java.io.FileInputStream;

/**
 * Created by pooja on 10/8/15.
 */
public class ListPerson {
    // Iterates though all people in the AddressBook and prints info about them.
    static void Print(PersonAccountsBook accountBook) {
        for (Person person: accountBook.getPersonList()) {
            System.out.println("Person ID: " + person.getId());
            System.out.println("  Name: " + person.getName());
            if (person.hasEmail()) {
                System.out.println("  E-mail address: " + person.getEmail());
            }

            for (Person.AccountNumber accountNumber : person.getAccountsList()) {
                switch (accountNumber.getType()) {
                    case VISA:
                        System.out.print("  Visa Account #: ");
                        break;
                    case SAVING:
                        System.out.print("  Saving Account  #: ");
                        break;
                    case CHECKING:
                        System.out.print("  Checking Account  #: ");
                        break;
                }
                System.out.println(accountNumber.getNumber());
            }
        }
    }

    // Main function:  Reads the entire address book from a file and prints all
    //   the information inside.
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage:  ListPeople Account_BOOK_FILE");
            System.exit(-1);
        }

        // Read the existing address book.
        PersonAccountsBook accountBook =
                PersonAccountsBook.parseFrom(new FileInputStream(args[0]));

        Print(accountBook);
    }
}
