package lc0;

import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.Arrays;

public class DeepCourse extends LC0_Algorithm {

    private int parentDoor = -1;
    private String[] neighborStates;

    @Override
    public Object clone() {
        return new DeepCourse();
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

    @Override
    protected void onStarCenter() {
        neighborStates[neighborDoor] = (String)getNeighborProperty("label");

        if(getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("A")) {
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
