package engine.behavior;

import engine.controls.Action;
import engine.controls.MoveDown;
import engine.controls.MoveLeft;
import engine.controls.MoveRight;
import engine.controls.MoveUp;
import engine.entity.GameEntity;
import engine.entity.Player;
import engine.movement.Flying;

/**
 * Behavior implementation that causes entity to chase the player object
 * @author Robert Gitau and Marcus Oertle
 *
 */
public class ChasePlayer implements Behavior{

	private Action actionLeft = new MoveLeft();
    private Action actionRight = new MoveRight();
    private Action actionUp= new MoveUp();
    private Action actionDown = new MoveDown();
    private Player player;
    
    public ChasePlayer(Player player){
        this.player = player;
    }
    
    @Override
	public void behave(GameEntity entity) {
        double xPos = entity.getPosition()[0];
        double xPosPlayer = player.getPosition()[0];
        if(xPosPlayer < xPos){
            actionLeft.execute(entity);
        }
        else{
            actionRight.execute(entity);
        }
        
        if(entity.getMovementType() instanceof Flying){
            double yPos = entity.getPosition()[1];
            double yPosPlayer = player.getPosition()[1];
            if(yPosPlayer < yPos){
                actionDown.execute(entity);
            }
            else{
                actionUp.execute(entity);
            }
        }
	}
	
	
}
