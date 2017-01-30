package ops;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.shape.MeshView;

public class Data3D {
	public Parameter para;
	MeshView meshView = new MeshView();
	public SurfaceMaker sm;
	public ArrayList<XYZ> data = new ArrayList<XYZ>();
	public FileRoader fr;
	public FileMaker fm;
	public Data3D(Parameter para){
		this.para = para;
		fr = new FileRoader(para);
		fm = new FileMaker(para);
		sm = new SurfaceMaker(para);
	}
	public MeshView setData(){
		String jar_path = System.getProperty("java.class.path");
		String pathSeparator = System.getProperty("path.separator");
		String ff;
		File fff;
		ff = (new File(jar_path.split(pathSeparator)[0])).getParent() + "/src/FG-GML-5340-53-dem10b-20161001.csv";
		fff = new File(ff);
		if (fff.exists()) {
			meshView = sm.readCSVfile(ff);
		} else {
			fr.road("FG-GML-5340-53-dem10b-20161001.xml");
			fm.writeCSV("FG-GML-5340-53-dem10b-20161001.xml");
			meshView = sm.readCSVfile(ff);
		}
		return meshView;
	}
}
