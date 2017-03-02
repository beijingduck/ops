package object;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class APVOAS {
	public MeshView meshView = new MeshView();
	public MeshView meshView2 = new MeshView();
	public Parameter para;
	public double[] Send,Nend,Start,End;
	public float Xocs1,Xocs2,Xocs3,Zocs3,Xfap,Xfas,X30,Xma,Yma,Xmatf,Ymatf,Zmatf,T,VPA,tana,h,ATT,DERh,hFAP,hOCS,hTHR,LOC_THR,RDH,cat,ma;
	public float Yocs1,Yocs2,Yocs3,Ymapt,Xtop,Ytop,Xbtm,hi,h30,hmoc;
	public double a;
	public Point2D C,D,E,CC,DD,EE;
	TriangleMesh tm = new TriangleMesh();
	TriangleMesh tm2 = new TriangleMesh();
	public APVOAS(Parameter para){
		this.para = para;
	}
	public void setNewAPVOAS(control.APVOAS apvoas){
		Send = apvoas.Send;
		Nend = apvoas.Nend;
		Start = apvoas.Start;
		End = apvoas.End;
		a = Math.toDegrees(Math.atan((Send[1]-Nend[1])/(Send[0]-Nend[0])));
		hTHR = (float)apvoas.THRelve;
		T = (float)apvoas.T;
		RDH = (float)apvoas.RDH;
		VPA = (float)apvoas.VPA;
		ma = (float)apvoas.ma/100;
		hFAP = (float)apvoas.aFAP*(float)0.3048;
		hOCS = (float)apvoas.OCS*(float)0.3048;
		ATT = (float)0.24*1852;
		h = (float)para.cal.getDeltaH(T, hFAP, hTHR);
		hi = 75;
		h30 = 30;
		hmoc = 150;
		tana = (hFAP-h-hi)*(float)Math.tan(Math.toRadians(VPA)/(hFAP-hi));
		Xfas = (hi-RDH)/tana+ATT;
		Xocs1 = (hFAP-hTHR-RDH)/(float)Math.tan(Math.toRadians(VPA))+(float)(2.5-1.45)*1852/(float)Math.tan(Math.toRadians(30));
		Xocs2 = Xfas+(hOCS-hTHR)/tana;
		Xocs3 = (hFAP-hTHR-RDH)/(float)Math.tan(Math.toRadians(VPA))-(float)(1.45-0.95)*1852/(float)Math.tan(Math.toRadians(30));		
		Yocs1 = (float) 2.5*1852;
		Yocs2 = (float)(1.45*1852-((hFAP-hTHR-RDH)/Math.tan(Math.toRadians(VPA))-Xocs2)*Math.tan(Math.toRadians(30)));
		Zocs3 = (Xocs3-Xfas)*tana+hTHR;
		X30 = Xfas-(hi-h30)/tana;
		Xma = 1400;
		Xmatf = (float)apvoas.Xmatf*1852;
		Ymapt = (float)0.95*1852;
		Yma = (float)((Xma+ATT)*Math.tan(Math.toRadians(15))+Ymapt);
		Ymatf = (float)((Xmatf+ATT)*Math.tan(Math.toRadians(15))+Ymapt);
		Zmatf = (Xmatf-Xma)*ma+hTHR;
		
		Xtop = Xfas+(hOCS+hmoc-hi)/tana;
		Ytop = (float)(Ymapt+(Xtop-Xocs3)*Math.tan(Math.toRadians(30)));
		
		Xbtm = Xfas-(hi-h30)/tana;
		
		if(!para.root.getChildren().contains(meshView)){
			setAPVOAS();
		}else{
			if(meshView.isVisible()){
				meshView.setVisible(false);	
			}else{
				meshView.setVisible(true);
			}
		}
	}
	public void setRevisedAPVOAS(control.APVOAS apvoas){
		Send = apvoas.Send;
		Nend = apvoas.Nend;
		Start = apvoas.Start;
		End = apvoas.End;
		a = Math.toDegrees(Math.atan((Send[1]-Nend[1])/(Send[0]-Nend[0])));
		hTHR = (float)apvoas.THRelve;
		T = (float)apvoas.T;
		RDH = (float)apvoas.RDH;
		VPA = (float)apvoas.VPA;
		ma = (float)apvoas.ma/100;
		hFAP = (float)apvoas.aFAP*(float)0.3048;
		hOCS = (float)apvoas.OCS*(float)0.3048;
		ATT = (float)0.24*1852;
		h = (float)para.cal.getDeltaH(T, hFAP, hTHR);
		hi = 75;
		h30 = 30;
		hmoc = 150;
		tana = (hFAP-h-hi)*(float)Math.tan(Math.toRadians(VPA)/(hFAP-hi));
		Xfas = (hi-RDH)/tana+ATT;
		Xocs1 = (hFAP-hTHR-RDH)/(float)Math.tan(Math.toRadians(VPA))+(float)(2.5-1.45)*1852/(float)Math.tan(Math.toRadians(30));
		Xocs2 = Xfas+(hOCS-hTHR)/tana;
		Xocs3 = (hFAP-hTHR-RDH)/(float)Math.tan(Math.toRadians(VPA))-(float)(1.45-0.95)*1852/(float)Math.tan(Math.toRadians(30));		
		Yocs1 = (float) 2.5*1852;
		Yocs2 = (float)(1.45*1852-((hFAP-hTHR-RDH)/Math.tan(Math.toRadians(VPA))-Xocs2)*Math.tan(Math.toRadians(30)));
		Zocs3 = (Xocs3-Xfas)*tana+hTHR;
		X30 = Xfas-(hi-h30)/tana;
		Xma = 1400;
		Xmatf = (float)apvoas.Xmatf*1852;
		Ymapt = (float)0.95*1852;
		Yma = (float)((Xma+ATT)*Math.tan(Math.toRadians(15))+Ymapt);
		Ymatf = (float)((Xmatf+ATT)*Math.tan(Math.toRadians(15))+Ymapt);
		Zmatf = (Xmatf-Xma)*ma+hTHR;
		
		Xtop = Xfas+(hOCS+hmoc-hi)/tana;
		Ytop = (float)(Ymapt+(Xtop-Xocs3)*Math.tan(Math.toRadians(30)));
		
		Xbtm = Xfas-(hi-h30)/tana;
		tm.getPoints().clear();
		tm2.getPoints().clear();
		float[] points = {
				Xocs1,-hOCS,-Yocs1/2,//0
				Xocs1,-hOCS,Yocs1/2,//1
				Xocs2,-hOCS,-Yocs2/2,//2
				Xocs2,-hOCS,Yocs2/2,//3
				Xocs3,-Zocs3,-Ymapt/2,//4
				Xocs3,-Zocs3,Ymapt/2,//5
				Xfas,-hTHR,-Ymapt/2,//6
				Xfas,-hTHR,Ymapt/2,//7
				-Xma,-hTHR,-Yma/2,//8
				-Xma,-hTHR,Yma/2,//9
				-Xmatf,-Zmatf,-Ymatf/2,//10
				-Xmatf,-Zmatf,Ymatf/2,//11
				
				Xtop,-hOCS-hmoc,-Ytop,//12
				Xtop,-hOCS-hmoc,Ytop,//13
				Xocs3,-Zocs3-hi,-Yma,//14
				Xocs3,-Zocs3-hi,Yma,//15
				
				Xbtm,-hTHR-h30,-Ymapt,//16
				Xbtm,-hTHR-h30,Ymapt,//17
				ATT,-hTHR-h30,-Ymapt,//18
				ATT,-hTHR-h30,-Ymapt/2,//19
				ATT,-hTHR-h30,Ymapt/2,//20
				ATT,-hTHR-h30,Ymapt,//21
				-Xma,-hTHR-h30,-Yma,//22
				-Xma,-hTHR-h30,Yma,//23
				-Xmatf,-Zmatf-h30,-Ymatf,//24
				-Xmatf,-Zmatf-h30,Ymatf,//25		
		};
		tm.getPoints().addAll(points);
        meshView.setTranslateX(Start[0]);
        meshView.setTranslateZ(Start[1]);
        
	}
	public void setAPVOAS(){
		float[] points = {
				Xocs1,-hOCS,-Yocs1/2,//0
				Xocs1,-hOCS,Yocs1/2,//1
				Xocs2,-hOCS,-Yocs2/2,//2
				Xocs2,-hOCS,Yocs2/2,//3
				Xocs3,-Zocs3,-Ymapt/2,//4
				Xocs3,-Zocs3,Ymapt/2,//5
				Xfas,-hTHR,-Ymapt/2,//6
				Xfas,-hTHR,Ymapt/2,//7
				-Xma,-hTHR,-Yma/2,//8
				-Xma,-hTHR,Yma/2,//9
				-Xmatf,-Zmatf,-Ymatf/2,//10
				-Xmatf,-Zmatf,Ymatf/2,//11
				
				Xtop,-hOCS-hmoc,-Ytop,//12
				Xtop,-hOCS-hmoc,Ytop,//13
				Xocs3,-Zocs3-hi,-Yma,//14
				Xocs3,-Zocs3-hi,Yma,//15
				
				Xbtm,-hTHR-h30,-Ymapt,//16
				Xbtm,-hTHR-h30,Ymapt,//17
				ATT,-hTHR-h30,-Ymapt,//18
				ATT,-hTHR-h30,-Ymapt/2,//19
				ATT,-hTHR-h30,Ymapt/2,//20
				ATT,-hTHR-h30,Ymapt,//21
				-Xma,-hTHR-h30,-Yma,//22
				-Xma,-hTHR-h30,Yma,//23
				-Xmatf,-Zmatf-h30,-Ymatf,//24
				-Xmatf,-Zmatf-h30,Ymatf,//25
		};
		
		int[] face = {
				2,0,3,0,5,0,5,0,4,0,2,0,
				4,0,5,0,7,0,7,0,6,0,4,0,
				8,0,9,0,11,0,11,0,10,0,8,0,

				6,0,7,0,20,0,20,0,19,0,6,0,
				19,0,20,0,9,0,9,0,8,0,19,0}; 
		int[] face2 = {
				12,0,2,0,4,0,4,0,14,0,12,0,
				13,0,15,0,5,0,5,0,3,0,13,0,
				14,0,4,0,6,0,6,0,16,0,14,0,
				5,0,15,0,17,0,17,0,7,0,5,0,
				16,0,6,0,19,0,19,0,18,0,16,0,
				7,0,17,0,21,0,21,0,20,0,7,0,
				18,0,19,0,8,0,8,0,22,0,18,0,
				20,0,21,0,23,0,23,0,9,0,20,0,
				22,0,8,0,10,0,10,0,24,0,22,0,
				9,0,23,0,25,0,25,0,11,0,9,0}; 
		tm.getPoints().addAll(points);
		tm.getTexCoords().addAll(0,0);
		tm.getFaces().addAll(face);
		
		tm2.getPoints().addAll(points);
		tm2.getTexCoords().addAll(0,0);
		tm2.getFaces().addAll(face2);
        // マテリアルを作成
        PhongMaterial   material    = new PhongMaterial();
        PhongMaterial   material2    = new PhongMaterial();
        material.setDiffuseColor( Color.BLUE);
        material2.setDiffuseColor( Color.DEEPSKYBLUE);
        // メッシュを登録
        meshView.setDrawMode( DrawMode.FILL);
        meshView.setMesh(tm);
        meshView.setMaterial( material );        
        meshView.setCullFace(CullFace.NONE);
        meshView.setTranslateX(Start[0]);
        meshView.setTranslateZ(Start[1]);
        Rotate RotateY = new Rotate(-a, Rotate.Y_AXIS);
        meshView.getTransforms().addAll(RotateY);
        
        meshView2.setDrawMode( DrawMode.FILL);
        meshView2.setMesh(tm2);
        meshView2.setMaterial( material2 );
        meshView2.setCullFace(CullFace.NONE);
        meshView2.setTranslateX(Start[0]);
        meshView2.setTranslateZ(Start[1]);
        meshView2.getTransforms().addAll(RotateY);
        
        
        para.root.getChildren().addAll(meshView,meshView2);
	}
}
