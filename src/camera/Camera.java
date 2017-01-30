package camera;


import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class Camera {
	public Parameter para;
	public PerspectiveCamera camera;
	public Camera(Parameter para){
		this.para = para;
		setCamera();
	}
	public void setCamera(){
		camera = new PerspectiveCamera(true);
		camera.setTranslateX(para.centerX);
		camera.setTranslateZ(para.centerY);
		camera.setTranslateY((-1.0)*para.centerZ);
		camera.setNearClip(1.0D);
		camera.setFarClip(50000.0D);
		camera.setFieldOfView(45.0D);
		Rotate cameraRotateX = new Rotate(-90.0D, Rotate.X_AXIS);
		Rotate cameraRotateY = new Rotate(0.0D, Rotate.Y_AXIS);
		camera.getTransforms().addAll(cameraRotateX, cameraRotateY);
		para.scene.setCamera(camera);
	}
}
