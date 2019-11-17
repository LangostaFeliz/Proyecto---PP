package gameObjeto;

import entidades.IsometricEntity;
import entidades.MovingIsoEntity;
import sample.Main;

public class BoteAzul extends MovingIsoEntity {
    public BoteAzul(double x, double y, double width, double height, double hitboxSize) {
        super(x, y, width, height, hitboxSize);
    }

    @Override
    public void move() {
        if(Main.getJugador().getX()>Main.getCamion().getX()&&Main.getDx()>0)
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
