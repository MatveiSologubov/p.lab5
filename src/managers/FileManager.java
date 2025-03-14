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

    public void save(Set<Ticket> collection, String filePath) throws IOException, XMLStreamException {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            this.writer = factory.createXMLStreamWriter(stream, "UTF-8");

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
            writer.close();
            this.writer = null;
        }
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

    public Set<Ticket> load(String filePath) throws IOException, XMLStreamException {
        Set<Ticket> collection = new HashSet<>();
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(filePath))) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(stream);

            Ticket currentTicket = null;
            Coordinates currentCoords = null;
            Person currentPerson = null;
            String currentElement = "";

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        if (currentElement.equals("ticket")) {
                            currentTicket = new Ticket();
                            currentTicket.setId(Long.parseLong(reader.getAttributeValue(null, "id")));
                        } else if (currentElement.equals("coordinates")) {
                            currentCoords = new Coordinates();
                        } else if (currentElement.equals("person")) {
                            currentPerson = new Person();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (!text.isEmpty()) {
                            if (currentTicket != null) {
                                switch (currentElement) {
                                    case "name":
                                        currentTicket.setName(text);
                                        break;
                                    case "creationDate":
                                        currentTicket.setCreationDate(ZonedDateTime.parse(text));
                                        break;
                                    case "price":
                                        currentTicket.setPrice(text.isEmpty() ? null : Float.parseFloat(text));
                                        break;
                                    case "refundable":
                                        currentTicket.setRefundable(Boolean.parseBoolean(text));
                                        break;
                                    case "type":
                                        currentTicket.setType(text.isEmpty() ? null : TicketType.valueOf(text));
                                        break;
                                    case "x":
                                        currentCoords.setX(Integer.parseInt(text));
                                        break;
                                    case "y":
                                        currentCoords.setY(Float.parseFloat(text));
                                        break;
                                    case "birthday":
                                        currentPerson.setBirthday(LocalDateTime.parse(text));
                                        break;
                                    case "height":
                                        currentPerson.setHeight(Integer.parseInt(text));
                                        break;
                                    case "weight":
                                        currentPerson.setWeight(Float.parseFloat(text));
                                        break;
                                    case "passportID":
                                        currentPerson.setPassportID(text.isEmpty() ? null : text);
                                        break;
                                }
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        String elementName = reader.getLocalName();
                        if ("ticket".equals(elementName)) {
                            collection.add(currentTicket);
                            currentTicket = null;
                        } else if ("coordinates".equals(elementName)) {
                            currentTicket.setCoordinates(currentCoords);
                            currentPerson = null;
                        } else if ("person".equals(elementName)) {
                            currentTicket.setPerson(currentPerson);
                            currentPerson = null;
                        }
                        break;
                }
            }
            reader.close();
        }

        return collection;
    }
}
