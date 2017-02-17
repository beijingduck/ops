package object;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import ops.Parameter;

public class Obstacle {
	public Parameter para;
	public Obstacle(Parameter para){
		this.para = para;
		setVORDME();
		setGP();
		setControlTwr();
		setWX();
		setTower();
		setStatue();
		setHotel();
		setFence();
		setLight();
		setASR();
		setHanger();
	}
	public void setVORDME(){
		double[] xy = new double[2];
		xy = para.cal.coordToXY(351537.18,1361005.58);
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
		xy = para.cal.coordToXY(351521.18,1360951.23);
		Cylinder GP = new Cylinder();
        GP.setTranslateX(xy[0]);
        GP.setTranslateZ(xy[1]);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.RED );
        GP.setRadius(2);
        GP.setHeight(117*2);
        GP.setMaterial(Material);
        para.root.getChildren().addAll(GP);
	}
	public void setControlTwr(){
		double[] xy = new double[2];
		xy = para.cal.coordToXY(351520.14,1361026.78);
		Cylinder TWR = new Cylinder();
        TWR.setTranslateX(xy[0]);
        TWR.setTranslateZ(xy[1]);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.GREY );
        TWR.setRadius(10);
        TWR.setHeight(158*2);
        TWR.setMaterial(Material);
        para.root.getChildren().addAll(TWR);
	}
	public void setWX(){
		double[] xy = new double[2];
		double r = 5;
		xy = para.cal.coordToXY(351508.32,1361002.10);
        Box base = new Box();
        base.setTranslateX(xy[0]);
        base.setTranslateZ(xy[1]);
        base.setHeight(129*2-4*r);
        base.setDepth(10);
        base.setWidth(10);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.GREY );
        base.setMaterial(Material);
        
        Sphere s = new Sphere(r);
        s.setTranslateX(xy[0]);
        s.setTranslateZ(xy[1]);
        s.setTranslateY(-129+r);
        PhongMaterial Material2 = new PhongMaterial();
        Material2.setDiffuseColor( Color.WHITE );
        s.setMaterial(Material2);
        
        para.root.getChildren().addAll(base,s);
	}
	public void setHotel(){
		double[] xy = new double[2];
		xy = para.cal.coordToXY(351545.97,1361145.66);
        Box hotel = new Box();
        hotel.setTranslateX(xy[0]);
        hotel.setTranslateZ(xy[1]);
        hotel.setHeight(235*2);
        hotel.setDepth(100);
        hotel.setWidth(30);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.ORANGE );
        hotel.setMaterial(Material);
        para.root.getChildren().addAll(hotel);
	}
	public void setTower(){
		double[] xy = new double[2];
		xy = para.cal.coordToXY(351301.56,1360649.90);
		Cylinder TWR = new Cylinder();
        TWR.setTranslateX(xy[0]);
        TWR.setTranslateZ(xy[1]);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.WHITE );
        TWR.setRadius(15);
        TWR.setHeight(155*2);
        TWR.setMaterial(Material);
        para.root.getChildren().addAll(TWR);
	}
	public void setStatue(){
		double[] xy = new double[2];
		xy = para.cal.coordToXY(351354.35,1360934.85);
		Cylinder statue = new Cylinder();
        statue.setTranslateX(xy[0]);
        statue.setTranslateZ(xy[1]);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.BROWN );
        statue.setRadius(15);
        statue.setHeight(180*2);
        statue.setMaterial(Material);
        para.root.getChildren().addAll(statue);
	}
	public void setFence(){
		double[] xy = new double[2];
		xy = para.cal.coordToXY(351513.93,1360934.49);
		Cylinder fence = new Cylinder();
        fence.setTranslateX(xy[0]);
        fence.setTranslateZ(xy[1]);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.GREY );
        fence.setRadius(15);
        fence.setHeight(106*2);
        fence.setMaterial(Material);
        para.root.getChildren().addAll(fence);
	}
	public void setLight(){
		double[] xy1 = para.cal.coordToXY(351511.50, 1360945.01);
		double[] xy2 = para.cal.coordToXY(351615.86, 1361114.10);
		double a = Math.toDegrees(Math.atan((xy1[1]-xy2[1])/(xy1[0]-xy2[0])));
		double b = Math.toDegrees(Math.atan(350.0/800.0));
		Cylinder LGT = new Cylinder();
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.GREY );
        LGT.setRadius(15);
        LGT.setHeight(132*2);
        LGT.setMaterial(Material);
        LGT.setTranslateX(xy1[0]+800*Math.cos(Math.toRadians(a-b)));
        LGT.setTranslateZ(xy1[1]+800*Math.sin(Math.toRadians(a-b)));
        para.root.getChildren().addAll(LGT);
	}
	public void setASR(){
		double[] xy1 = para.cal.coordToXY(351511.50, 1360945.01);
		double[] xy2 = para.cal.coordToXY(351615.86, 1361114.10);
		double a = Math.toDegrees(Math.atan((xy1[1]-xy2[1])/(xy1[0]-xy2[0])));
		double b = Math.toDegrees(Math.atan(500.0/1550.0));
		double r = 5;
        Box ASR = new Box();
        ASR.setTranslateX(xy1[0]+1550*Math.cos(Math.toRadians(a-b)));
        ASR.setTranslateZ(xy1[1]+1550*Math.sin(Math.toRadians(a-b)));
        ASR.setHeight(163*2-r*4);
        ASR.setDepth(10);
        ASR.setWidth(10);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.WHITE);
        ASR.setMaterial(Material);
        
        Sphere s = new Sphere(r);
        s.setTranslateX(xy1[0]+1550*Math.cos(Math.toRadians(a-b)));
        s.setTranslateZ(xy1[1]+1550*Math.sin(Math.toRadians(a-b)));
        s.setTranslateY(-163+r);
        PhongMaterial Material2 = new PhongMaterial();
        Material2.setDiffuseColor( Color.RED );
        s.setMaterial(Material2);

        para.root.getChildren().addAll(ASR,s);
	}	
	public void setHanger(){
		double[] xy1 = para.cal.coordToXY(351511.50, 1360945.01);
		double[] xy2 = para.cal.coordToXY(351615.86, 1361114.10);
		double a = Math.toDegrees(Math.atan((xy1[1]-xy2[1])/(xy1[0]-xy2[0])));
		double b = Math.toDegrees(Math.atan(400.0/2000.0));
        Box hanger = new Box();
        hanger.setHeight(163*2);
        hanger.setDepth(30);
        hanger.setWidth(30);
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor( Color.BLACK );
        hanger.setMaterial(Material);
        hanger.setTranslateX(xy1[0]+2000*Math.cos(Math.toRadians(a-b)));
        hanger.setTranslateZ(xy1[1]+2000*Math.sin(Math.toRadians(a-b)));        
        para.root.getChildren().addAll(hanger);
	}
	
}
