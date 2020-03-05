package lc1;

import visidia.simulation.process.algorithm.LC1_Algorithm;

public class OpenStarElection extends LC1_Algorithm {
    @Override
    public Object clone() {
        return new OpenStarElection();
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
        int nCount = getNCount();

        if(getLocalProperty("label").equals("N") && nCount == 1)
            setLocalProperty("label", "F");
        else if (getLocalProperty("label").equals("N") && nCount == 0)
            setLocalProperty("label", "E");
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
