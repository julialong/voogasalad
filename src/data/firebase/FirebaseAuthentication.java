package data.firebase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 * https://firebase.google.com/docs/database/admin/start#admin-sdk-setup
 * 
 * @author belanie.nagiel
 *
 */
public class FirebaseAuthentication {
	
	private FirebaseDatabase db;
	
	public FirebaseAuthentication() {
		db = setUp();
	}
	
	private FirebaseDatabase setUp()
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
					
					return db;
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}

				// As an admin, the app has access to read and write all data, regardless of Security Rules
				return null;
	}
	
	public void testAdd()
	{
		DatabaseReference ref = db.getReference("server/saving-data/fireblog");
		
		DatabaseReference testing = ref.child("test");
		Map<String, String> ex = new HashMap<>();
		
		ex.put("hello", "testing");
		
		testing.setValueAsync(ex);
	}
}
