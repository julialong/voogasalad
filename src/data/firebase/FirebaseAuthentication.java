package data.firebase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		FirebaseDatabase db2 = FirebaseDatabase.getInstance();
		DatabaseReference ref = db2.getReference("/");
		System.out.println(ref);
		DatabaseReference testing = ref.child("test");
		System.out.println(testing);
		Map<String, String> ex = new HashMap<>();
		
		ex.put("hello", "testing");
		System.out.println(ex);
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
        testing.setValue(ex, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
                System.out.println("Record saved!");
                // decrement countDownLatch value and application will be continues its execution.
                countDownLatch.countDown();
            }
        });
        try {
            //wait for firebase to saves record.
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
//		testing.setValueAsync(ex);
		
//		testing.setValue(ex, new DatabaseReference.CompletionListener() {
//		    @Override
//		    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//		    	System.out.println("here");
//		        if (databaseError != null) {
//		            System.out.println("Data could not be saved " + databaseError.getMessage());
//		        } else {
//		            System.out.println("Data saved successfully.");
//		        }
//		    }
//		});
}
