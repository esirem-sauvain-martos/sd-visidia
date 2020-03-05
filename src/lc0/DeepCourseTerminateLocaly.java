package lc0;

import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.Arrays;

public class DeepCourseTerminateLocaly extends LC0_Algorithm {

    private int parentDoor = -1;
    private String[] neighborStates;

    @Override
    public Object clone() {
        return new DeepCourseTerminateLocaly();
    }

    @Override
    public String getDescription() {
        return "A---N => A-*-I | A-*-I => F-*-A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        neighborStates = new String[getArity()];
        Arrays.fill(neighborStates, "N");
    }

    private boolean checkNeighborhood() {
        boolean result = true;

        for(int i = 0; i < neighborStates.length; i++) {
            if(!neighborStates[i].equals("F") && result) {
                result = false;
            }
        }

        return result;
    }

    @Override
    protected void onStarCenter() {
        neighborStates[neighborDoor] = (String)getNeighborProperty("label");

        System.out.println("Local state : " + getLocalProperty("label") + " | Peer state : " + getNeighborProperty("label"));

        if(getLocalProperty("label").equals("F") && checkNeighborhood()) {
            localTermination();
            System.out.println("stop");
        }
        else if(getLocalProperty("label").equals("A") && checkNeighborhood()) {
            globalTermination();
            System.out.println("global stop");
        }
        else if(getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("A")) {
            setLocalProperty("label", "A");
            setNeighborProperty("label", "I");
            setDoorState(new MarkedState(true), neighborDoor);
            parentDoor = neighborDoor;
        }
        else if(neighborDoor == parentDoor &&
                getLocalProperty("label").equals("A") &&
                getNeighborProperty("label").equals("I") &&
                Arrays.asList(neighborStates).indexOf("N") == -1) {

            setLocalProperty("label", "F");
            setNeighborProperty("label", "A");
        }
    }
}
