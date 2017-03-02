package object;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class SID {
	public MeshView meshView = new MeshView();
	public Parameter para;
	public double[] Send,Nend,Start,End;
	public float pdg,out30w,in30w,in15w,DERh,RWYl;
	public float dr,dr1,dr2,dr3;
	public double a;
	TriangleMesh tm = new TriangleMesh();
	public SID(Parameter para){
		this.para = para;
	}
	public void setNewSID(control.SID sid){
		Send = sid.Send;
		Nend = sid.Nend;
		Start = sid.Start;
		End = sid.End;
		pdg = (float)sid.pdg;
		out30w = (float)sid.out30w;
		in30w = (float)sid.in30w;
		in15w = (float)sid.in15w;
		DERh = (float)sid.DERh;
		RWYl = (float)Math.sqrt((Send[0]-Nend[0])*(Send[0]-Nend[0])+(Send[1]-Nend[1])*(Send[1]-Nend[1]));
		
		dr1 = (in15w/2-150)/(float)Math.tan(Math.toRadians(15));
		dr2 = (in15w-150)/(float)Math.tan(Math.toRadians(15));
		dr3 = 10*1852;
		if(!para.root.getChildren().contains(meshView)){
			setSID();
		}else{
			if(meshView.isVisible()){
				meshView.setVisible(false);	
			}else{
				meshView.setVisible(true);
			}
			
		}
	}
	public void setRevisedSID(control.SID sid){
		Send = sid.Send;
		Nend = sid.Nend;
		Start = sid.Start;
		End = sid.End;
		pdg = (float)sid.pdg;
		out30w = (float)sid.out30w;
		in30w = (float)sid.in30w;
		in15w = (float)sid.in15w;
		DERh = (float)sid.DERh;
		RWYl = (float)Math.sqrt((Send[0]-Nend[0])*(Send[0]-Nend[0])+(Send[1]-Nend[1])*(Send[1]-Nend[1]));
		a = Math.toDegrees(Math.atan((Send[1]-Nend[1])/(Send[0]-Nend[0])));
		dr1 = (in15w/2-150)/(float)Math.tan(Math.toRadians(15));
		dr2 = (in15w-150)/(float)Math.tan(Math.toRadians(15));
		dr3 = 10*1852;
		tm.getPoints().clear();
		float[] points = {
				RWYl,-DERh-5,150,//0
				RWYl,-DERh-5,-150,//1
				RWYl+dr1,-DERh-5-dr1*(float)Math.tan(Math.toRadians(pdg)),in15w/2,//2
				RWYl+dr1,-DERh-5-dr1*(float)Math.tan(Math.toRadians(pdg)),-in15w/2,//3
				RWYl+dr2,-DERh-5-dr2*(float)Math.tan(Math.toRadians(pdg)),in15w,//4
				RWYl+dr2,-DERh-5-dr2*(float)Math.tan(Math.toRadians(pdg)),-in15w,//5
				RWYl+dr3,-DERh-5-dr3*(float)Math.tan(Math.toRadians(pdg)),in15w,//6
				RWYl+dr3,-DERh-5-dr3*(float)Math.tan(Math.toRadians(pdg)),-in15w,//7
		};
		tm.getPoints().addAll(points);
        meshView.setTranslateX(Start[0]);
        meshView.setTranslateZ(Start[1]);
        
	}
	public void setSID(){
		double a = Math.toDegrees(Math.atan((Send[1]-Nend[1])/(Send[0]-Nend[0])));
		float[] points = {
				RWYl,-DERh-5,150,//0
				RWYl,-DERh-5,-150,//1
				RWYl+dr1,-DERh-5-dr1*(float)Math.tan(Math.toRadians(pdg)),in15w/2,//2
				RWYl+dr1,-DERh-5-dr1*(float)Math.tan(Math.toRadians(pdg)),-in15w/2,//3
				RWYl+dr2,-DERh-5-dr2*(float)Math.tan(Math.toRadians(pdg)),in15w,//4
				RWYl+dr2,-DERh-5-dr2*(float)Math.tan(Math.toRadians(pdg)),-in15w,//5
				RWYl+dr3,-DERh-5-dr3*(float)Math.tan(Math.toRadians(pdg)),in15w,//6
				RWYl+dr3,-DERh-5-dr3*(float)Math.tan(Math.toRadians(pdg)),-in15w,//7
		};
		int[] face = {0,0,1,0,3,0,3,0,2,0,0,0,
				2,0,3,0,5,0,5,0,4,0,2,0,
				4,0,5,0,7,0,7,0,6,0,4,0}; 
		tm.getPoints().addAll(points);
		tm.getTexCoords().addAll(0,0);
		tm.getFaces().addAll(face);
        // マテリアルを作成
        PhongMaterial   material    = new PhongMaterial();
        material.setDiffuseColor( Color.CORAL);
        //material.setSpecularColor( Color.GREY );
         
        // メッシュを登録
        meshView.setDrawMode( DrawMode.FILL);
        meshView.setMesh(tm);
        meshView.setMaterial( material );
        meshView.setCullFace(CullFace.NONE);
        meshView.setTranslateX(Start[0]);
        meshView.setTranslateZ(Start[1]);
        Rotate RotateY = new Rotate(-a, Rotate.Y_AXIS);
        meshView.getTransforms().addAll(RotateY);
        
        para.root.getChildren().add(meshView);
	}
}
