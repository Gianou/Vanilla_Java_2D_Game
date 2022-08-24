

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.awt.*;

import static net.java.games.input.Component.Identifier.Key.X;

/**
 * This class shows how to use the event queue system in JInput. It will show
 * how to get the controllers, how to get the event queue for a controller, and
 * how to read and process events from the queue.
 *
 * @author Endolf
 */
public class ReadAllEventsOne {

    public ReadAllEventsOne() {

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        Controller controller = null;

        Point right = new Point();

        for(int i = 0; i<controllers.length; i++) {
            if (controllers[i].getType() == Controller.Type.GAMEPAD) {
                System.out.println(controllers[i] + " " + controllers[i].getType());
                controller = controllers[i];
            }
        }

        while (true) {


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

                    buffer.append(value);

                    //Sticks


                } else {
                    if (value == 1.0f) {
                        buffer.append("On");
                    } else {
                        buffer.append("Off");
                    }
                }
                    /*
                    if(!comp.getName().equals("Y Axis") && !comp.getName().equals("X Axis")){
                        System.out.println(comp.getName());
                        System.out.println(buffer.toString());
                    }

                     */

                if(comp.getName().equals("Y Axis")){
                    right.y = (int)(value*100);
                }
                if(comp.getName().equals("X Axis")){
                    right.x = (int)(value*100);
                }
                //System.out.println("X : " + right.x);
                //System.out.println("Y : " + right.y);
                if(right.x<-40){
                    System.out.println("left");
                }
                if(right.x>40){
                    System.out.println("right");
                }
                if(right.y<-40){
                    System.out.println("up");
                }
                if(right.y>40){
                    System.out.println("down");
                }

            }


            /*
             * Sleep for 20 milliseconds, in here only so the example doesn't
             * thrash the system.
             */
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new ReadAllEventsOne();
    }
}