package ops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class SurfaceMaker {
	public Parameter para; 
	public SurfaceMaker(Parameter para){
		this.para = para;
	}
	public void readCSVfile(String csvFile){
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
			ObservableFloatArray texcoords = FXCollections.observableFloatArray();
			ObservableIntegerArray faces = FXCollections.observableIntegerArray();
			float[] corner = new float[8];
			while ((line = br.readLine()) != null) {
				String[] f = line.split(ex, -1);
				float tmpX = Float.parseFloat(f[0]);
				float tmpY = -Float.parseFloat(f[2]);
				float tmpZ = Float.parseFloat(f[1]);
				Integer vCurrent = vertexID[idX][idZ];
				points.addAll(tmpX);  
				points.addAll(tmpY);  
				points.addAll(tmpZ);
				
				texcoords.addAll((float)idX/1124);
				texcoords.addAll((float)idZ/749);
				vertexID[idX][idZ] = ctr++;
				vCurrent = vertexID[idX][idZ];
				if (idZ !=  0 && idX != 0) {
					Integer vUpLeft = vertexID[idX-1][idZ - 1];
					Integer vLeft = vertexID[idX - 1][idZ];
					Integer vUp = vertexID[idX][idZ - 1];
					
					faces.addAll(vUpLeft);  
					faces.addAll(vUpLeft);  
					faces.addAll(vLeft);  
					faces.addAll(vLeft);  
					faces.addAll(vCurrent);  
					faces.addAll(vCurrent); 
					faces.addAll(vCurrent);  
					faces.addAll(vCurrent);  
					faces.addAll(vUp);  
					faces.addAll(vUp);  
					faces.addAll(vUpLeft);  
					faces.addAll(vUpLeft);
				}
				if(idX==0&&idZ==0){corner[0]=tmpX;corner[1]=tmpZ;}
				else if(idX==para.dimensionX-1&&idZ==0){corner[2]=tmpX;corner[3]=tmpZ;}
				else if(idX==0&&idZ==para.dimensionZ-1){corner[4]=tmpX;corner[5]=tmpZ;}
				else if(idX==para.dimensionX-1&&idZ==para.dimensionZ-1){corner[6]=tmpX;corner[7]=tmpZ;}
				idX ++;
				if(idX==para.dimensionX){idX=0;idZ++;}
				f=null;
			}
			TriangleMesh mesh = new TriangleMesh();
			mesh.getPoints().addAll(points);
	        mesh.getTexCoords().addAll(texcoords);
	        mesh.getFaces().addAll(faces);	        	        
	        meshView.setMesh(mesh);	        
	        points = null;
	        faces = null;
	        // マテリアルを作成
	        PhongMaterial   material    = new PhongMaterial();
	        material.setDiffuseColor( Color.web( "#00FF00" ) );
	        //material.setSpecularColor( Color.GREEN );
	        
	        meshView.setDrawMode( DrawMode.FILL );
	        meshView.setMesh( mesh );
	        meshView.setMaterial( material(csvFile) );
	        meshView.setCullFace(CullFace.NONE);
	        para.root.getChildren().add(meshView);
	        
	        //readPDFfile(csvFile,corner);
	        
			br.close();
		} catch (FileNotFoundException arg7) {
			System.out.println(arg7);
		} catch (IOException arg8) {
			System.out.println(arg8);
		}
	}
	public void readPDFfile(String csvFile,float[] corner){
		MeshView meshView = new MeshView();
        TriangleMesh mesh = new TriangleMesh();
        System.out.println(csvFile);
        //空間での頂点座標
        //インデックスをつくっている。
        float[] corners = {
        		corner[0],0,corner[1],
        		corner[4],0,corner[5],
        		corner[6],0,corner[7],
        		corner[2],0,corner[3],
        };
        //貼り付ける画像の座標
        //インデックスを作っている
        float[] texCoords = {
                0,0,//画像の左上の点 t0
                0,1,//画像の左下の点 t1
                1,1,//画像の右下の点 t2
                1,0,//画像の右上の点 t3
        };
        //上記の2つを対応づけて三角形の面をつくる。
        //インデックスで指定
        //反時計回りで三角形を定義していることに注意
        int[] faces = {
                0,0,1,1,2,2, //三角形１ p0,t0,p1,t1,p2,t2,
                2,2,3,3,0,0//三角形２ p2,t2,p3,t3,p0,t0
        };
        mesh.getPoints().addAll(corners);
        mesh.getTexCoords().addAll(texCoords);
        mesh.getFaces().addAll(faces);
        meshView.setMesh(mesh);
        
        String url = new File(csvFile).toURI().toString();
        url = url.replace("csv", "jpg");
        Image img = new Image(url);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(img);
        
        meshView.setMaterial(material);
        meshView.setCullFace(CullFace.BACK);
        //para.root.getChildren().addAll(meshView);
        para.obj.trn.terrain.getChildren().add(meshView);
	}
	public PhongMaterial material(String csvFile){
        String url = new File(csvFile).toURI().toString();
        url = url.replace("csv", "jpg");
        Image img = new Image(url);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(img);
        return material;
	}
	
}
