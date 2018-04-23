package data.firebase;

import java.io.FileInputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;

public class FirebaseAuthentication {
	// Fetch the service account key JSON file contents
	FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");

	// Initialize the app with a service account, granting admin privileges
	FirebaseOptions options = new FirebaseOptions.Builder()
	    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	    .setDatabaseUrl("https://voogasalad-supersaladsquad.firebaseio.com")
	    .build();
	
	FirebaseApp.initializeApp(options);

	// As an admin, the app has access to read and write all data, regardless of Security Rules
	DatabaseReference ref = FirebaseDatabase.getInstance().getReference("restricted_access/secret_document");
	
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
