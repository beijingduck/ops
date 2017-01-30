package ops;

import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class FileRoader {
	public Parameter para;
	double [] latlngNW = new double[2];
	public DisAndDir dd;	
	public FileRoader(Parameter para){
		this.para = para;
		dd = new DisAndDir(para);
	}
	public void road(String file){
		try {
		    DOMParser parser = new DOMParser(); // パーサの生成
		    parser.parse(file);                 // XMLファイルのパース

		    Document document = parser.getDocument();   // DOCUMENTノードの取得
		    traceNodes(document);

		  } catch (SAXException e) {		
		      System.out.println("XMLデータが不正です。");
		      e.printStackTrace();
		  } catch (IOException e) {			
		      e.printStackTrace();
		  }
	}
	public void traceNodes(Node node){
		Node child = node.getFirstChild();
		while (child!=null){
			printTextNode(child);
			traceNodes(child);
			child = child.getNextSibling();
		}
	}
	public void printTextNode(Node node){
		if(node.getNodeName().equals("gml:lowerCorner")){
			String[] latlng1 = node.getFirstChild().getNodeValue().split(" ", -1);
			latlngNW[1] = Double.parseDouble(latlng1[1]);			
		}else if(node.getNodeName().equals("gml:upperCorner")){
			String[] latlng2 = node.getFirstChild().getNodeValue().split(" ", -1);
			latlngNW[0] = Double.parseDouble(latlng2[0]);
		}else if(node.getNodeName().equals("gml:tupleList")){			
			String[] H = node.getFirstChild().getNodeValue().split("\nその他,",-1);		//H[0]=space
			
			dd.XandYandZofCell(latlngNW[0],latlngNW[1],H);
		}
	}
}
