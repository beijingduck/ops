package object;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class Surface {
	public double a,d;
	public float SW;
	public TriangleMesh tm1,tm2,w,x,y,z;
	public MeshView meshView1,meshView2,W,X,Y,Z;
	public Parameter para;
	public double[] th06 = new double[2];
	public double[] th24 = new double[2];
	public Surface(Parameter para){
		this.para = para;
		setBIS();
		setOAS();
	}
	public void setBIS(){
		double[] xy1 = para.cal.coordToXY(351511.50, 1360945.01);
		double[] xy2 = para.cal.coordToXY(351615.86, 1361114.10);
		double a = Math.toDegrees(Math.atan((xy1[1]-xy2[1])/(xy1[0]-xy2[0])));
		
		tm1 = new TriangleMesh();
		float[] points = {
				-12660,-300,2040,//0
				-12660,-300,-2040,//1
				-3060,-300,2278,//2
				-3060,-60,600,//3
				-3060,-60,-600,//4
				-3060,-300,-2278,//5
				-60,-300,2248,//6
				-60,0,150,//7
				-60,0,-150,//8
				-60,-300,-2248,//9
				900,0,150,//10
				900,0,-150,//11
				2700,-300,2248,//12
				2700,-45,464,//13
				2700,-45,-464,//14
				2700,-300,-2248,//15
				12900,-300,3014,//16
				12900,-300,-3014//17
		};
		int[] face1 = {0,0,1,0,8,0,8,0,7,0,0,0,
				10,0,11,0,14,0,14,0,13,0,10,0,
				13,0,14,0,17,0,17,0,16,0,13,0}; 
		tm1.getPoints().addAll(points);
		tm1.getTexCoords().addAll(0,0);
		tm1.getFaces().addAll(face1);
		meshView1 = new MeshView();
        // マテリアルを作成
        PhongMaterial   material1    = new PhongMaterial();
        material1.setDiffuseColor( Color.LIGHTBLUE);
        //material.setSpecularColor( Color.GREY );
         
        // メッシュを登録
        meshView1.setDrawMode( DrawMode.FILL);
        meshView1.setMesh(tm1);
        meshView1.setMaterial( material1 );
        meshView1.setCullFace(CullFace.NONE);
        meshView1.setTranslateX(xy1[0]);
        meshView1.setTranslateZ(xy1[1]);
        Rotate RotateY = new Rotate(-a, Rotate.Y_AXIS);
        meshView1.getTransforms().addAll(RotateY);
        meshView1.setTranslateY(-97);
        
		tm2 = new TriangleMesh();
		int[] face2 = {0,0,7,0,6,0,12,0,13,0,16,0,
				1,0,9,0,8,0,14,0,15,0,17,0,
				6,0,7,0,10,0,10,0,13,0,12,0,12,0,6,0,10,0,
				8,0,9,0,15,0,15,0,14,0,11,0,11,0,8,0,15,0
		}; 
		tm2.getPoints().addAll(points);
		tm2.getTexCoords().addAll(0,0);
		tm2.getFaces().addAll(face2);
		meshView2 = new MeshView();
        // マテリアルを作成
        PhongMaterial   material2    = new PhongMaterial();
        material2.setDiffuseColor( Color.LIGHTPINK);
         
        // メッシュを登録
        meshView2.setDrawMode( DrawMode.FILL);
        meshView2.setMesh(tm2);
        meshView2.setMaterial( material2 );
        meshView2.setCullFace(CullFace.NONE);
        meshView2.setTranslateX(xy1[0]);
        meshView2.setTranslateZ(xy1[1]);
        meshView2.setTranslateY(-97);
        meshView2.getTransforms().addAll(RotateY);        
        
        para.root.getChildren().addAll(meshView1,meshView2);
	}
	public void setOAS(){
		double[] xy1 = para.cal.coordToXY(351511.50, 1360945.01);
		double[] xy2 = para.cal.coordToXY(351615.86, 1361114.10);
		double a = Math.toDegrees(Math.atan((xy1[1]-xy2[1])/(xy1[0]-xy2[0])));		
		float[] points = {
				-263,0,51,//C 0
				-263,0,-51,//C 1
				314,0,139,//D 2
				314,0,-139,//D 3
				900,0,188,//E 4
				900,0,-188,//E 5
				-10789,-300,78,//C" 6
				-10789,-300,-78,//C" 7		
				-5409,-300,894,//D" 8
				-5409,-300,-894,//D" 9
				8400,-300,2063,//E" 10
				8400,-300,-2063,//E" 11
		};
		int[] faceW = {6,0,7,0,1,0,1,0,0,0,6,0};
		int[] faceX = {8,0,6,0,0,0,0,0,2,0,8,0,
				7,0,9,0,3,0,3,0,1,0,7,0};
		int[] faceY = {8,0,2,0,4,0,4,0,10,0,8,0,
				9,0,11,0,5,0,5,0,3,0,9,0};
		int[] faceZ = {4,0,5,0,11,0,11,0,10,0,4,0};
				
		w = new TriangleMesh();
		w.getPoints().addAll(points);
		w.getTexCoords().addAll(0,0);
		w.getFaces().addAll(faceW);
		W = new MeshView();
        // マテリアルを作成
        PhongMaterial   materialW    = new PhongMaterial();
        materialW.setDiffuseColor( Color.LIGHTPINK);
         
        // メッシュを登録
        W.setDrawMode( DrawMode.FILL);
        W.setMesh(w);
        W.setMaterial( materialW );
        W.setCullFace(CullFace.NONE);
        W.setTranslateX(xy1[0]);
        W.setTranslateZ(xy1[1]);
        W.setTranslateY(-97);
        Rotate RotateY = new Rotate(-a, Rotate.Y_AXIS);
        W.getTransforms().addAll(RotateY); 
        
        x = new TriangleMesh();
		x.getPoints().addAll(points);
		x.getTexCoords().addAll(0,0);
		x.getFaces().addAll(faceX);
		X = new MeshView();
        // マテリアルを作成
        PhongMaterial   materialX    = new PhongMaterial();
        materialX.setDiffuseColor( Color.DARKGRAY);
         
        // メッシュを登録
        X.setDrawMode( DrawMode.FILL);
        X.setMesh(x);
        X.setMaterial( materialX );
        X.setCullFace(CullFace.NONE);
        X.setTranslateX(xy1[0]);
        X.setTranslateZ(xy1[1]);
        X.setTranslateY(-97);
        X.getTransforms().addAll(RotateY); 
        
        y = new TriangleMesh();
		y.getPoints().addAll(points);
		y.getTexCoords().addAll(0,0);
		y.getFaces().addAll(faceY);
		Y = new MeshView();
        // マテリアルを作成
        PhongMaterial   materialY    = new PhongMaterial();
        materialY.setDiffuseColor( Color.BURLYWOOD);
         
        // メッシュを登録
        Y.setDrawMode( DrawMode.FILL);
        Y.setMesh(y);
        Y.setMaterial( materialY );
        Y.setCullFace(CullFace.NONE);
        Y.setTranslateX(xy1[0]);
        Y.setTranslateZ(xy1[1]);
        Y.setTranslateY(-97);
        Y.getTransforms().addAll(RotateY);

        z = new TriangleMesh();
		z.getPoints().addAll(points);
		z.getTexCoords().addAll(0,0);
		z.getFaces().addAll(faceZ);
		Z = new MeshView();
        // マテリアルを作成
        PhongMaterial   materialZ    = new PhongMaterial();
        materialZ.setDiffuseColor( Color.LIGHTSKYBLUE);
         
        // メッシュを登録
        Z.setDrawMode( DrawMode.FILL);
        Z.setMesh(z);
        Z.setMaterial( materialZ );
        Z.setCullFace(CullFace.NONE);
        Z.setTranslateX(xy1[0]);
        Z.setTranslateZ(xy1[1]);
        Z.setTranslateY(-97);
        Z.getTransforms().addAll(RotateY);
		
        para.root.getChildren().addAll(W,X,Y,Z);
	}
	public void setOIS(){
		double[] xy1 = para.cal.coordToXY(351511.50, 1360945.01);
		double[] xy2 = para.cal.coordToXY(351615.86, 1361114.10);
		double a = Math.toDegrees(Math.atan((xy1[1]-xy2[1])/(xy1[0]-xy2[0])));
		TriangleMesh mesh = new TriangleMesh();
		float[] points = {
				3000,-5,150,//0 start 
				3000,-5,-150,//1 start
				20000,-5-20000*(float)0.025,150+20000*(float)Math.tan(Math.toRadians(15)),//2 end
				20000,-5-20000*(float)0.025,-150-20000*(float)Math.tan(Math.toRadians(15)),//3 end
		};
		int[] face = {0,0,1,0,3,0,0,0,3,0,2,0};
		
		mesh.getPoints().addAll(points);
		mesh.getTexCoords().addAll(0,0);
		mesh.getFaces().addAll(face);
		MeshView meshView = new MeshView();
        // マテリアルを作成
        PhongMaterial   material    = new PhongMaterial();
        material.setDiffuseColor( Color.BLUE);
         
        // メッシュを登録
        meshView.setDrawMode( DrawMode.FILL);
        meshView.setMesh(mesh);
        meshView.setMaterial( material );
        meshView.setCullFace(CullFace.NONE);
        meshView.setTranslateX(xy2[0]);
        meshView.setTranslateZ(xy2[1]);
        meshView.setTranslateY(-100);		//northend
        Rotate RotateY = new Rotate(-a, Rotate.Y_AXIS);
        meshView.getTransforms().addAll(RotateY);
	}
}
