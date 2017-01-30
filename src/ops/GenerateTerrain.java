package ops;

import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.shape.TriangleMesh;

public class GenerateTerrain {
	public int dimensionX=200,dimensionY=200;
	public GenerateTerrain(){		
	}
	
	public TriangleMesh generateTerrain(int dimension, int scale){  
		float[][] generatedYValues = generateYValues();  
		return createTerrainMesh(scale,generatedYValues);  
	}  
	private float[][] generateYValues() {  		
		float[][] generatedYValues = new float[dimensionX][dimensionY];
		for (int x = 0; x < dimensionX; x++) {
			for (int z = 0; z < dimensionY; z++) {
				Random rnd = new Random();
				int ran = rnd.nextInt(5)+100;
				generatedYValues[x][z] = -ran;
			}
		}  
		return generatedYValues;  
	}  
	private TriangleMesh createTerrainMesh(float scale, float[][] generatedYValues) {  
	ObservableFloatArray points = FXCollections.observableFloatArray();  
		ObservableIntegerArray faces = FXCollections.observableIntegerArray();  
		Integer[][] vertexID = new Integer[dimensionX][dimensionY];  
		int ctr = 0;
		for (int x = 0; x < dimensionX; x++) {  
			for (int z = 0; z < dimensionY; z++) { 
			float tmpX = x * scale;  
			float tmpY = generatedYValues[x][z] * scale;  
			float tmpZ = z * scale;  
			if (z + 1 < dimensionY && x + 1 < dimensionX) {  
				//current  
				Integer vCurrent = vertexID[x][z];  
				Integer vDown = vertexID[x][z + 1];  
				Integer vRight = vertexID[x + 1][z];  
				if (vCurrent == null) {  
				points.addAll(tmpX);  
				points.addAll(tmpY);  
				points.addAll(tmpZ);  
				vertexID[x][z] = ctr++;  
				vCurrent = vertexID[x][z]; 
				}  
				if (vDown == null) {  
				//point above  
				points.addAll(tmpX);  
				points.addAll(generatedYValues[x][z + 1] * scale);  
				points.addAll(tmpZ + scale);  
				vertexID[x][z + 1] = ctr++;  
				vDown = vertexID[x][z + 1];  
				}  
				if (vRight == null) {  
				//point to the right  
				points.addAll(tmpX + scale);  
				points.addAll(generatedYValues[x + 1][z] * scale);  
				points.addAll(tmpZ);  
				vertexID[x + 1][z] = ctr++;  
				vRight = vertexID[x + 1][z];  
				} 
				faces.addAll(vCurrent);  
				faces.addAll(0);  
				faces.addAll(vDown);  
				faces.addAll(0);  
				faces.addAll(vRight);  
				faces.addAll(0);  
				} 
			if (z - 1 >= 0 && x - 1 >= 0) {  
			Integer vCurrent = vertexID[x][z];  
			Integer vUp = vertexID[x][z - 1];  
			Integer vLeft = vertexID[x - 1][z];  
				if (vCurrent == null) {  
				//current  
				points.addAll(tmpX);  
				points.addAll(tmpY);  
				points.addAll(tmpZ);  
				vertexID[x][z] = ctr++;  
				vCurrent = vertexID[x][z]; 
				}  
				if (vUp == null) {  
				//point to the left  
				points.addAll(tmpX - scale);  
				points.addAll(generatedYValues[x - 1][z] * scale);  
				points.addAll(tmpZ);  
				vertexID[x][z - 1] = ctr++;  
				vUp = vertexID[x][z - 1];  
				}  
				if (vLeft == null) {  
				//point below  
				points.addAll(tmpX);  
				points.addAll(generatedYValues[x][z - 1] * scale);  
				points.addAll(tmpZ - scale);  
				vertexID[x - 1][z] = ctr++;  
				vLeft = vertexID[x - 1][z];  
				}
				faces.addAll(vCurrent);  
				faces.addAll(0);  
				faces.addAll(vUp);  
				faces.addAll(0);  
				faces.addAll(vLeft);  
				faces.addAll(0); 
				}  
			}  
		}
		TriangleMesh mesh = new TriangleMesh();
		mesh.getPoints().addAll(points);  
		mesh.getFaces().addAll(faces);  
		return mesh;  
		}  
}
