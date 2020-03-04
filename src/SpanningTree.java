import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class SpanningTree extends LC0_Algorithm {

    @Override
    public String getDescription() {
        return "Spanning tree rule with LCO \nRule: A---N => A-*-A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
        if(getLocalProperty("label").equals("A") && getNeighborProperty("label").equals("N")) {
            setNeighborProperty("label", "A");
            setDoorState(new MarkedState(true), neighborDoor);
        }
    }

    @Override
    public Object clone() {
        return new SpanningTree();
    }
}
