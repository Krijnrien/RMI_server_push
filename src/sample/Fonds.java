package sample;

public class Fonds implements IFonds {
	private String name;
	private int x;

	Fonds(){}

	Fonds(String name, int x) {
		this.name = name;
		this.x = x;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getX() {
		return x;
	}
}