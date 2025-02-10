import java.time.LocalDateTime;

public class Person {
	private LocalDateTime birthday; // Поле может быть null
	private Integer height; // Поле не может быть null, Значение поля должно быть больше 0
	private float weight; // Значение поля должно быть больше 0
	private String passportID; // Строка не может быть пустой, Поле может быть null

	public Person(LocalDateTime birthday, Integer height, float weight, String passportID) {
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
		this.passportID = passportID;
	}

	public LocalDateTime getBirthday() {
		return this.birthday;
	}

	public Integer getHeight() {
		return this.height;
	}

	public float getWeight() {
		return this.weight;
	}

	public String getPassportID() {
		return this.passportID;
	}
}
