package control;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ops.Parameter;

public class Control {
	public Parameter para;
	public Road road;
	public Open open;
	public OASBIS bis;
	public SID sid;
	public OAS oas;
	public APVOAS apvoas;
	public Stage stage;
	public Scene scene;
	public Pane pane;
	public Control(Parameter para){
		this.para = para;		
		
		pane = new Pane();
		road = new Road(para,pane);
		road.setRoad();
		
		open = new Open(para,pane);
		open.setOpen();
		
		bis = new OASBIS(para,pane);
		bis.setOASBIS();
		sid = new SID(para,pane);
		oas = new OAS(para,pane);
		apvoas = new APVOAS(para,pane);
		
		scene = new Scene(pane,200,80);
		stage = new Stage();
		stage.setTitle("control");
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);		
		stage.show();
		stage.setX(0);
		stage.setY(0);
				
	}
}
