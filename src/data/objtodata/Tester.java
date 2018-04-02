package data.objtodata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tester	{
	public static void main(String[] args)	{
		Gson gson = new Gson(); // new GsonBuilder().registerTypeAdapter(ExampleObject.class, new ExampleObjectSerializer()).create();
        // ExampleObjectSerializer aSer = new ExampleObjectSerializer();
        ExampleObject obj = new ExampleObject(
				"Norman",
				"norman@futurestud.io",
				26,
				true
		);

        System.out.println(gson.toJson(obj));
	}
}