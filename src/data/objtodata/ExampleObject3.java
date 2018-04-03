package data.objtodata;

import java.util.ArrayList;
import java.util.List;

public class ExampleObject3 {  
	int id;
    int health;
    boolean attacks;

    public ExampleObject3()	{
    	this(3, true);
    }

    public ExampleObject3(int health, boolean attacks)	{
    	this.id = (int)(Math.random() * 100);
    	this.health = health;
    	this.attacks = attacks;
    }
}