package lc2;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC2_Algorithm;

public class ClosedStarElection extends LC2_Algorithm {
    @Override
    public Object clone() {
        return new ClosedStarElection();
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("activeCounter", getArity());
        putProperty("Neighbors", getLocalProperty("activeCounter"), SimulationConstants.PropertyStatus.DISPLAYED);
    }

    @Override
    protected void onStarCenter() {
        if (getLocalProperty("label").equals("N")) {
            if(getNCount() == 0) {
                setLocalProperty("label", "E");
            } else {
                for (int portNum : getActiveDoors()) {
                    if((int)getNeighborProperty(portNum, "activeCounter") == 1 && getNeighborProperty(portNum, "label").equals("N")) {
                        setNeighborProperty(portNum, "label", "F");
                        setLocalProperty("activeCounter", (int)getLocalProperty("activeCounter") - 1);
                    }
                }
            }
        }
        else if (getLocalProperty("label").equals("E") || getLocalProperty("label").equals("F"))
            localTermination();

        putProperty("Neighbors", getLocalProperty("activeCounter"), SimulationConstants.PropertyStatus.DISPLAYED);
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
