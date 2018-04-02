package data.objtodata;

public class Tester	{
	private static Serializer ser = new Serializer();

	public static void main(String[] args)	{
		ser.serialize(new ExampleObject());
	}
}