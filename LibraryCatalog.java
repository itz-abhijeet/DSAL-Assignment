import java.util.*;

public class LibraryCatalog {
    private static final String[] ISBN_LIST = {
        "9780134685991", 
        "9780596009205", 
        "9780135166307", 
        "9780132350884", 
        "9780201633610"
    };

    private static final String[] BOOK_TITLES = {
        "Effective Java",
        "Head First Java",
        "Core Java Volume I",
        "Clean Code",
        "Design Patterns"
    };

    private static final String[] hashTable = new String[ISBN_LIST.length];

    private static int perfectHash(String isbn) {
        int lastDigits = Integer.parseInt(isbn.substring(isbn.length() - 3));
        return (lastDigits % ISBN_LIST.length);
    }

    private static void buildHashTable() {
        for (int i = 0; i < ISBN_LIST.length; i++) {
            int index = perfectHash(ISBN_LIST[i]);
            while (hashTable[index] != null) {
                index = (index + 1) % ISBN_LIST.length;
            }
            hashTable[index] = BOOK_TITLES[i];
        }
    }

    private static String lookupBook(String isbn) {
        int index = perfectHash(isbn);
        int start = index;
        while (hashTable[index] != null) {
            if (ISBN_LIST[index].equals(isbn))
                return hashTable[index];
            index = (index + 1) % ISBN_LIST.length;
            if (index == start)
                break;
        }
        return "Book not found.";
    }

    public static void main(String[] args) {
        buildHashTable();
        System.out.println("=== Library Book Catalog (Perfect Hashing) ===\n");
        for (int i = 0; i < ISBN_LIST.length; i++) {
            System.out.println("ISBN: " + ISBN_LIST[i] + " -> Book: " + lookupBook(ISBN_LIST[i]));
        }
        System.out.println("\nLookup Test:");
        String searchISBN = "9780132350884";
        System.out.println("Searching for ISBN " + searchISBN + ": " + lookupBook(searchISBN));
    }
}
