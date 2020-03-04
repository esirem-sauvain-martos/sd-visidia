import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.Arrays;

public class DeepCourseTerminateLocalyAndCount extends LC0_Algorithm {

    private int parentDoor = -1;
    private String[] neighborStates;

    @Override
    public Object clone() {
        return new DeepCourseTerminateLocalyAndCount();
    }

    @Override
    public String getDescription() {
        return "A---N => A-*-I | A-*-I => F-*-A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("counter", 1);
        neighborStates = new String[getArity()];
        Arrays.fill(neighborStates, "N");
    }

    private boolean checkNeighborhood() {
        boolean result = true;

        for (String neighborState : neighborStates) {
            if (!neighborState.equals("F") && result) {
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
            System.out.println("global stop\nOrder : " + (int)getNeighborProperty("counter") + 1);
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
            setNeighborProperty("counter", (int)getNeighborProperty("counter") + (int)getLocalProperty("counter"));
        }
    }
}
