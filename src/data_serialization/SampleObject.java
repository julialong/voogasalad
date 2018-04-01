package data_serialization;


public class SampleObject{
	
	private int ID;
	private int added;
	private Sample s = new Sample();
		
	public SampleObject(int id)
	{
		ID = id;
	}
	
	public int add(int b)
	{
		this.added = ID + b;
		System.out.println(s.getName());
		return this.added;
	}
}
