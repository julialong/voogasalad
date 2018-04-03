package data.objtodata;

import java.util.ArrayList;
import java.util.List;

public class ExampleObject2 {  
	int id;
    int health;
    boolean attacks;

    public ExampleObject2()	{
    	this(2, true);
    }

    public ExampleObject2(int health, boolean attacks)	{
    	this.id = (int)(Math.random() * 100);
    	this.health = health;
    	this.attacks = attacks;
    }
}