import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Serializer	{
	House house = new House();

	private static Field[] fields;

	public Serializer()	{
		fields = House.class.getDeclaredFields();

		for (Field field:fields)	{
			System.out.println(House.class.getField(field.getName()));
		}
	}

	public static void main(String[] args)	{
	}

	public class House	{
		int price = 400000;
		String city = "Austin";
	}
}