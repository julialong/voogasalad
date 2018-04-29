package engine.powerup;

import engine.entity.Player;

/**
 * Abstract class for timed power ups
 * @author Marcus Oertle and Robert Gitau
 *
 */
public abstract class TimedPowerUp implements PowerUp{
	protected int counter;
	protected double duration;
    protected Player player;
    protected boolean active = false;
    
    public void activate(){
    	if(!active) {
    		counter = 0;
    		effect();
        	active = true;
    	}
    }
    
    public void deactivate(){
    	if(active) {
    		reverseEffect();
    		active = false;
    	}
    }
    
	public void setDuration(double time) {
		duration = 60 * time;		
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
    
    public boolean update(){
        if(active) {
            counter++;
        }
        if(counter > duration){
        	counter = 0;
            deactivate();
            return true;
        }
		return false;
    }
    
    /**
     * Effect when the power up is activated
     */
    public abstract void effect();
    
    /**
     * Reverse effect to undo the power up    
     */
    public abstract void reverseEffect();
}
