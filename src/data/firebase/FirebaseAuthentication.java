package data.firebase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


/**
 * https://firebase.google.com/docs/database/admin/start#admin-sdk-setup
 * 
 * @author belanie.nagiel
 *
 */
public class FirebaseAuthentication {
	
	public FirebaseAuthentication() {
		setUp();
	}
	
	private void setUp()
	{
		// Fetch the service account key JSON file content
				try
				{
					FileInputStream serviceAccount = new FileInputStream("./data/firebase/serviceAccountKey.json");				
					
					FirebaseOptions options = new FirebaseOptions.Builder()
						    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
						    .setDatabaseUrl("https://voogasalad-supersaladsquad.firebaseio.com")
						    .build();
					
					// Initialize the app with a service account, granting admin privileges
					FirebaseApp.initializeApp(options);
					
					// As an admin, the app has access to read and write all data, regardless of Security Rules
					FirebaseDatabase db = FirebaseDatabase.getInstance();
					DatabaseReference ref = db.getReference("restricted_access/secret_document");
					
					ref.addListenerForSingleValueEvent(new ValueEventListener() {
						
					  @Override
					  public void onDataChange(DataSnapshot dataSnapshot) {
					    Object document = dataSnapshot.getValue();
					    System.out.println(document);
					  }
				
					  @Override
					  public void onCancelled(DatabaseError error) {
					  }
					});	
					
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}

	}
}
