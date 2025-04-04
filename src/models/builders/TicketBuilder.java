package src.models.builders;

import src.models.Coordinates;
import src.models.Person;
import src.models.Ticket;
import src.models.TicketType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class TicketBuilder {
    private final Scanner scanner;

    public TicketBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    public Ticket buildTicket() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        Float price = readPrice();
        String comment = readComment();
        Boolean refundable = readRefundable();
        TicketType ticketType = readTicketType();
        Person person = readPerson();
        return new Ticket(name, coordinates, price, comment, refundable, ticketType, person);
    }

    private Person readPerson() {
        LocalDateTime birthday = readBirthday();
        Integer height = readHeight();
        float weight = readWeight();
        String passportID = readPassportID();
        return new Person(birthday, height, weight, passportID);
    }

    private String readPassportID() {
        System.out.println("Enter Passport ID (nullable): ");
        String passportID = scanner.nextLine().trim();
        if (passportID.isEmpty()) {
            return null;
        }
        return passportID;
    }

    private float readWeight() {
        while (true) {
            System.out.println("Enter weight (kg) (not nullable): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Weight cannot be empty!");
                continue;
            }
            try {
                float weight = Float.parseFloat(input);
                if (weight <= 0) {
                    System.out.println("Weight must be positive!");
                    continue;
                }
                return weight;
            } catch (NumberFormatException e) {
                System.out.println("Weight must be float");
            }
        }
    }

    private Integer readHeight() {
        while (true) {
            System.out.println("Please enter the height of the person (not nullable):");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Height must not be empty");
                continue;
            }
            try {
                int height = Integer.parseInt(input);
                if (height <= 0) {
                    System.out.println("Height must be positive");
                }
                return height;
            } catch (NumberFormatException e) {
                System.out.println("Height must be an integer");
            }
        }
    }

    private LocalDateTime readBirthday() {
        while (true) {
            System.out.println("Please enter persons birthday (nullable) (yyyy-MM-dd): ");
            String birthday = scanner.nextLine().trim();
            if (birthday.isEmpty()) {
                return null;
            }

            try {
                LocalDate birthdayDate = LocalDate.parse(birthday);

                if (birthdayDate.isAfter(LocalDate.now())) {
                    System.out.println("Please enter a valid birthday");
                    continue;
                }

                return birthdayDate.atStartOfDay();

            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid birthday");
            }
        }
    }

    private TicketType readTicketType() {
        while (true) {
            System.out.println("Enter ticket type (nullable):");
            System.out.println("Types of tickets: " + Arrays.toString(TicketType.values()));
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return TicketType.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid ticket type");
            }
        }
    }

    private Boolean readRefundable() {
        System.out.println("Please enter your refundable status (type \"true for true or anything else for false\")");
        String refundableStatus = scanner.nextLine().trim();
        return Boolean.parseBoolean(refundableStatus);
    }

    private String readComment() {
        while (true) {
            System.out.println("Please enter a comment (leave empty for null) (length of comment <= 855): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            if (input.length() > 855) {
                System.out.println("Error: comment is too long");
                continue;
            }
            return input;
        }
    }

    private Float readPrice() {
        while (true) {
            System.out.println("Please enter the price of the ticket (nullable) (price > 0): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;
            try {
                Float price = Float.parseFloat(input);
                if (price > 0) {
                    return price;
                }
                System.out.println("Error: price cannot be negative");
            } catch (NumberFormatException e) {
                System.out.println("Error: wrong price format");
            }
        }
    }

    private Coordinates readCoordinates() {
        System.out.println("Please enter the coordinates you wish to add (not nullable): ");
        Integer x = readX();
        Float y = readY();
        return new Coordinates(x, y);
    }

    private Integer readX() {
        while (true) {
            System.out.println("Enter x coordinate (not nullable) (Integer x <= 793): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("X coordinate cannot be null");
                continue;
            }

            try {
                int x = Integer.parseInt(input);

                if (x > 793) {
                    System.out.println("Error: X coordinate must NOT be greater than 793");
                    continue;
                }

                return x;
            } catch (NumberFormatException e) {
                System.out.println("X coordinate must be an integer");
            }
        }
    }

    private Float readY() {
        while (true) {
            System.out.println("Enter y coordinate (not nullable) (Float y > -429): ");
            String inputY = scanner.nextLine().trim();

            try {
                Float y = Float.parseFloat(inputY);

                if (y <= -429) {
                    System.out.println("Error: Y coordinate must be greater than -429");
                    continue;
                }

                return y;
            } catch (NumberFormatException e) {
                System.out.println("Y coordinate must be float");
            }
        }
    }


    private String readName() {
        while (true) {
            System.out.println("Please enter the name of the ticket (not nullable): ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: Ticket name cannot be empty");
        }
    }
}
