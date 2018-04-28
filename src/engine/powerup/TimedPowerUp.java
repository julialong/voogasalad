package engine.powerup;

import engine.entity.Player;

public abstract class TimedPowerUp implements PowerUp{
	protected int counter;
	protected double duration;
    protected Player player;
    protected boolean active = false;
    
    public void activate(){
    	if(!active) {
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
        counter++;
        if(counter > duration){
            deactivate();
            return true;
        }
		return false;
    }
    
    public abstract void effect();
    
    public abstract void reverseEffect();
}
