package threads.toets;

public class LemonadeDrinker
{
    private static final int VOLUME_IN_ML = 200;

    private final String name;
    private final Bottle bottle;

    private int consumedLemonade = 0;

    public LemonadeDrinker(String name, Bottle bottle)
    {
        this.name = name;
        this.bottle = bottle;
    }

    public void startDrinking()
    {
        while (bottle.getRemainingVolume() > 0)
        {
            consumedLemonade += bottle.tap(VOLUME_IN_ML);
        }
        System.out.println(this.name + " drank " + consumedLemonade + "ml.");
    }
}
