package light;

import javafx.scene.AmbientLight;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import ops.Parameter;

public class Light {
	public Parameter para;
	public Light(Parameter para){
		this.para = para;
		setLight();
	}
	public void setLight(){
		PointLight light = new PointLight();
		light.getTransforms().add(new Translate(50.0D, -10000.0D, 165.0D));
		para.root.getChildren().add(light);
		AmbientLight ambientLight = new AmbientLight(Color.rgb(50, 50, 50));
		para.root.getChildren().add(ambientLight);
	}
}
