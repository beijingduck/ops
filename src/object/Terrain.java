package object;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import ops.Parameter;

public class Terrain {
	public Parameter para;
	public MeshView meshView = new MeshView();
	public Terrain(Parameter para){
		this.para = para;
	}
	public void setTerrain(TriangleMesh mesh){
        PhongMaterial   material    = new PhongMaterial();
        material.setDiffuseColor( Color.web( "#00FF00" ) );
        material.setSpecularColor( Color.GREEN );
        meshView.setDrawMode( DrawMode.LINE );
        meshView.setMesh( mesh );
        meshView.setMaterial( material );
        meshView.setCullFace(CullFace.NONE);
        para.root.getChildren().addAll(meshView);
	}
}
