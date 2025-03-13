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
    public void save(Set<Ticket> collection, String filePath) throws IOException, XMLStreamException {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(stream, "UTF-8");

            int indentLevel = 0;
            final String lineSeparator = System.lineSeparator();

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeCharacters(lineSeparator);
            writeIndentedStartElement(writer, "tickets", indentLevel++);

            for (Ticket ticket : collection) {
                // Start ticket element
                writeIndentedStartElement(writer, "ticket", indentLevel);
                writer.writeAttribute("id", String.valueOf(ticket.getId()));
                indentLevel++;

                // Name
                writeIndentedElement(writer, "name", ticket.getName(), indentLevel);

                // Coordinates
                writeIndentedStartElement(writer, "coordinates", indentLevel);
                indentLevel++;
                writeIndentedElement(writer, "x", ticket.getCoordinates().getX().toString(), indentLevel);
                writeIndentedElement(writer, "y", ticket.getCoordinates().getY().toString(), indentLevel);
                indentLevel--;
                writeIndentedEndElement(writer, "coordinates", indentLevel);

                // Creation Date
                writeIndentedElement(writer, "creationDate", ticket.getCreationDate().toString(), indentLevel);

                // Price (optional)
                if (ticket.getPrice() != null) {
                    writeIndentedElement(writer, "price", ticket.getPrice().toString(), indentLevel);
                }

                // Refundable
                writeIndentedElement(writer, "refundable", ticket.getRefundable().toString(), indentLevel);

                // Type (optional)
                if (ticket.getType() != null) {
                    writeIndentedElement(writer, "type", ticket.getType().name(), indentLevel);
                }

                // Person (optional)
                if (ticket.getPerson() != null) {
                    writeIndentedStartElement(writer, "person", indentLevel);
                    indentLevel++;
                    Person p = ticket.getPerson();
                    if (p.getBirthday() != null) {
                        writeIndentedElement(writer, "birthday", p.getBirthday().toString(), indentLevel);
                    }
                    writeIndentedElement(writer, "height", p.getHeight().toString(), indentLevel);
                    writeIndentedElement(writer, "weight", String.valueOf(p.getWeight()), indentLevel);
                    if (p.getPassportID() != null && !p.getPassportID().isEmpty()) {
                        writeIndentedElement(writer, "passportID", p.getPassportID(), indentLevel);
                    }
                    indentLevel--;
                    writeIndentedEndElement(writer, "person", indentLevel);
                }

                // End ticket element
                indentLevel--;
                writeIndentedEndElement(writer, "ticket", indentLevel);
            }

            // End tickets element
            indentLevel--;
            writeIndentedEndElement(writer, "tickets", indentLevel);
            writer.writeEndDocument();
            writer.close();
        }
    }

    private void writeIndentedStartElement(XMLStreamWriter writer, String element, int indentLevel)
            throws XMLStreamException {
        writeIndentation(writer, indentLevel);
        writer.writeStartElement(element);
    }

    private void writeIndentedEndElement(XMLStreamWriter writer, String element, int indentLevel)
            throws XMLStreamException {
        writeIndentation(writer, indentLevel);
        writer.writeEndElement();
    }

    private void writeIndentedElement(XMLStreamWriter writer, String name, String value, int indentLevel)
            throws XMLStreamException {
        writeIndentation(writer, indentLevel);
        writer.writeStartElement(name);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }

    private void writeIndentation(XMLStreamWriter writer, int indentLevel) throws XMLStreamException {
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
                        if ("ticket".equals(currentElement)) {
                            currentTicket = new Ticket();
                            currentTicket.setId(Long.parseLong(reader.getAttributeValue(null, "id")));
                        } else if ("coordinates".equals(currentElement)) {
                            currentCoords = new Coordinates();
                        } else if ("person".equals(currentElement)) {
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
