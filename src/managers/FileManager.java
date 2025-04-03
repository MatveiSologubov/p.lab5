package src.managers;

import src.models.Coordinates;
import src.models.Person;
import src.models.Ticket;
import src.models.TicketType;

import javax.xml.stream.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class FileManager {
    private XMLStreamWriter writer;
    private int indentLevel = 0;

    public void save(Set<Ticket> collection, String filePath) {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            this.writer = factory.createXMLStreamWriter(stream, "UTF-8");
            writeXmlContent(collection);
            writer.close();
        } catch (XMLStreamException | IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        } finally {
            this.writer = null;
        }
    }

    private void writeXmlContent(Set<Ticket> collection) throws XMLStreamException {
        indentLevel = 0;
        final String lineSeparator = System.lineSeparator();

        writer.writeStartDocument("UTF-8", "1.0");
        writer.writeCharacters(lineSeparator);
        writeStartElement("tickets");

        for (Ticket ticket : collection) {
            // Start ticket element
            writeStartElement("ticket");
            writer.writeAttribute("id", String.valueOf(ticket.getId()));

            // Name
            writeElement("name", ticket.getName());

            // Coordinates
            writeStartElement("coordinates");
            writeElement("x", ticket.getCoordinates().getX().toString());
            writeElement("y", ticket.getCoordinates().getY().toString());
            writeEndElement();

            // Creation Date
            writeElement("creationDate", ticket.getCreationDate().toString());

            // Price (optional)
            if (ticket.getPrice() != null) {
                writeElement("price", ticket.getPrice().toString());
            }

            // Refundable
            writeElement("refundable", ticket.getRefundable().toString());

            // Type (optional)
            if (ticket.getType() != null) {
                writeElement("type", ticket.getType().name());
            }

            // Person (optional)
            if (ticket.getPerson() != null) {
                writeStartElement("person");
                Person person = ticket.getPerson();
                if (person.getBirthday() != null) {
                    writeElement("birthday", person.getBirthday().toString());
                }
                writeElement("height", person.getHeight().toString());
                writeElement("weight", String.valueOf(person.getWeight()));
                if (person.getPassportID() != null && !person.getPassportID().isEmpty()) {
                    writeElement("passportID", person.getPassportID());
                }
                writeEndElement();
            }

            // End ticket element
            writeEndElement();
        }

        // End tickets element
        writeEndElement();
        writer.writeEndDocument();
    }

    private void writeStartElement(String element)
            throws XMLStreamException {
        writeIndentation();
        writer.writeStartElement(element);
        indentLevel++;
    }

    private void writeEndElement()
            throws XMLStreamException {
        indentLevel--;
        writeIndentation();
        writer.writeEndElement();
    }

    private void writeElement(String name, String value)
            throws XMLStreamException {
        writeIndentation();
        writer.writeStartElement(name);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }

    private void writeIndentation() throws XMLStreamException {
        String indent = "  ".repeat(indentLevel); // 2 spaces per indent
        writer.writeCharacters(System.lineSeparator() + indent);
    }

    public Set<Ticket> load(String filePath) {
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(filePath))) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(stream);
            return new XmlParser().parse(reader);
        } catch (XMLStreamException | IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
            return new HashSet<>();
        }
    }

    private static class XmlParser {
        private final Set<Ticket> collection = new HashSet<>();
        private Ticket currentTicket;
        private Coordinates currentCoordinates;
        private Person currentPerson;
        private String currentElement;

        public Set<Ticket> parse(XMLStreamReader reader) throws XMLStreamException {
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT -> handleStartElement(reader);
                    case XMLStreamConstants.CHARACTERS -> handleCharacters(reader);
                    case XMLStreamConstants.END_ELEMENT -> handleEndElement(reader);
                }
            }
            return collection;
        }

        private void handleStartElement(XMLStreamReader reader) {
            currentElement = reader.getLocalName();
            switch (currentElement) {
                case "ticket" -> initNewTicket(reader);
                case "coordinates" -> currentCoordinates = new Coordinates();
                case "person" -> currentPerson = new Person();
            }
        }

        private void handleCharacters(XMLStreamReader reader) {
            String text = reader.getText().trim();
            if (text.isEmpty() || currentTicket == null) return;

            switch (currentElement) {
                case "name" -> currentTicket.setName(text);
                case "creationDate" -> currentTicket.setCreationDate(ZonedDateTime.parse(text));
                case "price" -> currentTicket.setPrice(Float.parseFloat(text));
                case "refundable" -> currentTicket.setRefundable(Boolean.parseBoolean(text));
                case "type" -> currentTicket.setType(TicketType.valueOf(text));
                case "x" -> currentCoordinates.setX(Integer.parseInt(text));
                case "y" -> currentCoordinates.setY(Float.parseFloat(text));
                case "birthday" -> currentPerson.setBirthday(LocalDateTime.parse(text));
                case "height" -> currentPerson.setHeight(Integer.parseInt(text));
                case "weight" -> currentPerson.setWeight(Float.parseFloat(text));
                case "passportID" -> currentPerson.setPassportID(text);
            }
        }

        private void handleEndElement(XMLStreamReader reader) {
            switch (reader.getLocalName()) {
                case "ticket" -> completeTicket();
                case "coordinates" -> attachCoordinates();
                case "person" -> attachPerson();
            }
        }

        private void initNewTicket(XMLStreamReader reader) {
            currentTicket = new Ticket();
            currentTicket.setId(Long.parseLong(reader.getAttributeValue(null, "id")));
        }

        private void completeTicket() {
            collection.add(currentTicket);
            currentTicket = null;
        }

        private void attachCoordinates() {
            currentTicket.setCoordinates(currentCoordinates);
            currentCoordinates = null;
        }

        private void attachPerson() {
            currentTicket.setPerson(currentPerson);
            currentPerson = null;
        }
    }
}
