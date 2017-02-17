package object;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import ops.Parameter;

public class Terrain {
	public Parameter para;
	public Group terrain = new Group();
	public Terrain(Parameter para){
		this.para = para;
		setTerrain3D();
	}
	public void setTerrain3D(){	
        para.root.getChildren().addAll(terrain);
	}
}
