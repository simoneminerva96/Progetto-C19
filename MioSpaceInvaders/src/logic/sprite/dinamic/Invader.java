package logic.sprite.dinamic;

import logic.sprite.Coordinate;

public class Invader extends AbstractMovable {

    private int value;

    public Invader(Coordinate coordinate, double size, int value) {
        super(coordinate, size);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "{" + "value=" + value + '}' + super.toString();
    }

}