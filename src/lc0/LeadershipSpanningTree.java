package lc0;

import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.Arrays;

public class LeadershipSpanningTree extends LC0_Algorithm {

    private int parentDoor = -1;
    private String[] neighborStates;

    @Override
    public Object clone() {
        return new DeepCourse();
    }

    @Override
    public String getDescription() {
        return "Test";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        neighborStates = new String[getArity()];
        Arrays.fill(neighborStates, "N");
    }

    @Override
    protected void onStarCenter() {
//        System.out.println("Test");
//
//        neighborStates[neighborDoor] = (String)getNeighborProperty("label");
//
//        if(getLocalProperty("label").equals("N") &&
//                getNeighborProperty("label").equals("A")) {
//
//            setLocalProperty("label", "A");
//            setNeighborProperty("label", "I");
//            setDoorState(new MarkedState(true), neighborDoor);
//            parentDoor = neighborDoor;
//        }
//        else if(getLocalProperty("label").equals("I") && getNeighborProperty("label").equals("I") && neighborDoor == parentDoor) {
//            setLocalProperty("label", "F");
//        }
        if(getLocalProperty("label").equals("A") && getNeighborProperty("label").equals("N")) {
            setNeighborProperty("label", "A");
            setDoorState(new MarkedState(true), neighborDoor);
        }
    }
}
