package src.models;

public class Coordinates {
    private Integer x; // Максимальное значение поля: 793, Поле не может быть null
    private Float y; // Значение поля должно быть больше -429, Поле не может быть null

    public Coordinates() {
    }


    public Coordinates(Integer x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Float getY() {
        return this.y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
