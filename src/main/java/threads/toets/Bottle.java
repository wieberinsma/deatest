package threads.toets;

public class Bottle
{
    private static final int INITIAL_CAPACITY_IN_ML = 50000;

    private int remainingCapacityInMl = INITIAL_CAPACITY_IN_ML;

    public int getRemainingVolume()
    {
        return this.remainingCapacityInMl;
    }

    public int tap(int volumeInMl)
    {
        if (volumeInMl > this.remainingCapacityInMl)
        {
            volumeInMl = this.remainingCapacityInMl;
        }

        this.remainingCapacityInMl -= volumeInMl;
        return volumeInMl;
    }
}
