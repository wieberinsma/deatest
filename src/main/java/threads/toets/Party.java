package threads.toets;

import java.util.ArrayList;

public class Party
{
    public static void main(String[] args)
    {
        var bottle = new Bottle();
        var drinkers = new ArrayList<LemonadeDrinker>();

        drinkers.add(new LemonadeDrinker("Marlon", bottle));
        drinkers.add(new LemonadeDrinker("Ken", bottle));
        drinkers.add(new LemonadeDrinker("Dennis", bottle));
        drinkers.add(new LemonadeDrinker("Hugo", bottle));

        drinkers.forEach(drinker -> new Thread(drinker::startDrinking).start());

        // Kan ook, maar minder mooi en vergeet de @Override niet (Barend):
//        drinkers.forEach(drinker -> new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                drinker.startDrinking();
//            }
//        }).start());
    }
}
