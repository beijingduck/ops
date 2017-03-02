package control;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class SID {
	public Parameter para;
	public Pane pane,paneSID;
	public ToggleButton btn;
	public double out30w,in30w,in15w,pdg,DERh,RWYl;
	public double[] Nend = new double[2];
	public double[] Send = new double[2];
	public double[] Start = new double[2];
	public double[] End = new double[2];
	public SID(Parameter para,Pane pane){
		this.para = para;
		this.pane = pane;
		
		setButton();
		setPane();
		setItem();
		setInitial();
	}
	public void setInitial(){
		//Basic RNP1,PDG3.3,RWY06
		out30w = 3.5*1852;
		in30w = 2.5*1852;
		in15w = 2*1852;
		Nend = para.cal.coordToXY(351615.86,1361114.10);
		Send = para.cal.coordToXY(351511.50,1360945.01);
		Start = Send;
		End = Nend;
		DERh = 100;
		pdg = 3.3;
	}
	public void setButton(){
		btn = new ToggleButton("SID");
		btn.setLayoutY(50);
		pane.getChildren().add(btn);		
		btn.setOnAction((e) -> {
			if(btn.isSelected()){
				para.cntl.stage.setHeight(500);
				para.obj.sid.setNewSID(this);
				paneSID.setVisible(true);
			}else{
				para.cntl.stage.setHeight(100);
				para.obj.sid.setNewSID(this);
				paneSID.setVisible(false);
			}	
		});	}
	public void setPane(){
		paneSID = new Pane();
		paneSID.setPrefWidth(200);
		paneSID.setPrefHeight(400);
		paneSID.setLayoutY(80);
		paneSID.setStyle("-fx-background-color: grey;");
		pane.getChildren().add(paneSID);		
	}
	public void setItem(){
		Label lblrwy = new Label("RWY");
		lblrwy.setLayoutX(5);
		lblrwy.setLayoutY(5);
		lblrwy.setPrefWidth(60);
		lblrwy.setPrefHeight(30);
		ComboBox<String> cmbrwy = new ComboBox<String>();
		cmbrwy.setLayoutX(lblrwy.getLayoutX()+lblrwy.getPrefWidth()+10);
		cmbrwy.setLayoutY(lblrwy.getLayoutY());
		cmbrwy.setPrefWidth(120);
		cmbrwy.getItems().addAll(new String[] { "06","24"});
		cmbrwy.getSelectionModel().select(0);
		cmbrwy.valueProperty().addListener((ov,oldValue,newValue)->{
			if(cmbrwy.getSelectionModel().getSelectedItem().equals("06")){
				DERh = 100;
				Start = Send;
				End = Nend;
				para.obj.sid.setRevisedSID(this);		        
		        para.obj.sid.meshView.getTransforms().addAll(new Rotate(180, Rotate.Y_AXIS));
			}else if(cmbrwy.getSelectionModel().getSelectedItem().equals("24")){
				DERh = 97;
				Start = Nend;
				End = Send;
				para.obj.sid.setRevisedSID(this);
				para.obj.sid.meshView.getTransforms().addAll(new Rotate(180, Rotate.Y_AXIS));
			} 
		});
		
		Label lblspec = new Label("SPEC");
		lblspec.setLayoutX(5);
		lblspec.setLayoutY(lblrwy.getLayoutY()+lblrwy.getPrefHeight()+5);
		lblspec.setPrefWidth(60);
		lblspec.setPrefHeight(30);
		ComboBox<String> cmbspec = new ComboBox<String>();
		cmbspec.setLayoutX(lblspec.getLayoutX()+lblspec.getPrefWidth()+10);
		cmbspec.setLayoutY(lblspec.getLayoutY());
		cmbspec.setPrefWidth(120);
		cmbspec.getItems().addAll(new String[] { "Basic RNP1","RNAV1"});
		cmbspec.getSelectionModel().select(0);
		cmbspec.valueProperty().addListener((ov,oldValue,newValue)->{
			if(newValue.equals("Basic RNP1")){
				out30w = 3.5*1852;
			}else if(newValue.equals("RNAV1")){
				out30w = 5*1852;
			}
		});
		
		Label lblpdg = new Label("PDG");
		lblpdg.setLayoutX(5);
		lblpdg.setLayoutY(lblspec.getLayoutY()+lblspec.getPrefHeight()+5);
		lblpdg.setPrefWidth(60);
		lblpdg.setPrefHeight(30);
		Spinner<Double> spnpdg = new Spinner<Double>();
		spnpdg.setLayoutX(lblpdg.getLayoutX()+lblpdg.getPrefWidth()+10);
		spnpdg.setLayoutY(lblpdg.getLayoutY());
		spnpdg.setPrefWidth(120);
		spnpdg.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(3.3,10.0,0.1,0.1));
		spnpdg.setEditable(true);
		spnpdg.valueProperty().addListener((ov,oldValue,newValue)->{
			pdg = newValue;
			para.obj.sid.setRevisedSID(this);
		});
		
		
		paneSID.getChildren().addAll(lblrwy,cmbrwy,lblspec,cmbspec,lblpdg,spnpdg);
	}
}
