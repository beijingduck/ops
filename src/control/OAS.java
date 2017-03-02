package control;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import ops.Parameter;

public class OAS {
	public Parameter para;
	public Pane pane,paneOAS;
	public ToggleButton btn;
	public double out30w,in30w,in15w,DERh,RWYl,ma,THRelve,cat,LOC_THR,RDH;
	public double[] Nend = new double[2];
	public double[] Send = new double[2];
	public double[] Start = new double[2];
	public double[] End = new double[2];
	public OAS(Parameter para,Pane pane){
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
		THRelve = 97;
		ma = 0.025;
		cat = 1;
		paneOAS.setVisible(false);
	}
	public void setButton(){
		btn = new ToggleButton("OAS");
		btn.setLayoutX(40);
		btn.setLayoutY(50);
		pane.getChildren().add(btn);		
		btn.setOnAction((e) -> {
			if(btn.isSelected()){
				para.cntl.stage.setHeight(500);
				//para.obj.sid.setNewSID(this);
				paneOAS.setVisible(true);
			}else{
				para.cntl.stage.setHeight(100);
				paneOAS.setVisible(false);
			}					
		});	}
	public void setPane(){
		paneOAS = new Pane();
		paneOAS.setPrefWidth(200);
		paneOAS.setPrefHeight(400);
		paneOAS.setLayoutY(80);
		paneOAS.setStyle("-fx-background-color: grey;");
		pane.getChildren().add(paneOAS);		
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
				//para.obj.sid.setRevisedSID(this);
			}else if(cmbrwy.getSelectionModel().getSelectedItem().equals("24")){
				DERh = 97;
				Start = Nend;
				End = Send;
				//para.obj.sid.setRevisedSID(this);
			} 
		});
		
		Label lblCAT = new Label("CAT");
		lblCAT.setLayoutX(5);
		lblCAT.setLayoutY(lblrwy.getLayoutY()+lblrwy.getPrefHeight()+5);
		lblCAT.setPrefWidth(60);
		lblCAT.setPrefHeight(30);
		ComboBox<String> cmbCAT = new ComboBox<String>();
		cmbCAT.setLayoutX(lblCAT.getLayoutX()+lblCAT.getPrefWidth()+10);
		cmbCAT.setLayoutY(lblCAT.getLayoutY());
		cmbCAT.setPrefWidth(120);
		cmbCAT.getItems().addAll(new String[] { "Ⅰ"});
		cmbCAT.getSelectionModel().select(0);
		cmbCAT.valueProperty().addListener((ov,oldValue,newValue)->{
		});
		
		Label lblLOC_THR = new Label("LOC-THR");
		lblLOC_THR.setLayoutX(5);
		lblLOC_THR.setLayoutY(lblCAT.getLayoutY()+lblCAT.getPrefHeight()+5);
		lblLOC_THR.setPrefWidth(60);
		lblLOC_THR.setPrefHeight(30);
		Spinner<Double> spnLOC_THR = new Spinner<Double>();
		spnLOC_THR.setLayoutX(lblLOC_THR.getLayoutX()+lblLOC_THR.getPrefWidth()+10);
		spnLOC_THR.setLayoutY(lblLOC_THR.getLayoutY());
		spnLOC_THR.setPrefWidth(120);
		spnLOC_THR.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(3000,3000,3000,100.0));
		spnLOC_THR.setEditable(true);
		spnLOC_THR.valueProperty().addListener((ov,oldValue,newValue)->{
			LOC_THR = newValue;
			//para.obj.sid.setRevisedSID(this);
		});
		
		Label lblRDH = new Label("RDH");
		lblRDH.setLayoutX(5);
		lblRDH.setLayoutY(lblLOC_THR.getLayoutY()+lblLOC_THR.getPrefHeight()+5);
		lblRDH.setPrefWidth(60);
		lblRDH.setPrefHeight(30);
		Spinner<Double> spnRDH = new Spinner<Double>();
		spnRDH.setLayoutX(lblRDH.getLayoutX()+lblRDH.getPrefWidth()+10);
		spnRDH.setLayoutY(lblRDH.getLayoutY());
		spnRDH.setPrefWidth(120);
		spnRDH.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(15,15,0.1,0.1));
		spnRDH.setEditable(true);
		spnRDH.valueProperty().addListener((ov,oldValue,newValue)->{
			RDH = newValue;
			//para.obj.sid.setRevisedSID(this);
		});
		
		Label lblma = new Label("MA CG");
		lblma.setLayoutX(5);
		lblma.setLayoutY(lblRDH.getLayoutY()+lblRDH.getPrefHeight()+5);
		lblma.setPrefWidth(60);
		lblma.setPrefHeight(30);
		Spinner<Double> spnma = new Spinner<Double>();
		spnma.setLayoutX(lblma.getLayoutX()+lblma.getPrefWidth()+10);
		spnma.setLayoutY(lblma.getLayoutY());
		spnma.setPrefWidth(120);
		spnma.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(2.5,5.0,0.5,0.5));
		spnma.setEditable(true);
		spnma.valueProperty().addListener((ov,oldValue,newValue)->{
			ma = newValue;
			//para.obj.sid.setRevisedSID(this);
		});
		
		Label lblcat = new Label("CAT");
		lblcat.setLayoutX(5);
		lblcat.setLayoutY(lblma.getLayoutY()+lblma.getPrefHeight()+5);
		lblcat.setPrefWidth(60);
		lblcat.setPrefHeight(30);
		ComboBox<String> cmbcat = new ComboBox<String>();
		cmbcat.setLayoutX(lblcat.getLayoutX()+lblcat.getPrefWidth()+10);
		cmbcat.setLayoutY(lblcat.getLayoutY());
		cmbcat.setPrefWidth(120);
		cmbcat.getItems().addAll(new String[] { "D"});
		cmbcat.getSelectionModel().select(0);
		cmbcat.valueProperty().addListener((ov,oldValue,newValue)->{
		});
		
		
		paneOAS.getChildren().addAll(lblrwy,cmbrwy,lblCAT,cmbCAT,lblLOC_THR,spnLOC_THR,
				lblRDH,spnRDH,lblma,spnma,lblcat,cmbcat);
	}
}
