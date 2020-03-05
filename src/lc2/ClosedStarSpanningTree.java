package lc2;

import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class ClosedStarSpanningTree extends LC2_Algorithm {

    @Override
    public Object clone() {
        return new ClosedStarSpanningTree();
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
        if(getLocalProperty("label").equals("A") && getNCount() > 0) {
            for(int portNum : getActiveDoors()) {
                if (getNeighborProperty(portNum, "label").equals("N")) {
                    setNeighborProperty(portNum, "label", "A");
                    setDoorState(new MarkedState(true), portNum);
                }
            }
        }
        else if (getLocalProperty("label").equals("A") && getNCount() == 0)
            localTermination();
    }

    private int getNCount() {
        int res = 0;

        for(int portNum : getActiveDoors()) {
            if(getNeighborProperty(portNum, "label").equals("N"))
                res++;
        }

        return res;
    }
}
