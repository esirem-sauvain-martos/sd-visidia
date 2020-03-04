import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.Arrays;

public class Leadership extends LC0_Algorithm {

    private int parentDoor = -1;

    @Override
    public Object clone() {
        return new Leadership();
    }

    @Override
    public String getDescription() {
        return "Leadership";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("activeCounter", getArity());
        putProperty("Neighbors", getLocalProperty("activeCounter"), SimulationConstants.PropertyStatus.DISPLAYED);
    }

    @Override
    protected void onStarCenter() {
        if(getLocalProperty("label").equals("N")
                && (int)getLocalProperty("activeCounter") == 1
                && getNeighborProperty("label").equals("N")
                && (int)getNeighborProperty("activeCounter") == 1) {

            setNeighborProperty("label", "F");
            setLocalProperty("label", "E");
            setNeighborProperty("activeCounter", 0);

        } else  if(getLocalProperty("label").equals("N")
                && (int)getLocalProperty("activeCounter") == 1
                && getNeighborProperty("label").equals("N")
                && (int)getNeighborProperty("activeCounter") >= 1) {

            setLocalProperty("label", "F");
            setNeighborProperty("activeCounter", (int)getNeighborProperty("activeCounter") - 1);
        }
        else if(getLocalProperty("label").equals("E"))
            globalTermination();

        if((getLocalProperty("label").equals("F") || getLocalProperty("label").equals("E"))  && getNeighborProperty("label").equals("F") && (int)getLocalProperty("activeCounter") == 1) {
            setLocalProperty("activeCounter", 0);
        }

        putProperty("Neighbors", getLocalProperty("activeCounter"), SimulationConstants.PropertyStatus.DISPLAYED);
    }
}
