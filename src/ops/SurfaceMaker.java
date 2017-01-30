package ops;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class SurfaceMaker {
	public Parameter para;	    
    float tmpX,tmpY,tmpZ;
    String[] f;
	public SurfaceMaker(Parameter para){
		this.para = para;
	}
	public MeshView readCSVfile(String csvFile){
		int ctr = 0;
		int idX = 0;
		int idZ = 0;
		MeshView meshView = new MeshView();
		Integer[][] vertexID = new Integer[para.dimensionX][para.dimensionZ];
		try {
			String ex = ",";
			BufferedReader br;
			String line;
			FileReader fr = new FileReader(csvFile);
			br = new BufferedReader(fr);
			ObservableFloatArray points = FXCollections.observableFloatArray();
			ObservableIntegerArray faces = FXCollections.observableIntegerArray();
			while ((line = br.readLine()) != null) {
				f = line.split(ex, -1);
				tmpX = Float.parseFloat(f[0]);
				tmpY = -Float.parseFloat(f[2]);
				tmpZ = Float.parseFloat(f[1]);
				Integer vCurrent = vertexID[idX][idZ];
				points.addAll(tmpX);  
				points.addAll(tmpY);  
				points.addAll(tmpZ);
				vertexID[idX][idZ] = ctr++;
				vCurrent = vertexID[idX][idZ];
					if (idZ !=  0 && idX != 0) {
						Integer vUpLeft = vertexID[idX-1][idZ - 1];
						Integer vLeft = vertexID[idX - 1][idZ];
						Integer vUp = vertexID[idX][idZ - 1];
						
						faces.addAll(vUpLeft);  
						faces.addAll(0);  
						faces.addAll(vLeft);  
						faces.addAll(0);  
						faces.addAll(vCurrent);  
						faces.addAll(0); 
						faces.addAll(vCurrent);  
						faces.addAll(0);  
						faces.addAll(vUp);  
						faces.addAll(0);  
						faces.addAll(vUpLeft);  
						faces.addAll(0);
					}
					idX ++;
					if(idX==para.dimensionX){idX=0;idZ++;}
			}
			TriangleMesh mesh = new TriangleMesh();
			mesh.getPoints().addAll(points);
	        mesh.getTexCoords().addAll(0,0);
	        mesh.getFaces().addAll(faces);
	         
	        // マテリアルを作成
	        PhongMaterial   material    = new PhongMaterial();
	        material.setDiffuseColor( Color.web( "#00FF00" ) );
	        material.setSpecularColor( Color.GREEN );
	         
	        // メッシュを登録
	        
	        meshView.setDrawMode( DrawMode.LINE );
	        meshView.setMesh( mesh );
	        meshView.setMaterial( material );
	        meshView.setCullFace(CullFace.NONE);
			br.close();
		} catch (FileNotFoundException arg7) {
			System.out.println(arg7);
		} catch (IOException arg8) {
			System.out.println(arg8);
		}
		return meshView;
	}
	
}
