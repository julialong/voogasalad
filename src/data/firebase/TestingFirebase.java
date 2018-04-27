package data.firebase;

import java.io.File;

public class TestingFirebase {

	public static void main(String[] args)
	{
//		FirebaseAuthentication fb = new FirebaseAuthentication();
//		fb.testAdd();
		
		ExportLevel export = new ExportLevel(new File("./data/levelData/Default.json"));
		export.export();
	}
}
