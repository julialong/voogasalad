package data.objtodata;

import java.util.ArrayList;
import java.util.List;

public class ExampleObject {  
	int id;
    int health;
    boolean attacks;

    public ExampleObject()	{
    	this(2, true);
    }

    public ExampleObject(int health, boolean attacks)	{
    	this.id = (int)(Math.random() * 100);
    	this.health = health;
    	this.attacks = attacks;
    }
}