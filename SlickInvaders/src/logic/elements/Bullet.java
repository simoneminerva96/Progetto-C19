package logic.elements;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import states.SinglePlayerState;

public class Bullet implements CollisionElement{
    private Image image;
    private float x;
    private float y;
    private Shape shape;
    private float size;

    private GameContainer container;
    private static final float PROP_SPEED = 0.0008f;
    private static final float PROP_SIZE = 0.03f;

    public Bullet(GameContainer container,float x, float y) throws SlickException {
        this.container = container;
        image = new Image("res/Laser.png");
        this.x = x;
        this.y = y;
        size = container.getHeight()*PROP_SIZE;
        this.shape = new Rectangle(x,y,size,size);
    }

    @Override
    public void update(GameContainer container, int delta) {
        y -= container.getHeight()*PROP_SPEED*delta;
        shape.setY(y);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) {
        image.draw(x,y,size,size);
    }

    public boolean collides(Shape s) {
        return shape.intersects(s);
    }

    public boolean endReached(){
        if(y<=0){
            return true;
        }
        return false;
    }

    public float getY() {
        return y;
    }

}
