package data.dummyObjects;

import java.util.ArrayList;
import java.util.List;

public class GameObject {  
	int id;
    int health;
    boolean attacks;

    public GameObject()	{
    	this(1, true);
    }

    public GameObject(int health, boolean attacks)	{
    	this.id = (int)(Math.random() * 100);
    	this.health = health;
    	this.attacks = attacks;
    }
}
