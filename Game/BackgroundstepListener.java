package Game;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;

public class BackgroundstepListener implements StepListener {
    public boolean running = false;
    public Player player;
    public BackgroundstepListener(World w, Player p) {
        super();
        player = p;
        w.addStepListener(this);
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
