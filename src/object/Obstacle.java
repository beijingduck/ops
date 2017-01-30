package object;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import ops.Parameter;

public class Obstacle {
	public Parameter para;
	public Obstacle(Parameter para){
		this.para = para;
		setVORDME();
		setGP();
		setTwr();
		setWX();
	}
	public void setVORDME(){
		double[] xy = new double[2];
		xy = para.cal.dirdisToXY(para.cal.coordToDirdis(351537.18,1361005.58));
		Cylinder WXE = new Cylinder();
        WXE.setTranslateX(xy[0]);
        WXE.setTranslateZ(xy[1]);
        WXE.setTranslateY(-100-5/2);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.RED );
        WXE.setRadius(15);
        WXE.setHeight(5);
        WXE.setMaterial(Material);
        para.root.getChildren().addAll(WXE);
	}
	public void setGP(){
		double[] xy = new double[2];
		xy = para.cal.dirdisToXY(para.cal.coordToDirdis(351521.18,1360951.23));
		Cylinder GP = new Cylinder();
        GP.setTranslateX(xy[0]);
        GP.setTranslateZ(xy[1]);
        GP.setTranslateY(-117/2);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.RED );
        GP.setRadius(2);
        GP.setHeight(117);
        GP.setMaterial(Material);
        para.root.getChildren().addAll(GP);
	}
	public void setTwr(){
		double[] xy = new double[2];
		xy = para.cal.dirdisToXY(para.cal.coordToDirdis(351520.14,1361026.78));
		Cylinder TWR = new Cylinder();
        TWR.setTranslateX(xy[0]);
        TWR.setTranslateZ(xy[1]);
        TWR.setTranslateY(-158/2);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.GREY );
        TWR.setRadius(10);
        TWR.setHeight(158);
        TWR.setMaterial(Material);
        para.root.getChildren().addAll(TWR);
	}
	public void setWX(){
		double[] xy = new double[2];
		xy = para.cal.dirdisToXY(para.cal.coordToDirdis(351508.32,1361002.10));
		Sphere  WX = new Sphere(5);
        WX.setTranslateX(xy[0]);
        WX.setTranslateZ(xy[1]);
        WX.setTranslateY(-129);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.WHITE );
        WX.setMaterial(Material);
        Box base = new Box();
        base.setTranslateX(xy[0]);
        base.setTranslateZ(xy[1]);
        base.setTranslateY(-129/2);
        base.setHeight(129);
        base.setDepth(10);
        PhongMaterial Material2 = new PhongMaterial();
        Material.setDiffuseColor( Color.GREY );
        WX.setMaterial(Material2);
        
        para.root.getChildren().addAll(base,WX);
	}
}
