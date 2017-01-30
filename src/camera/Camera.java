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
		
		//Point2D twr = coordinate(1575.896D, 371358.25D);
		//Point2D Pos = para.cal.dirdisToXY(8287.130,3594557.30);
		camera.setTranslateX(para.centerX);
		camera.setTranslateZ(para.centerY);
		camera.setTranslateY(-para.centerZ);
		camera.setNearClip(1.0D);
		camera.setFarClip(50000.0D);
		camera.setFieldOfView(45.0D);
		Rotate cameraRotateX = new Rotate(-90.0D, Rotate.X_AXIS);
		Rotate cameraRotateY = new Rotate(0.0D, Rotate.Y_AXIS);
		camera.getTransforms().addAll(cameraRotateX, cameraRotateY);
		para.scene.setCamera(camera);
	}
}
