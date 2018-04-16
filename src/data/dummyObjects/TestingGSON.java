package data.dummyObjects;


import data.gamefiles.GameFileReader;

/**
 * Class used for testing serialization.
 * 
 * @author Belanie Nagiel
 *
 */
public class TestingGSON {
	
	

//	@Test
//	public void TestDeserialization() {
//		SampleObject x = new SampleObject(69);
//		Gson g = new Gson();
//		String save = g.toJson(x);
//		Deserializer deserializer = new Deserializer();
//		Object y = deserializer.deserialize(save, SampleObject.class);
//		assertEquals("Test Type", x.getClass(), y.getClass());
//		SampleObject z = (SampleObject) y;
//		assertEquals("Test ID", x.add(5), z.add(5));
//	}


	
	public static void main(String[] args) {
//		Gson gson = new Gson();
////		gson.toJson("Hello", System.out);
////		gson.toJson(123, System.out);
//
//		SampleObject so = new SampleObject(10);
//		String sample = gson.toJson(so);
//
////		System.out.println(sample);
//
////		try {
////			Object test = gson.fromJson(sample, Class.forName("data_serialization.SampleObject"));
////			System.out.println(test);
////		} catch (JsonSyntaxException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (ClassNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
////
////		System.out.println(test.add(17));
//
////		GameFileWriter f = new GameFileWriter("sampleGame");
////		f.add(1, " \"1\": " + sample + ",\n");
////		f.remove(1, "\"1\"");
//
//		GameFileReader fr = new GameFileReader();
//		Map<String, List<Object>> x = fr.loadCompleteGame("TestGame");
//		for(String xx: x.keySet())
//		{
//			System.out.println(xx);
//			System.out.println(x.get(xx));
//		}
////		System.out.print(x.keySet());
//		fr.getGameNames();
////		f.loadGame("sampleGame");
		
//		Map<Level, List<GameEntity>> objsOrganized = new HashMap<>();
//		List<GameEntity> objsToWrite = new ArrayList<>();
//
//		Player p = new Player();
//		objsToWrite.add(new Block());
//		objsToWrite.add(new Foes());
//		objsToWrite.add(new Flag());
//		objsToWrite.add(new Player());
//		objsToWrite.add(new Block());
//		objsToWrite.add(new Flag());
//		objsToWrite.add(new Foes());
//
//		Level one = new BasicLevel();
//		objsOrganized.put(one, new ArrayList<GameEntity>());
//		for (GameEntity obj:objsToWrite)	{
//			objsOrganized.get(one).add(obj);
//		}
//		
//		GameFileWriter myWriter = new GameFileWriter("NewTester");
//		myWriter.update(objsOrganized);
//		
		GameFileReader fr = new GameFileReader();
//		Map<String, List<Object>> x = fr.loadCompleteGame("NewTester");
//		for(String xx: x.keySet())
//		{
//			System.out.println(xx);
//			System.out.println(x.get(xx));
//		}
	
//		GridPane sc = new GridPane();
//		Gson gs = new Gson();
//		String x = gs.toJson(sc);
//		System.out.println(x);
		
//		System.out.println(fr.loadCompleteGame("TestGame"));
//		fr.loadLevel("TestGame", "Default");
		System.out.println(fr.getGameNames());
	}
}
