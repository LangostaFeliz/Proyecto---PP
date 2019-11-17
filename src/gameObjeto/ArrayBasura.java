package gameObjeto;

import java.util.ArrayList;

public  class ArrayBasura {


    private  ArrayList<Basura> arrayBasura=new ArrayList<>();
    private  Basura botella1 = new Basura(100,300,40,40,1);
    private  Basura botella2 = new Basura(230,350,40,40,1);
    private  Basura botella3 = new Basura(430,270,40,40,1);
    private  Basura botella4 = new Basura(600,300,40,40,1);
    private  Basura botella5 = new Basura(320,110,40,40,1);
    private  Basura botella6 = new Basura(111,150,40,40,1);
    private  Basura botella7 = new Basura(677,30,40,40,1);
    private  Basura botella8 = new Basura(700,400,40,40,1);
    private  Basura botella9= new Basura(890,40,40,40,1);

    public ArrayBasura() {
        arrayBasura.add(botella1);
        arrayBasura.add(botella2);
        arrayBasura.add(botella3);
        arrayBasura.add(botella4);
        arrayBasura.add(botella5);
        arrayBasura.add(botella6);
        arrayBasura.add(botella7);
        arrayBasura.add(botella8);
        arrayBasura.add(botella9);



    }

    public  ArrayList<Basura> getArrayBasura() {
        return arrayBasura;
    }

    public  void setArrayBasura(ArrayList<Basura> arrayBasura) {
        this.arrayBasura = arrayBasura;
    }

    public void remove(int numero)
    {
        arrayBasura.remove(numero);
    }

}
