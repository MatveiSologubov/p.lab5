package src.models;

import java.time.ZonedDateTime;

public class Ticket {
	private static long idCounter = 1; // Значение поля должно быть больше 0, Значение этого поля должно быть
                            // уникальным, Значение этого поля должно генерироваться автоматически
    private long id;
	private String name; // Поле не может быть null, Строка не может быть пустой
	private Coordinates coordinates; // Поле не может быть null
	private java.time.ZonedDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
													// автоматически
	private Float price; // Поле может быть null, Значение поля должно быть больше 0
	private String comment; // Длина строки не должна быть больше 855, Поле может быть null
	private Boolean refundable; // Поле не может быть null
	private TicketType type; // Поле может быть null
	private Person person; // Поле может быть null

	public Ticket(){}

	public Ticket(String name, Coordinates coordinates, ZonedDateTime creationDate, Float price,
			String comment, Boolean refundable, TicketType type, Person person) {
        this.id = idCounter++;
		this.name = name;
		this.coordinates = coordinates;
		this.creationDate = creationDate;
		this.price = price;
		this.comment = comment;
		this.refundable = refundable;
		this.type = type;
		this.person = person;
	}
	
	public long getId(){
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", refundable=" + refundable +
                ", type=" + type +
                ", person=" + person +
                '}';
    }
}
