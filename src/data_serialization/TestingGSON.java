package data_serialization;

import com.google.gson.*;

public class TestingGSON {
	
	public static void main(String[] args) {
		Gson gson = new Gson();
//		gson.toJson("Hello", System.out);
//		gson.toJson(123, System.out);
		
		SampleObject so = new SampleObject(10);
		String sample = gson.toJson(so);
		
//		System.out.println(sample);
		
//		try {
//			Object test = gson.fromJson(sample, Class.forName("data_serialization.SampleObject"));
//			System.out.println(test);
//		} catch (JsonSyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		
//		System.out.println(test.add(17));	
		
//		GameFileWriter f = new GameFileWriter("sampleGame");
//		f.add(1, " \"1\": " + sample + ",\n");
//		f.remove(1, "\"1\"");
		
		GameFileReader f = new GameFileReader();
//		f.loadGame("sampleGame");
		
		
	}

}
