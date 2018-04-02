package data.objtodata;

public class Tester	{
	public static void main(String[] args)	{
        Serializer aSer = new Serializer();
        ExampleObject obj = new ExampleObject(12);

        aSer.serialize();
	}
}