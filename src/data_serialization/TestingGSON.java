package data_serialization;

import com.google.gson.*;

public class TestingGSON {
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
//		gson.toJson("Hello", System.out);
//		gson.toJson(123, System.out);
		
		SampleObject so = new SampleObject(25);
		String sample = gson.toJson(so);
		
		System.out.println(sample);
		
		SampleObject test = gson.fromJson(sample, SampleObject.class);
		
		System.out.println(test.add(17));
		
		
	}

}
