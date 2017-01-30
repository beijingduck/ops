package control;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import ops.FileMaker;
import ops.FileRoader;
import ops.Parameter;
import ops.XYZ;

public class Road {
	public Parameter para;
	public Button btn;
	public Pane pane;
	public FileRoader fr;
	public FileMaker fm;
	public ArrayList<XYZ> data = new ArrayList<XYZ>();
	public Road(Parameter para,Pane pane){
		this.para = para;	
		this.pane = pane;
		fr = new FileRoader(para);
		fm = new FileMaker(para);
	}
	public void setRoad(){
		setRoadButton();
	}
	public void setRoadButton(){
		btn = new Button("ROAD");
		pane.getChildren().add(btn);
		btn.setOnAction((e) -> {
			roadFile();
			
		});
	}
	public void roadFile(){
		para.cntl.stage.setAlwaysOnTop(false);
		
		File file = new File(System.getProperty("user.dir"));
		String jar_path = System.getProperty("java.class.path");
		String pathSeparator = System.getProperty("path.separator");
		File f = new File(jar_path.split(pathSeparator)[0]);
		String ff = f.getParent();
		File fff = new File(ff + "/terrainData/XML");
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.setTitle("Open Resource File");
		if (file != null) {
			fc.setInitialDirectory(fff);
		}
		fc.getExtensionFilters()
				.addAll(new ExtensionFilter[] { new ExtensionFilter("Text Files", new String[] { "*.xml" }) });
		File importFile = fc.showOpenDialog((Window) null);
		fr.road(importFile.getAbsolutePath());
		fm.writeCSV(importFile.getAbsolutePath());
	}
		
}
