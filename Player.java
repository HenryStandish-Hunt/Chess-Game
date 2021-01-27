
public class Player {
	
	private String colour;
	
	private String name;
	
	Player(String colour){
		this.setColour(colour);
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
