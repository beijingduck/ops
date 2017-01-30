package ops;

import camera.Camera;
import control.Control;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import light.Light;
import mouse.Mouse;
import object.Rwy;

public class Parameter {
	public String airport;
	public double variation,scale,centerX,centerY,centerZ,centerLat,centerLng;
	public int dimensionX,dimensionZ;
	public Group root;
	public Scene scene;
	public Light light;
	public Camera camera;
	public Mouse mouse;
	public Calculation cal;
	public Control cntl;
	public Rwy rwy;
	public Parameter(Stage stage){
		scale = 1;
		variation = 7.3;
		centerLat = 35.262133;
		centerLng = 136.174875;
		centerX = -3493;			//cameraの初期位置
		centerY = 8412;
		centerZ = 1000;
		dimensionX = 1125;
		dimensionZ = 750;
		cal = new Calculation(this);
		
		//double[] xy = cal.dirdisToXY(cal.coordToDirdis(351543.68, 1361029.55));
 		//System.out.println(xy[0]+" "+xy[1]);
		
		root = new Group();
		scene = new Scene(this.root, 1300.0D, 800.0D, true, SceneAntialiasing.BALANCED);
		
		light = new Light(this);
		camera = new Camera(this);
		mouse = new Mouse(this);
		cntl = new Control(this);
		rwy = new Rwy(this);
		
		stage.setTitle("3D designe");
		stage.setScene(this.scene);
		stage.show();
	}
	
}
