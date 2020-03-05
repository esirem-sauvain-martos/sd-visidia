package lc1;

import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class OpenStarSpanningTree extends LC1_Algorithm {

    @Override
    public Object clone() {
        return new OpenStarSpanningTree();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
        if(getLocalProperty("label").equals("N")) {
            for(int i = 0; i < getActiveDoors().size(); i++) {
                int portNum = getActiveDoors().get(i);
                if(getNeighborProperty(portNum, "label").equals("A")) {
                    setDoorState(new MarkedState(true), portNum);
                    setLocalProperty("label", "A");
                    break;
                }
            }
        }
    }
}
