package data.objtodata;

public class ExampleObject	{
    int id;
    public Class myClass = this.getClass();
    private boolean canRemove = false;

    public ExampleObject(int id)	{
        this.id = id;
    }
}