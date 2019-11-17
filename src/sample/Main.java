package sample;

import botones.Button;
import controller.TouchControl;
import gameObjeto.ArrayBasura;
import gameObjeto.Basura;
import gameObjeto.BoteAzul;
import gameObjeto.Camion;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import people.Player;
import people.StatePlayer;
import reproductor.MusicPlayer;
import resourceLoaders.ImageLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main extends Application {

    private Canvas canvas=new Canvas(1024,600);
    private Group grupo;
    private GraphicsContext gc;
    private static StateGame stateGame;
    private Button botonA;
    private Button botonB;
    private Button dpad;
    final long starNanoTime=System.nanoTime();
    private static double dx;
    private static double dy;
    private int puntaje=0;
    Text t=new Text();

    private static ArrayBasura arrayBasura=new ArrayBasura();
    private static Player jugador = new Player(100,100,32,48,0.5);
    private static Camion camion = new Camion(100,200,200,100,1);
    private static BoteAzul boteAzul= new BoteAzul(50,250,50,50,1);
    Iterator<Basura> array=arrayBasura.getArrayBasura().iterator();
    List<Basura> remove=new ArrayList();


    @Override
    public void init() throws Exception
    {
        stateGame=StateGame.playing;
        initializeControls();
        initializeGroup();
        t.setText("EL puntaje es:"+puntaje);
        t.setX(0);
        t.setY(40);
        t.setFont(Font.font("Verdana",30));
        addComponet();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setupPrimaryStage(primaryStage);
        gc=canvas.getGraphicsContext2D();
        primaryStage.setScene(new Scene(grupo));
        addComponet();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t =(now-starNanoTime)/1000000000.0;
                updateLogic();

                updateGraphic(gc,t);

            }
        }.start();

        MusicPlayer reproductor = new MusicPlayer("resources/sounds/music/", "p5.mp3");
        reproductor.setVolume(0);
        reproductor.fadeToVolume(0.8);
        reproductor.play();

        primaryStage.show();
    }


    /*
        Define propiedades basicas del stage.
     */
    private void setupPrimaryStage(Stage primaryStage) {
        primaryStage.setAlwaysOnTop(true);
        primaryStage.centerOnScreen();
        primaryStage.requestFocus();
        primaryStage.setTitle("Proyecto - PP");
    }

    private void initializeControls() {
        botonA = TouchControl.getBotonA();
        botonB = TouchControl.getBotonB();
        dpad = TouchControl.getDpad();
    }

    public void updateLogic()
    {
        if(stateGame==StateGame.playing)
        {   t.setText("EL puntaje es:"+puntaje);
            camion.move();
            jugador.move();
            boteAzul.move();
            arrayBasura.getArrayBasura().forEach(basura -> {
                basura.move();
                if (basura.collisionsWith(jugador.getHitboxX() + dx, jugador.getHitboxY() + dy, jugador.getHitboxWidth(), jugador.getHitboxHeight()) == 1) {
                    jugador.setColisionado(true);
                    basura.setCollision(true);
                } else {
                    jugador.setColisionado(false);
                    basura.setCollision(false);
                }
                if(basura.collisionsWith(camion.getHitboxX(),camion.getHitboxY(),camion.getHitboxWidth(),camion.getHitboxHeight())==1)
                {   // si el camion esta chocando a una basura se agrega esa basura a la lista de elimina
                        remove.add(basura);
                }
                if(basura.collisionsWith(boteAzul.getHitboxX(),boteAzul.getHitboxY(),boteAzul.getHitboxWidth(),boteAzul.getHitboxHeight())==1)
                {   // si el camion esta chocando a una basura se agrega esa basura a la lista de elimina
                    remove.add(basura);

                    puntaje++;

                }
            });

            //Lista de elimianr (si es size de la lista eliminar se elimina)
            if(remove.size()>0)
            {
                arrayBasura.getArrayBasura().removeAll(remove);
                jugador.setOcupado(false);
            }
        }
    }

    public void updateGraphic(GraphicsContext gc,double t)
    {   gc.clearRect(0,0,1024,600);
        if(stateGame==StateGame.playing)
        {
                for (Basura basura:
                        arrayBasura.getArrayBasura()) {
                    gc.drawImage(ImageLoader.spritePlastico,basura.getX(),basura.getY(),basura.getWidth(),basura.getHeight());
                }
                gc.drawImage(ImageLoader.spriteBoteAzul,boteAzul.getHitboxX(),boteAzul.getHitboxY(),boteAzul.getWidth(),boteAzul.getWidth());
                gc.drawImage(ImageLoader.spriteCamion,camion.getX(),camion.getY(),camion.getWidth(),camion.getHeight());
                paintPlayer(gc,t);

        }

    }

    private void initializeGroup() {
        //Aqui va la configuracion inicial del grupo
        grupo = new Group();

    }

    public void addComponet()
    {   grupo.getChildren().clear();
      grupo.getChildren().add(canvas);
        if(stateGame==StateGame.playing)
        {
            grupo.getChildren().addAll(botonA, botonB, dpad,t);

        }
    }

        public void paintPlayer(GraphicsContext gc,double t)
        {
            if (StatePlayer.abajo==jugador.getState()) {
                if(dy!=0)gc.drawImage(ImageLoader. caminaAbajo.getFrame(t),jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
                else gc.drawImage(ImageLoader.paradoAbajo,jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
            }
            else if(jugador.getState()==StatePlayer.arriba)
            {
                if (dy!=0) gc.drawImage(ImageLoader.caminaArriba.getFrame(t),jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
                else gc.drawImage(ImageLoader.paradoArriba,jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
            }
            else if(jugador.getState()==StatePlayer.derecha)
            {
                if (dx!=0) gc.drawImage(ImageLoader.caminaderecho.getFrame(t),jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
                else gc.drawImage(ImageLoader.paradoDerecho,jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
            }
            else if(jugador.getState()==StatePlayer.izquierda)
            {
                if(dx!=0) gc.drawImage(ImageLoader.caminaIzquierda.getFrame(t),jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
                else gc.drawImage(ImageLoader.paradoIzquierda,jugador.getX(),jugador.getY(),jugador.getWidth(),jugador.getHeight());
            }


        }


    public static double getDx() {
        return dx;
    }

    public static void setDx(double dx) {
        Main.dx = dx;
    }

    public static double getDy() {
        return dy;
    }

    public static void setDy(double dy) {
        Main.dy = dy;
    }

    public static Player getJugador() {
        return jugador;
    }

    public static void setJugador(Player jugador) {
        Main.jugador = jugador;
    }

    public static ArrayBasura getArrayBasura() {
        return arrayBasura;
    }
    public static Camion getCamion() {
        return camion;
    }

    public static void setCamion(Camion camion) {
        Main.camion = camion;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
