package data.objtodata;

import java.util.ArrayList;
import java.util.List;

public class ExampleObject {  
	int id;
    String type;
    int health;
    boolean attacks;

    public ExampleObject()	{
    	this("enemy", 2, true);
    }

    public ExampleObject(String type, int health, boolean attacks)	{
    	this.id = (int)(Math.random() * 100);
    	this.type = type;
    	this.health = health;
    	this.attacks = attacks;
    }
}