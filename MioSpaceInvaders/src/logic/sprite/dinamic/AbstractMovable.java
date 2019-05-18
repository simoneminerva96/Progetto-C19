package logic.sprite.dinamic;

import logic.sprite.Coordinate;
import logic.sprite.Sprite;

public abstract class AbstractMovable extends Sprite implements Movable{

    private final static double MOVE_OFFSET = 10;

    public AbstractMovable(Coordinate coordinate, double size) {
        super(coordinate, size);
    }

    public Coordinate moveLeft() {
        super.setX(super.getX() - MOVE_OFFSET);
        return super.getCoordinate();
    }

    public Coordinate moveRight() {
        super.setX(super.getX() + MOVE_OFFSET);
        return super.getCoordinate();
    }

    public Coordinate moveUp() {
        super.setY(super.getY() - MOVE_OFFSET);
        return super.getCoordinate();
    }

    public Coordinate moveDown() {
        super.setY(super.getY() + MOVE_OFFSET);
        return super.getCoordinate();
    }
}
