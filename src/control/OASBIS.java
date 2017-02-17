package control;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ops.Parameter;

public class OASBIS {
	public Parameter para;
	public Pane pane;
	public Button btnOAS,btnBIS;
	public OASBIS(Parameter para,Pane pane){
		this.para = para;
		this.pane = pane;
	}
	public void setOASBIS(){
		setBISButton();
		setOASButton();
	}
	public void setBISButton(){
		btnBIS = new Button("BIS");
		btnBIS.setLayoutX(110);
		pane.getChildren().add(btnBIS);
		btnBIS.setOnAction((e) -> {
			if(para.obj.sfc.meshView1.isVisible()){
				para.obj.sfc.meshView1.setVisible(false);
				para.obj.sfc.meshView2.setVisible(false);
			}else{
				para.obj.sfc.meshView1.setVisible(true);
				para.obj.sfc.meshView2.setVisible(true);
			}			
		});
	}
	public void setOASButton(){
		btnOAS = new Button("OAS");
		btnOAS.setLayoutX(150);
		pane.getChildren().add(btnOAS);
		btnOAS.setOnAction((e) -> {
			if(para.obj.sfc.W.isVisible()){
				para.obj.sfc.W.setVisible(false);
				para.obj.sfc.X.setVisible(false);
				para.obj.sfc.Y.setVisible(false);
				para.obj.sfc.Z.setVisible(false);
			}else{
				para.obj.sfc.W.setVisible(true);
				para.obj.sfc.X.setVisible(true);
				para.obj.sfc.Y.setVisible(true);
				para.obj.sfc.Z.setVisible(true);
			}			
		});
	}	
}
