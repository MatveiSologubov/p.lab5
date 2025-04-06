package src.models.builders;

import src.models.Coordinates;
import src.models.Person;
import src.models.Ticket;
import src.models.TicketType;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class TicketBuilder extends Builder<Ticket> {
    private final Scanner scanner;

    public TicketBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Ticket build() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        Float price = readPrice();
        String comment = readComment();
        Boolean refundable = readRefundable();
        TicketType ticketType = readTicketType();
        Person person = new PersonBuilder(scanner).build();
        return new Ticket(name, coordinates, ZonedDateTime.now(), price, comment, refundable, ticketType, person);
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
                float price = Float.parseFloat(input);
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
                float y = Float.parseFloat(inputY);

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
