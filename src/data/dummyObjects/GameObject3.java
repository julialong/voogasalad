package data.dummyObjects;

import java.util.ArrayList;
import java.util.List;

public class GameObject3 extends GameObject {  
    public GameObject3()	{
    	this(3, true);
    }

    public GameObject3(int health, boolean attacks)	{
    	this.id = (int)(Math.random() * 100);
    	this.health = health;
    	this.attacks = attacks;
    }
}
