package data.objtodata;

public class ExampleObject {  
    String type;
    int health;
    boolean attacks;

    public ExampleObject()	{
    	this("enemy", 2, true);
    }

    public ExampleObject(String type, int health, boolean attacks)	{
    	this.type = type;
    	this.health = health;
    	this.attacks = attacks;
    }
}