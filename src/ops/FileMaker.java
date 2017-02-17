package ops;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

public class FileMaker {
	public Parameter para;
	public FileMaker(Parameter para){
		this.para = para;
	}
	public void writeCSV(String filename) {
		try {
			filename = filename.replace("xml", "csv");
			BufferedWriter ex = new BufferedWriter(new FileWriter(filename));
			for(Iterator<XYZ> ite = para.cntl.road.data.iterator(); ite.hasNext();){			
				XYZ one = (XYZ) ite.next();
				ex.write(one.x + ",");
				ex.write(one.y + ",");
				ex.write(String.valueOf(one.z));				
				ex.newLine();
			}
			ex.close();
		} catch (IOException arg4) {
			System.out.println(arg4);
		}

	}
}
