package control;

import java.io.File;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import object.Rwy;
import javafx.stage.FileChooser.ExtensionFilter;
import ops.Parameter;
import ops.SurfaceMaker;

public class Open {
	public Parameter para;
	public Pane pane;
	public Button btn;
	public SurfaceMaker sm;
	public Open(Parameter para,Pane pane){
		this.para = para;
		this.pane = pane;		
		sm = new SurfaceMaker(para);
	}
	public void setOpen(){
		setRoadButton();
	}
	public void setRoadButton(){
		btn = new Button("OPEN");
		btn.setLayoutX(50);
		pane.getChildren().add(btn);
		btn.setOnAction((e) -> {
			openFile();
			
		});
	}
	public void openFile(){
		para.cntl.stage.setAlwaysOnTop(false);
		
		File file = new File(System.getProperty("user.dir"));
		String jar_path = System.getProperty("java.class.path");
		String pathSeparator = System.getProperty("path.separator");
		File f = new File(jar_path.split(pathSeparator)[0]);
		String ff = f.getParent();
		File fff = new File(ff + "/terrainData/CSV");
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.setTitle("Open Resource File");
		if (file != null) {
			fc.setInitialDirectory(fff);
		}
		fc.getExtensionFilters()
				.addAll(new ExtensionFilter[] { new ExtensionFilter("Text Files", new String[] { "*.csv" }) });
		 List<File> importFiles = fc.showOpenMultipleDialog(null);
         if (importFiles != null) {

             for (File imf : importFiles) {
     			sm.readCSVfile(imf.getAbsolutePath());
             }
         }
	}
}
