package control;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class APVOAS {
	public Parameter para;
	public Pane pane,paneAPVOAS;
	public ToggleButton btn;
	public double DERh,aFAP,ma,Xma,Xfas,Xmatf,THRelve,VPA,RDH,T,OCS;
	public double[] Nend = new double[2];
	public double[] Send = new double[2];
	public double[] Start = new double[2];
	public double[] End = new double[2];
	public APVOAS(Parameter para,Pane pane){
		this.para = para;
		this.pane = pane;
		
		setButton();
		setPane();
		setItem();
		setInitial();
	}
	public void setInitial(){
		//RWY24
		Nend = para.cal.coordToXY(351615.86,1361114.10);
		Send = para.cal.coordToXY(351511.50,1360945.01);
		Start = Nend;
		End = Send;
		DERh = 97;
		THRelve = 100;
		ma = 0.025;
		VPA = 3;
		RDH = 15;
		T = -10;
		aFAP = 1700;
		OCS = 1400;
		paneAPVOAS.setVisible(false);
		Xmatf = 5;
		Xma = 1400;
	}
	public void setButton(){
		btn = new ToggleButton("APV");
		btn.setLayoutX(85);
		btn.setLayoutY(50);
		pane.getChildren().add(btn);		
		btn.setOnAction((e) -> {
			if(btn.isSelected()){
				para.cntl.stage.setHeight(500);
				para.obj.apvoas.setNewAPVOAS(this);
				paneAPVOAS.setVisible(true);
			}else{
				para.cntl.stage.setHeight(100);
				paneAPVOAS.setVisible(false);
			}					
		});	}
	public void setPane(){
		paneAPVOAS = new Pane();
		paneAPVOAS.setPrefWidth(200);
		paneAPVOAS.setPrefHeight(400);
		paneAPVOAS.setLayoutY(80);
		paneAPVOAS.setStyle("-fx-background-color: grey;");
		pane.getChildren().add(paneAPVOAS);		
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
				para.obj.apvoas.setRevisedAPVOAS(this);
				para.obj.apvoas.meshView.getTransforms().addAll(new Rotate(180, Rotate.Y_AXIS));
			}else if(cmbrwy.getSelectionModel().getSelectedItem().equals("24")){
				DERh = 97;
				Start = Nend;
				End = Send;
				para.obj.apvoas.setRevisedAPVOAS(this);
		        
		        para.obj.apvoas.meshView.getTransforms().addAll(new Rotate(180, Rotate.Y_AXIS));
			} 
		});
		
		Label lblFAP_ALT = new Label("FAP_ALT");
		lblFAP_ALT.setLayoutX(5);
		lblFAP_ALT.setLayoutY(lblrwy.getLayoutY()+lblrwy.getPrefHeight()+5);
		lblFAP_ALT.setPrefWidth(60);
		lblFAP_ALT.setPrefHeight(30);
		Spinner<Double> spnFAP_ALT = new Spinner<Double>();
		spnFAP_ALT.setLayoutX(lblFAP_ALT.getLayoutX()+lblFAP_ALT.getPrefWidth()+10);
		spnFAP_ALT.setLayoutY(lblFAP_ALT.getLayoutY());
		spnFAP_ALT.setPrefWidth(120);
		spnFAP_ALT.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1000,5000,1700,100));
		spnFAP_ALT.setEditable(true);
		spnFAP_ALT.valueProperty().addListener((ov,oldValue,newValue)->{
			aFAP = newValue;
			para.obj.apvoas.setRevisedAPVOAS(this);
		});
		
		Label lblVPA = new Label("VPA");
		lblVPA.setLayoutX(5);
		lblVPA.setLayoutY(lblFAP_ALT.getLayoutY()+lblFAP_ALT.getPrefHeight()+5);
		lblVPA.setPrefWidth(60);
		lblVPA.setPrefHeight(30);
		Spinner<Double> spnVPA = new Spinner<Double>();
		spnVPA.setLayoutX(lblVPA.getLayoutX()+lblVPA.getPrefWidth()+10);
		spnVPA.setLayoutY(lblVPA.getLayoutY());
		spnVPA.setPrefWidth(120);
		spnVPA.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(2.5,3.5,3.0,0.1));
		spnVPA.setEditable(true);
		spnVPA.valueProperty().addListener((ov,oldValue,newValue)->{
			VPA = newValue;
			para.obj.apvoas.setRevisedAPVOAS(this);
		});
		
		Label lblRDH = new Label("RDH");
		lblRDH.setLayoutX(5);
		lblRDH.setLayoutY(lblVPA.getLayoutY()+lblVPA.getPrefHeight()+5);
		lblRDH.setPrefWidth(60);
		lblRDH.setPrefHeight(30);
		Spinner<Double> spnRDH = new Spinner<Double>();
		spnRDH.setLayoutX(lblRDH.getLayoutX()+lblRDH.getPrefWidth()+10);
		spnRDH.setLayoutY(lblRDH.getLayoutY());
		spnRDH.setPrefWidth(120);
		spnRDH.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(14,20,15,0.1));
		spnRDH.setEditable(true);
		spnRDH.valueProperty().addListener((ov,oldValue,newValue)->{
			RDH = newValue;
			para.obj.apvoas.setRevisedAPVOAS(this);
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
		spnma.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(2.5,5.0,2.5,0.1));
		spnma.setEditable(true);
		spnma.valueProperty().addListener((ov,oldValue,newValue)->{
			ma = newValue;
			para.obj.apvoas.setRevisedAPVOAS(this);
		});
		
		Label lblTHR_MATF = new Label("THR-MATF");
		lblTHR_MATF.setLayoutX(5);
		lblTHR_MATF.setLayoutY(lblma.getLayoutY()+lblma.getPrefHeight()+5);
		lblTHR_MATF.setPrefWidth(70);
		lblTHR_MATF.setPrefHeight(30);
		Spinner<Double> spnTHR_MATF = new Spinner<Double>();
		spnTHR_MATF.setLayoutX(lblTHR_MATF.getLayoutX()+lblTHR_MATF.getPrefWidth()+10);
		spnTHR_MATF.setLayoutY(lblTHR_MATF.getLayoutY());
		spnTHR_MATF.setPrefWidth(110);
		spnTHR_MATF.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,10.0,5,0.1));
		spnTHR_MATF.setEditable(true);
		spnTHR_MATF.valueProperty().addListener((ov,oldValue,newValue)->{
			Xmatf = newValue;
			para.obj.apvoas.setRevisedAPVOAS(this);//para.obj.sid.setRevisedSID(this);
		});
		
		Label lblOCS = new Label("OCS");
		lblOCS.setLayoutX(5);
		lblOCS.setLayoutY(lblTHR_MATF.getLayoutY()+lblTHR_MATF.getPrefHeight()+5);
		lblOCS.setPrefWidth(70);
		lblOCS.setPrefHeight(30);
		Spinner<Double> spnOCS = new Spinner<Double>();
		spnOCS.setLayoutX(lblOCS.getLayoutX()+lblOCS.getPrefWidth()+10);
		spnOCS.setLayoutY(lblOCS.getLayoutY());
		spnOCS.setPrefWidth(110);
		spnOCS.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1000,5000,1500,100));
		spnOCS.setEditable(true);
		spnOCS.valueProperty().addListener((ov,oldValue,newValue)->{
			OCS = newValue;
			para.obj.apvoas.setRevisedAPVOAS(this);
		});
		
		Label lblcat = new Label("CAT");
		lblcat.setLayoutX(5);
		lblcat.setLayoutY(lblOCS.getLayoutY()+lblOCS.getPrefHeight()+5);
		lblcat.setPrefWidth(60);
		lblcat.setPrefHeight(30);
		ComboBox<String> cmbcat = new ComboBox<String>();
		cmbcat.setLayoutX(lblcat.getLayoutX()+lblcat.getPrefWidth()+10);
		cmbcat.setLayoutY(lblcat.getLayoutY());
		cmbcat.setPrefWidth(120);
		cmbcat.getItems().addAll(new String[] { "D","C","B","A"});
		cmbcat.getSelectionModel().select(0);
		cmbcat.valueProperty().addListener((ov,oldValue,newValue)->{
			if(newValue.equals("D")){Xma=1400;}
			else if(newValue.equals("C")){Xma=1100;}
			else{Xma=900;}
			para.obj.apvoas.setRevisedAPVOAS(this);
		});
		Label lblTEMP = new Label("TEMP");
		lblTEMP.setLayoutX(5);
		lblTEMP.setLayoutY(lblcat.getLayoutY()+lblcat.getPrefHeight()+5);
		lblTEMP.setPrefWidth(70);
		lblTEMP.setPrefHeight(30);
		Spinner<Double> spnTEMP = new Spinner<Double>();
		spnTEMP.setLayoutX(lblTEMP.getLayoutX()+lblTEMP.getPrefWidth()+10);
		spnTEMP.setLayoutY(lblTEMP.getLayoutY());
		spnTEMP.setPrefWidth(110);
		spnTEMP.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-50,40,-10,10));
		spnTEMP.setEditable(true);
		spnTEMP.valueProperty().addListener((ov,oldValue,newValue)->{
			T = newValue;
			para.obj.apvoas.setRevisedAPVOAS(this);
		});
		
		
		paneAPVOAS.getChildren().addAll(lblrwy,cmbrwy,lblFAP_ALT,spnFAP_ALT,lblVPA,spnVPA,
				lblRDH,spnRDH,lblma,spnma,lblTHR_MATF,spnTHR_MATF,lblOCS,spnOCS,lblcat,cmbcat,lblTEMP,spnTEMP);
	}
}
