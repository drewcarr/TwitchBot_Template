package navseu.processor;

public class Storage {
	public int na = 0;
	public int eu = 0;
	public int kappa =0;
	public int pogchamp = 0;
	public void addNA()
	{
		na ++;
	}
	public int getNA()
	{
		return na;
	}
	public void addEU()
	{
		eu ++;
	}
	public int getEU()
	{
		return eu;
	}
	public void addKappa()
	{
		System.out.println("Added Kappa");
		kappa ++;
	}
	public int getKappa()
	{
		return kappa;
	}
	public void addPogchamp()
	{
		pogchamp ++;
	}
	public int getPogchamp()
	{
		return pogchamp;
	}
}
