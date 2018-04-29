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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import data.resources.DataFileException;
import engine.entity.GameEntity;

public class PushData {
	
	public PushData() 
	{
		FirebaseAuthentication fb = new FirebaseAuthentication();
	}
	
	public void export(String databaseReference, Map<String,Object> data)
	{
		FirebaseDatabase db2 = FirebaseDatabase.getInstance();
		DatabaseReference ref = db2.getReference("/" + databaseReference);
		System.out.println(ref);

		CountDownLatch countDownLatch = new CountDownLatch(1);
		ref.setValue(data, new DatabaseReference.CompletionListener() {
			@Override
			public void onComplete(DatabaseError de, DatabaseReference dr) {
				System.out.println("Record saved!");
				countDownLatch.countDown();
			}
		});
		
		try 
		{
			countDownLatch.await();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
