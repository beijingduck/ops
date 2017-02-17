package ops;

public class DisAndDir {
	public Parameter para;
	double [] xy = new double[2];
	double[] latlng= new double[2];
	public DisAndDir(Parameter para){
		this.para = para;
	}
	//lat,lngを始点とし、dataにx,y,zの値を格納する
	public void XandYandZofCell(double lat, double lng,String[] H){
		latlng[0] = lat;
		latlng[1] = lng;
		para.cntl.road.data.clear();
		for(int n=0;n<para.dimensionZ;n++){		//750&1125
			for(int i=0;i<para.dimensionX;i++){				
				xy = XandYofCell(i,n);
				para.cntl.road.data.add(new XYZ(xy[0],xy[1],Double.parseDouble(H[n*1125+i+1])));
			}
		}
	}
	public double[] XandYofCell(int i,int n){
		//start from northwest corner
		//center of box
		double lat = latlng[0] - n*1.0/9000.0;
		double lng = latlng[1] + i*1.0/9000.0;
		return para.cal.coordToXY(lat, lng);
	}
}