package Main;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.awt.*;

public class GamePadHandler {
    Controller controller = null;
    GamePanel gp;
    public Point right = new Point();
    Point left = new Point();
    boolean [] button = new boolean[8];
    public boolean controllerOn = false;


    public GamePadHandler(GamePanel gp){
        this.gp = gp;
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for(int i = 0; i<controllers.length; i++) {
            if (controllers[i].getType() == Controller.Type.GAMEPAD) {
                System.out.println(controllers[i] + " " + controllers[i].getType());
                controller = controllers[i];
            }
        }
        if(controller!=null){
            controllerOn = true;
        }
        System.out.println(controller);
    }
    public void update(){
        /* Remember to poll each one */
        controller.poll();

        /* Get the controllers event queue */
        EventQueue queue = controller.getEventQueue();

        /* Create an event object for the underlying plugin to populate */
        Event event = new Event();

        /* For each object in the queue */
        while (queue.getNextEvent(event)) {


            /*
             * Create a string buffer and put in it, the controller name,
             * the time stamp of the event, the name of the component
             * that changed and the new value.
             *
             * Note that the timestamp is a relative thing, not
             * absolute, we can tell what order events happened in
             * across controllers this way. We can not use it to tell
             * exactly *when* an event happened just the order.
             */
            StringBuffer buffer = new StringBuffer(controller
                    .getName());
            buffer.append(" at ");
            buffer.append(event.getNanos()).append(", ");
            Component comp = event.getComponent();
            buffer.append(comp.getName()).append(" changed to ");
            float value = event.getValue();
            //value = value * 100;
            //value = (int) value;

            /*
             * Check the type of the component and display an
             * appropriate value
             */
            if (comp.isAnalog()) {

            } else {

                if (value == 1.0f) {
                    buffer.append("On");
                } else {
                    buffer.append("Off");
                }
            }

            buttons(comp, value);
            buttonsEffect();
            leftStick(comp, value);
            rightStick(comp, value);
        }

    }
    //Map buttons, remap
    public void buttonsEffect(){
        //Dash
        if(button[0] || button[4]){gp.keyH.spacePressed = true;}
        else{gp.keyH.spacePressed = false;}
        //Attack
        if(button[2] || button[5]){gp.mouseH.leftClick = true;}
        else{gp.mouseH.leftClick = false;}

        gp.keyH.tPressed = button[3];
        gp.keyH.enterPressed = button[7];
    }
    public void buttons(Component comp, float value){
        switch (comp.getName()){
            case "Button 0":
                if (value == 1.0f) {
                    button[0] = true;
                    System.out.println(button[0]);
                } else {
                    button[0] = false;
                    System.out.println(button[0]);
                }
                break;
            case "Button 1":
                if (value == 1.0f) {
                    button[1] = true;
                    System.out.println(button[1]);
                } else {
                    button[1] = false;
                    System.out.println(button[1]);
                }
                break;
            case "Button 2":
                if (value == 1.0f) {
                    button[2] = true;
                    System.out.println(button[2]);
                } else {
                    button[2] = false;
                    System.out.println(button[2]);
                }
                break;
            case "Button 3":
                if (value == 1.0f) {
                    button[3] = true;
                    System.out.println(button[3]);
                } else {
                    button[3] = false;
                    System.out.println(button[3]);
                }
                break;
            case "Button 4":
                if (value == 1.0f) {
                    button[4] = true;
                    System.out.println(button[4]);
                } else {
                    button[4] = false;
                    System.out.println(button[4]);
                }
                break;
            case "Button 5":
                if (value == 1.0f) {
                    button[5] = true;
                    System.out.println(button[5]);
                } else {
                    button[5] = false;
                    System.out.println(button[5]);
                }
                break;
            case "Button 6":
                if (value == 1.0f) {
                    button[6] = true;
                    System.out.println(button[6]);
                } else {
                    button[6] = false;
                    System.out.println(button[6]);
                }
                break;
            case "Button 7":
                if (value == 1.0f) {
                    button[7] = true;
                    System.out.println(button[7]);
                } else {
                    button[7] = false;
                    System.out.println(button[7]);
                }
                break;
        }
    }
    public void rightStick(Component comp, float value){

        if(comp.getName().equals("Y Rotation") || comp.getName().equals("Z Rotation")){
            right.y = (int)(value*100);
        }
        if(comp.getName().equals("X Rotation") || comp.getName().equals("Z Axis")){
            right.x = (int)(value*100);
        }

        System.out.println("X : " + right.x);
        System.out.println("Y : " + right.y);
        if(right.x>0 && right.y<0){
            gp.player.orientation=0;
        }
        if(right.x>0 && right.y>0){
            gp.player.orientation=1;
        }
        if(right.x<0 && right.y>0){
            gp.player.orientation=2;
        }

        if(right.x<0 && right.y<0){
            gp.player.orientation=3;
        }
    }
    public void leftStick(Component comp, float value){

        if(comp.getName().equals("Y Axis")){
            left.y = (int)(value*100);
        }
        if(comp.getName().equals("X Axis")){
            left.x = (int)(value*100);
        }
        //System.out.println("X : " + right.x);
        //System.out.println("Y : " + right.y);
        if(left.x<-40){
            System.out.println("left");
            gp.keyH.leftPressed = true;
        }
        else {
            gp.keyH.leftPressed = false;
        }
        if(left.x>40){
            System.out.println("right");
            gp.keyH.rightPressed = true;
        }
        else{
            gp.keyH.rightPressed = false;
        }
        if(left.y<-40){
            System.out.println("up");
            gp.keyH.upPressed = true;
        }
        else{
            gp.keyH.upPressed = false;
        }
        if(left.y>40){
            System.out.println("down");
            gp.keyH.downPressed = true;
        }
        else {
            gp.keyH.downPressed = false;
        }
    }
}
