package object;

import javafx.scene.control.ListView;
import ops.Parameter;

public class Object {
	public Parameter para;
	public Rwy rwy;
	public Obstacle obs;
	public Surface sfc;
	public Terrain trn;
	public SID sid;
	public APVOAS apvoas;
	public ListView<SID> sids = new ListView<SID>();
	public Object(Parameter para){
		this.para = para;
		rwy = new Rwy(para);
		obs = new Obstacle(para);
		sfc = new Surface(para);
		trn = new Terrain(para);
		sid = new SID(para);
		apvoas = new APVOAS(para);
	}
}
