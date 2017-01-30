package ops;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.geometry.Point2D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Start extends Application {
	public Parameter para;
	public AnchorPane bottom;
	public AnchorPane ap;
	public Group root;
	public Scene scene;
	public PerspectiveCamera camera;
	public GenerateTerrain gt = new GenerateTerrain();
	public MeshView meshView2 = new MeshView();
    ObservableFloatArray points = FXCollections.observableFloatArray();
    ObservableIntegerArray faces = FXCollections.observableIntegerArray();
	public static void main(String[] args) {		
		launch(args);		
	}

	public void start(Stage stage) throws Exception {
		para = new Parameter(stage);
	}

	public void setBackGround() {
        Box             ground      = new Box();
        PhongMaterial   material    = new PhongMaterial();
        material.setDiffuseColor( Color.web( "9FCC7F" ) );
        ground.setWidth( 500 );
        ground.setHeight( 0.5 );
        ground.setDepth( 500 );
        ground.setMaterial( material );
		//ground.setTranslateX(twr.getX());
		//ground.setTranslateY(twr.getY());
        para.root.getChildren().add( ground );
	}
	
}
