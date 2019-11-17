package gameObjeto;

import entidades.MovingIsoEntity;
import sample.Main;

public class Basura extends MovingIsoEntity {
    private boolean collision;
    private boolean moving;

    public Basura(double x, double y, double width, double height, double hitboxSize) {
        super(x, y, width, height, hitboxSize);
        moving=false;
        collision=false;
    }
    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public void move() {
        if(moving)
        {
           setX(Main.getJugador().getX());
           setY(Main.getJugador().getY()-50);
           setHitboxX(Main.getJugador().getX());
           setHitboxY(Main.getJugador().getY()-50);
        }
    }
}
