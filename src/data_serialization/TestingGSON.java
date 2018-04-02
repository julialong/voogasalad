package data_serialization;

import com.google.gson.*;

public class TestingGSON {
	
	public static void main(String[] args) {
		Gson gson = new Gson();
//		gson.toJson("Hello", System.out);
//		gson.toJson(123, System.out);
		
		SampleObject so = new SampleObject(10);
		String sample = gson.toJson(so);
		
		System.out.println(sample);
		
//		SampleObject test = gson.fromJson(sample, SampleObject.class);
//		
//		System.out.println(test.add(17));	
		
		GameFileWriter f = new GameFileWriter("sampleGame");
//		f.newGame("sampleGame");
		
		f.update("sampleGame", 1, sample);
	}

}
