package data.firebase;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * This is a general class that allows you to export a Map containing key value pairs 
 * to Firebase. Unfortunately, this was not compatible with the way that we serialize objects
 * using gson serialization so we did not have the time to integrate with firebase.
 * 
 * Code was taken from https://www.javaquery.com/2016/09/how-to-save-data-in-firebase.html in order
 * to get this class to function.
 * 
 * @author Belanie Nagiel
 *
 */
public class PushData {
	
	/**
	 * Class constructor
	 * 
	 * Sets up the firebase connection
	 */
	public PushData() 
	{
		FirebaseAuthentication fb = new FirebaseAuthentication();
	}
	
	/**
	 * Given the name for a database reference and then a series of key, value pairs,
	 * the information from the map will be pushed to firebase under the tag for the 
	 * reference name
	 * 
	 * @param databaseReference
	 * @param data
	 */
	public void export(String databaseReference, Map<String,Object> data)
	{
		FirebaseDatabase db2 = FirebaseDatabase.getInstance();
		DatabaseReference ref = db2.getReference("/" + databaseReference);

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
