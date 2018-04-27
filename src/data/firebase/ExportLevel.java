package data.firebase;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.resources.DataFileException;
import engine.entity.GameEntity;

public class ExportLevel {
	
	private static final String RESOURCE_FILE = "data.resources/gameObjects";
	private static final String NAME = "name";
	private static final String ID = "id";
	private Map<String,Class<?>> objectTypes;
	private File level;
	
	public ExportLevel(File level) 
	{
		this.level = level;
		FirebaseAuthentication fb = new FirebaseAuthentication();
		objectTypes = new HashMap<>();
		createObjectToClassMap();
	}
	
	public void export()
	{
		try
		{
			JsonParser jsonParser = new JsonParser();
			JsonObject jobject = jsonParser.parse(new FileReader(level)).getAsJsonObject();
			
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			
			writeLevelData(database,jobject);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private void writeLevelData(FirebaseDatabase database, JsonObject jobject) {
		String levelName = jobject.get(NAME).getAsString();
		
		DatabaseReference levelRef = database.getReference("/" + levelName);
		
		Map<String,Object> levelItems = new HashMap<>();
		levelItems.put(NAME, levelName);
		levelItems.put(ID, jobject.get(ID).getAsInt());
		
		
		for(String objectType: objectTypes.keySet())
		{
			if(jobject.has(objectType))
			{
//				levelItems.put(objectType, jobject.get(objectType));
				List<String> newObjectsOfType = new ArrayList<>();
				JsonArray jarray = jobject.getAsJsonArray(objectType);
				for(int i = 0; i < jarray.size(); i++)
				{
					newObjectsOfType.add(jarray.get(i).getAsString());
				}
				System.out.println(newObjectsOfType);
			}
		}
		
		
//		System.out.println(levelItems);
		
//		CountDownLatch countDownLatch = new CountDownLatch(1);
//        levelRef.setValue(levelItems, new DatabaseReference.CompletionListener() {
//
//            @Override
//            public void onComplete(DatabaseError de, DatabaseReference dr) {
//                System.out.println("Record saved!");
//                // decrement countDownLatch value and application will be continues its execution.
//                countDownLatch.countDown();
//            }
//        });
//        try {
//            //wait for firebase to saves record.
//            countDownLatch.await();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

		
	}
	
	private void createObjectToClassMap() 
	{
		ResourceBundle gameObjects = ResourceBundle.getBundle(RESOURCE_FILE);
		Enumeration<String> objectNames = gameObjects.getKeys();
		while(objectNames.hasMoreElements())
		{
			String objectName = objectNames.nextElement();
			try 
			{
				Class<?> objectClass = Class.forName(gameObjects.getString(objectName));
				objectTypes.put(objectName, objectClass);
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
