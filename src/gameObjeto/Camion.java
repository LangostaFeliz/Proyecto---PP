package gameObjeto;

import entidades.MovingIsoEntity;
import sample.Main;

public class Camion extends MovingIsoEntity {
    public Camion(double x, double y, double width, double height, double hitboxSize) {
        super(x, y, width, height, hitboxSize);
    }

    @Override
    public void move() {
        if(Main.getJugador().getX()>getX()&&Main.getDx()>0)
        {
            setX(getX() -0.5);
            setHitboxX(getHitboxX() - 0.5);
        }
        else
        {
            setX(getX() + 0.16);
            setHitboxX(getHitboxX() + 0.16);
        }
    }
}
