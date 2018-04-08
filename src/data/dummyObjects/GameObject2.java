package data.dummyObjects;

import java.util.ArrayList;
import java.util.List;

public class GameObject2 extends GameObject {
    public GameObject2()	{
    	this(2, true);
    }

    public GameObject2(int health, boolean attacks)	{
    	this.id = (int)(Math.random() * 100);
    	this.health = health;
    	this.attacks = attacks;
    }
}
