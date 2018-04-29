package data.firebase;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * This class sets up a firebase connection to a service account for pushing data
 * at a later time. Created with help from the firebase admin sdk api: 
 * https://firebase.google.com/docs/database/admin/start#admin-sdk-setup
 * 
 * @author Belanie Nagiel
 *
 */
public class FirebaseAuthentication {
	
	public FirebaseAuthentication() {
		setUp();
	}
	
	/**
	 * Connects the the service account given the key and the database URL.
	 * 
	 */
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
