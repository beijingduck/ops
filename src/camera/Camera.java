package camera;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class Camera {
	public Group base;
	public Parameter para;
	public PerspectiveCamera camera;
	public Rotate cameraRotateX,cameraRotateY;
	public Camera(Parameter para){
		this.para = para;
		setCamera();
	}
	public void setCamera(){
		Box box = new Box();
		box.setHeight(100);
		box.setWidth(100);
		box.setLayoutX(0);
		box.setLayoutY(0);
		
		camera = new PerspectiveCamera(true);
		camera.setTranslateX(0);
		camera.setTranslateZ(0);
		camera.setTranslateY(0);
		camera.setNearClip(1.0D);
		camera.setFarClip(50000.0D);
		camera.setFieldOfView(45.0D);
		cameraRotateX = new Rotate(-90.0D, Rotate.X_AXIS);
		cameraRotateY = new Rotate(0.0D, Rotate.Y_AXIS);
		
		camera.getTransforms().addAll(cameraRotateX,cameraRotateY);
		
		base = new Group();
		base.setTranslateX(para.centerX);
		base.setTranslateZ(para.centerY);
		base.setTranslateY((-1.0)*para.centerZ);
		base.getChildren().addAll(camera);
		base.setRotationAxis(Rotate.Y_AXIS);
		para.root.getChildren().addAll(base);
		
		para.scene.setCamera(camera);
	} 
}
