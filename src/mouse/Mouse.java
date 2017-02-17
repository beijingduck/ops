package mouse;

import javafx.scene.Camera;
import javafx.scene.input.KeyCode;
import ops.Parameter;

public class Mouse {
	public Parameter para;
	public Camera camera;
	public double d;
	public Mouse(Parameter para){
		this.para = para;
		this.camera = para.camera.camera;
		d = 100;
		setMouse();
	}
	public void setMouse(){
		para.scene.setOnScroll((sc) -> {
			camera.setTranslateY(camera.getTranslateY() - sc.getDeltaY()*10);			
		});
		para.scene.setOnKeyPressed((e) -> {
			if(e.isShiftDown()){
				if(e.getCode().equals(KeyCode.RIGHT)){
					para.camera.base.setRotate(para.camera.base.getRotate()+5);
				}else if(e.getCode().equals(KeyCode.LEFT)){
					para.camera.base.setRotate(para.camera.base.getRotate()-5);
				}else if(e.getCode().equals(KeyCode.UP)){					
					para.camera.cameraRotateX.setAngle(para.camera.cameraRotateX.getAngle()+5);
				}else if(e.getCode().equals(KeyCode.DOWN)){
					para.camera.cameraRotateX.setAngle(para.camera.cameraRotateX.getAngle()-5);
				}
			}else if(e.getCode().equals(KeyCode.RIGHT)){
				para.camera.base.setTranslateX(para.camera.base.getTranslateX()+d*Math.sin(Math.toRadians(para.camera.base.getRotate()+90)));
				para.camera.base.setTranslateZ(para.camera.base.getTranslateZ()+d*Math.cos(Math.toRadians(para.camera.base.getRotate()+90)));
			}else if(e.getCode().equals(KeyCode.LEFT)){
				para.camera.base.setTranslateX(para.camera.base.getTranslateX()+d*Math.sin(Math.toRadians(para.camera.base.getRotate()-90)));
				para.camera.base.setTranslateZ(para.camera.base.getTranslateZ()+d*Math.cos(Math.toRadians(para.camera.base.getRotate()-90)));
			}else if(e.getCode().equals(KeyCode.UP)){
				para.camera.base.setTranslateX(para.camera.base.getTranslateX()+d*Math.sin(Math.toRadians(para.camera.base.getRotate())));
				para.camera.base.setTranslateZ(para.camera.base.getTranslateZ()+d*Math.cos(Math.toRadians(para.camera.base.getRotate())));
			}else if(e.getCode().equals(KeyCode.DOWN)){
				para.camera.base.setTranslateX(para.camera.base.getTranslateX()-d*Math.sin(Math.toRadians(para.camera.base.getRotate())));
				para.camera.base.setTranslateZ(para.camera.base.getTranslateZ()-d*Math.cos(Math.toRadians(para.camera.base.getRotate())));
			}
		});
	}	
}
