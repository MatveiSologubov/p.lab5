import java.time.ZonedDateTime;

public class Ticket {
	private static long id = 1; // Значение поля должно быть больше 0, Значение этого поля должно быть
							// уникальным, Значение этого поля должно генерироваться автоматически
	private String name; // Поле не может быть null, Строка не может быть пустой
	private Coordinates coordinates; // Поле не может быть null
	private java.time.ZonedDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
													// автоматически
	private Float price; // Поле может быть null, Значение поля должно быть больше 0
	private String comment; // Длина строки не должна быть больше 855, Поле может быть null
	private Boolean refundable; // Поле не может быть null
	private TicketType type; // Поле может быть null
	private Person person; // Поле может быть null

	public Ticket(String name, Coordinates coordinates, ZonedDateTime creationDate, Float price,
			String comment, Boolean refundable, TicketType type, Person person) {
		this.name = name;
		this.coordinates = coordinates;
		this.creationDate = creationDate;
		this.price = price;
		this.comment = comment;
		this.refundable = refundable;
		this.type = type;
		this.person = person;
	}
}
