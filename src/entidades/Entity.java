package entidades;



import java.io.Serializable;

public abstract class Entity implements Serializable {

    private double x;
    private double y;
    private double width;
    private double height;

    public Entity(double x, double y, double width, double height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    /*
        Devuelve 1 en caso de que las coordenadas se encuentren en culaquier punto
        -dentro- del espacio que ocupa la entidad. De otro modo, devuelve 0.
    */
    public int containsPoint(int x, int y) {
        if ((x >= getX() && x <= getX() + getWidth()) && (y >= getY() && y <= getY() + getHeight())) {
            return 1;
        }

        return 0;
    }

    /*
        Devuelve 1 en caso de que las coordenadas y datos enviados se encuentren
        en culaquier punto -dentro- del espacio que ocupa la entidad. De otro modo, devuelve 0.
    */
    public int collisionsWith(double x, double y, double width, double height) {
        if ((x + width >= this.x && x <= this.x + this.width) &&
                (y + height >= this.y && y <= this.y + this.height)) {

            return 1;
        }

        return 0;
    }

    /*
        Devuelve 1 en caso de que las coordenadas y datos enviados se encuentren
        en culaquier punto justo a un lado del espacio que ocupa la entidad. De otro modo, devuelve 0.
        [No para esquinas]
    */
    public int nextTo(double x, double y, double width, double height) {
        if ((x + width + 1 >= this.x && x - 1 <= this.x + this.width) &&
                (y + height + 1 >= this.y && y - 1 <= this.y + this.height)) {

            return 1;
        }

        return 0;
    }
}

