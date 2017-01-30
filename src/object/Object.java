package object;

import ops.Parameter;

public class Object {
	public Parameter para;
	public Rwy rwy;
	public Obstacle obs;
	public Object(Parameter para){
		this.para = para;
		rwy = new Rwy(para);
		obs = new Obstacle(para);
	}	
}
