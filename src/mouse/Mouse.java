package mouse;

import javafx.scene.Camera;
import javafx.scene.input.KeyCode;
import ops.Parameter;

public class Mouse {
	public Parameter para;
	public Camera camera;
	public Mouse(Parameter para){
		this.para = para;
		this.camera = para.camera.camera;
		setMouse();
	}
	public void setMouse(){
		para.scene.setOnScroll((sc) -> {
			camera.setTranslateY(camera.getTranslateY() - sc.getDeltaY());
		});
		para.scene.setOnKeyPressed((e) -> {
			if(e.getCode().equals(KeyCode.RIGHT)){
				camera.setTranslateX(camera.getTranslateX() +10);
			}else if(e.getCode().equals(KeyCode.LEFT)){
				camera.setTranslateX(camera.getTranslateX() -10);
			}else if(e.getCode().equals(KeyCode.UP)){
				camera.setTranslateZ(camera.getTranslateZ() +10);
			}else if(e.getCode().equals(KeyCode.DOWN)){
				camera.setTranslateZ(camera.getTranslateZ() -10);
			}
		});
	}
}
