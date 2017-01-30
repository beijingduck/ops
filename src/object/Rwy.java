package object;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import ops.Parameter;

public class Rwy {	
	public double a,d;
	public float SW;
	public TriangleMesh tm;
	public Parameter para;
	public double[] xy1 = new double[2];
	public double[] xy2 = new double[2];
	public Rwy(Parameter para){
		this.para = para;
		setRwy();
	}
	public void setRwy(){
		
		xy1 = para.cal.dirdisToXY(para.cal.coordToDirdis(351511.50, 1360945.01));
		xy2 = para.cal.dirdisToXY(para.cal.coordToDirdis(351615.86, 1361114.10));
		a = Math.atan((xy1[1]-xy2[1])/(xy1[0]-xy2[0]));
		
		d = 40;
		tm = new TriangleMesh();
		float[] points = {
				(float)(xy1[0]-d*Math.sin(a)),-97,(float)(xy1[1]+d*Math.cos(a)),
				(float)(xy1[0]+d*Math.sin(a)),-97,(float)(xy1[1]-d+Math.cos(a)),
				(float)(xy2[0]+d*Math.sin(a)),-100,(float)(xy2[1]-d*Math.cos(a)),
				(float)(xy2[0]-d*Math.sin(a)),-100,(float)(xy2[1]+d*Math.cos(a))				
		};
		int[] faces = {0,0,1,0,2,0,2,0,3,0,0,0};
		tm.getPoints().addAll(points);
		tm.getTexCoords().addAll(0,0);
		tm.getFaces().addAll(faces);
		MeshView meshView = new MeshView();
        // マテリアルを作成
        PhongMaterial   material    = new PhongMaterial();
        material.setDiffuseColor( Color.GREY);
        material.setSpecularColor( Color.GREY );
         
        // メッシュを登録
        meshView.setDrawMode( DrawMode.FILL);
        meshView.setMesh(tm);
        meshView.setMaterial( material );
        meshView.setCullFace(CullFace.BACK);
        
        para.root.getChildren().addAll(meshView);
	}
}
