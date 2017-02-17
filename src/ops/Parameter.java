package ops;

import camera.Camera;
import control.Control;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import light.Light;
import mouse.Mouse;
import object.Object;

public class Parameter {
	public String airport;
	public double variation,scale,centerX,centerY,centerZ,centerLat,centerLng;
	public int dimensionX,dimensionZ;
	public Pane pane;
	public Group root;
	public Scene scene;
	public Light light;
	public Camera camera;
	public Mouse mouse;
	public Calculation cal;
	public Control cntl;
	public Object obj;
	public Parameter(Stage stage){
		scale = 1;
		variation = 7.3;
		centerLat = 35.262133;
		centerLng = 136.174875;
		
		dimensionX = 1125;
		dimensionZ = 750;
		
		
		cal = new Calculation(this);		
		//double[] xy = cal.dirdisToXY(cal.coordToDirdis(centerLat,centerLng));
		centerX = 14787;			//cameraの初期位置
		centerY = -80852;
		centerZ = 10000;
		root = new Group();
		scene = new Scene(this.root, 1300.0D, 800.0D, true, SceneAntialiasing.BALANCED);
		
		light = new Light(this);
		camera = new Camera(this);
		mouse = new Mouse(this);
		cntl = new Control(this);
		obj = new Object(this);		
		stage.setTitle("3D designe");
		stage.setScene(this.scene);
		stage.show();		
	}
	
}
