package data_serialization;

import static org.junit.Assert.*;
import com.google.gson.*;
import org.junit.Test;

public class TestingGSON {

	@Test
	public void TestDeserialization() {
		SampleObject x = new SampleObject(69);
		Gson g = new Gson();
		String save = g.toJson(x);
		Deserializer deserializer = new Deserializer();
		Object y = deserializer.deserialize(save, SampleObject.class);
		assertEquals("Test Type", x.getClass(), y.getClass());
		SampleObject z = (SampleObject) y;
		assertEquals("Test ID", x.add(5), z.add(5));
	}


	
//	public static void main(String[] args) {
//		Gson gson = new Gson();
////		gson.toJson("Hello", System.out);
////		gson.toJson(123, System.out);
//
//		SampleObject so = new SampleObject(10);
//		String sample = gson.toJson(so);
//
////		System.out.println(sample);
//
////		try {
////			Object test = gson.fromJson(sample, Class.forName("data_serialization.SampleObject"));
////			System.out.println(test);
////		} catch (JsonSyntaxException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (ClassNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
////
////		System.out.println(test.add(17));
//
////		GameFileWriter f = new GameFileWriter("sampleGame");
////		f.add(1, " \"1\": " + sample + ",\n");
////		f.remove(1, "\"1\"");
//
//		GameFileReader f = new GameFileReader();
////		f.loadGame("sampleGame");
//
//
//	}

}
