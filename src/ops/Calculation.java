package ops;

import javafx.geometry.Point2D;

public class Calculation {
	double dev,scale,angle;
	double lat1,lng1,DO1,HUN1,BYO1,DO2,HUN2,BYO2,a,f,l,L,u1,u2,xi,xid,eta,etad,x,y,epsilon,theta0,theta,sigma,J,K,
	gamma,GAMMA,zeta,zetad,G,H,g,h,D,E,F,alpha,alphad,alhpa1,alpha1,alphad1,alpha2,alphad21,n0,A,B,s;
	double a1,an1,ang1,angle1;
	int alpha1d,sd,size;	
	public Parameter para;
	public Calculation(Parameter para){
		this.para = para;
	}
	public double[] dirdisToXY(double[] disdir) {
		angle = disdir[0] + para.variation;
		double [] XY = new double[2];	
		XY[0] = Math.sin(Math.toRadians(angle)) * disdir[1] / para.scale;
		XY[1] = Math.cos(Math.toRadians(angle)) * disdir[1] / para.scale;
		return XY;
	}
	public Point2D dirdisToXY(double dis,double dir) {
		angle = dir + para.variation;
		double [] XY = new double[2];
		XY[0] = Math.sin(Math.toRadians(angle)) * dis / para.scale;
		XY[1] = Math.cos(Math.toRadians(angle)) * dis / para.scale;
		Point2D Pos = new Point2D(XY[0],XY[1]);
		return Pos;
	}
	public double[] coordToDirdis(double lat2, double lng2) {
		if(lat2>1000){
			DO1 = (double) ((int) (lat2 / 10000.0D));
			HUN1 = (double) ((int) (lat2 / 100.0D - DO1 * 100.0D));
			BYO1 = lat2 - DO1 * 10000.0D - HUN1 * 100.0D;
			lat2 = DO1 + HUN1 / 60.0D + BYO1 / 60.0D / 60.0D;
			DO2 = (double) ((int) (lng2 / 10000.0D));
			HUN2 = (double) ((int) (lng2 / 100.0D - DO2 * 100.0D));
			BYO2 = lng2 - DO2 * 10000.0D - HUN2 * 100.0D;
			lng2 = DO2 + HUN2 / 60.0D + BYO2 / 60.0D / 60.0D;
		}

		lat1 = para.centerLat;
		lng1 = para.centerLng;
		a = 6378137.0D;
		f = 0.003352810681182319D;
		l = lng2 - lng1;
		L = Math.abs(l);
		if (l >= 0.0D) {
			u1 = Math.toDegrees(Math.atan((1.0D - f) * Math.tan(Math.toRadians(lat1))));
			u2 = Math.toDegrees(Math.atan((1.0D - f) * Math.tan(Math.toRadians(lat2))));
		} else {
			u1 = Math.toDegrees(Math.atan((1.0D - f) * Math.tan(Math.toRadians(lat2))));
			u2 = Math.toDegrees(Math.atan((1.0D - f) * Math.tan(Math.toRadians(lat1))));
		}

		xi = Math.cos(Math.toRadians((u1 + u2) / 2.0D));
		xid = Math.sin(Math.toRadians((u1 + u2) / 2.0D));
		eta = Math.sin(Math.toRadians((u2 - u1) / 2.0D));
		etad = Math.cos(Math.toRadians((u2 - u1) / 2.0D));
		x = Math.sin(Math.toRadians(u1)) * Math.sin(Math.toRadians(u2));
		y = Math.cos(Math.toRadians(u1)) * Math.cos(Math.toRadians(u2));
		epsilon = f * (2.0D - f) / ((1.0D - f) * (1.0D - f));
		theta0 = L * (1.0D + f * y);
		theta = theta0;
		sigma = 0.0D;
		J = 0.0D;
		K = 0.0D;
		gamma = 0.0D;
		GAMMA = 0.0D;
		zeta = 0.0D;
		zetad = 0.0D;
		for (F = 1.0D; Math.abs(F) >= 1.0E-15D; theta -= F / (1.0D - G)) {
			g = Math.sqrt(
					eta * eta * Math.cos(Math.toRadians(theta / 2.0D)) * Math.cos(Math.toRadians(theta / 2.0D)) + xi
							* xi * Math.sin(Math.toRadians(theta / 2.0D)) * Math.sin(Math.toRadians(theta / 2.0D)));
			h = Math.sqrt(
					etad * etad * Math.cos(Math.toRadians(theta / 2.0D)) * Math.cos(Math.toRadians(theta / 2.0D)) + xid
							* xid * Math.sin(Math.toRadians(theta / 2.0D)) * Math.sin(Math.toRadians(theta / 2.0D)));
			sigma = 2.0D * Math.toDegrees(Math.atan(g / h));
			J = 2.0D * g * h;
			K = h * h - g * g;
			gamma = y * Math.sin(Math.toRadians(theta)) / J;
			GAMMA = 1.0D - gamma * gamma;
			zeta = GAMMA * K - 2.0D * x;
			zetad = zeta + x;
			D = 0.0D * f * (1.0D + f) - 0.0D * f * f * GAMMA;
			E = (1.0D - D * GAMMA) * f * gamma
					* (sigma + D * J * (zeta + D * K * (2.0D * zeta * zeta - GAMMA * GAMMA)));
			F = theta - L - E;
			G = f * gamma * gamma * (1.0D - 2.0D * D * GAMMA)
					+ f * zetad * (sigma / J) * (1.0D - D * GAMMA + f * gamma * gamma / 2.0D)
					+ f * f * zeta * zetad / 4.0D;
		}

		alpha = Math.toDegrees(Math.atan(xi * Math.tan(Math.toRadians(theta / 2.0D)) / eta));
		if (alpha < 0.0D && L > 0.0D) {
			alphad = alpha + 180.0D;
		} else {
			alphad = alpha;
		}

		alphad1 = alphad - Math.toDegrees(Math.atan(xid * Math.tan(Math.toRadians(theta / 2.0D)) / etad));
		alpha2 = alphad + Math.toDegrees(Math.atan(xid * Math.tan(Math.toRadians(theta / 2.0D)) / etad));
		alphad21 = 180.0D + alpha2;
		if (l >= 0.0D) {
			alpha1 = alphad1;
		} else {
			alpha1 = alphad21;
		}

		n0 = epsilon * GAMMA
				/ ((Math.sqrt(1.0D + epsilon * GAMMA) + 1.0D) * (Math.sqrt(1.0D + epsilon * GAMMA) + 1.0D));
		A = (1.0D + n0) * (1.0D + 1.0D * n0 * n0);
		B = epsilon * (1.0D - 0.0D * n0 * n0)
				/ ((Math.sqrt(1.0D + epsilon * GAMMA) + 1.0D) * (Math.sqrt(1.0D + epsilon * GAMMA) + 1.0D));
		s = (1.0D - f) * a * A
				* (Math.toRadians(sigma) - B * J * (zeta - 0.0D * B * (K * (GAMMA * GAMMA - 2.0D * zeta * zeta)
						- 0.0D * B * zeta * (1.0D - 4.0D * K * K) * (3.0D * GAMMA * GAMMA - 4.0D * zeta * zeta))));
		alpha1d = (int) (alpha1 * 10000.0D);
		alpha1 = (double) alpha1d / 10000.0D;
		sd = (int) (s * 100.0D);
		s = (double) sd / 100.0D;
		double[] disdir= new double[2];
		disdir[0] = alpha1;
		disdir[1] = s;
		return disdir;
	}
	public double[] coordToXY(double lat, double lng){
		double[] XY= new double[2];
		if(lat>1000){
			DO1 = (double) ((int) (lat / 10000.0D));
			HUN1 = (double) ((int) (lat / 100.0D - DO1 * 100.0D));
			BYO1 = lat - DO1 * 10000.0D - HUN1 * 100.0D;
			lat = DO1 + HUN1 / 60.0D + BYO1 / 60.0D / 60.0D;
			DO2 = (double) ((int) (lng / 10000.0D));
			HUN2 = (double) ((int) (lng / 100.0D - DO2 * 100.0D));
			BYO2 = lng - DO2 * 10000.0D - HUN2 * 100.0D;
			lng = DO2 + HUN2 / 60.0D + BYO2 / 60.0D / 60.0D;
		}
		
		double phi0RAD=Math.toRadians(36);//ラジアンにした原点の緯度
		double rmd0RAD=Math.toRadians(136);//ラジアンにした原点の経度

		//ターゲット
		double phiRAD=Math.toRadians(lat);//ラジアンにした緯度
		double rmdRAD=Math.toRadians(lng);//ラジアンにした緯度
		
		double a,f,F,b,m0,e,et,W,N,A,B,C,I,B1,B2,B3,B4,B5,B6,B7,B8,B9,S,S0,drmd,nyu2,t,x0,x1,x2,x3,y0,y1,y2,y3;
		//パラメータ（世界測地系）
		a=6378137; //長半径
		f=1/298.257224;//扁平率
		F=298.257224;//逆扁平率

		b=a*(1-f);//短半径

		//座標系
		m0=0.9999;//座標系の原点における縮尺係数

		//第一離心率を求める
		e=Math.sqrt((Math.pow(a,2)-Math.pow(b,2))/Math.pow(a,2));//第一離心率
		//第二離心率を求める
		et=Math.sqrt((Math.pow(a,2)-Math.pow(b,2))/Math.pow(b,2));//第二離心率

		//楕円形の公式
		//パラメータ
		W=Math.sqrt(1-(Math.pow(e,2)*Math.pow(Math.sin(phiRAD),2)));

		//卯酉線曲率半径の計算
		N=a/W;

		//緯度を与えて赤道からの子午線弧長を求める計算
		//パラメータ演算
			A=1+(double)3/4*Math.pow(e,2)+(double)45/64*Math.pow(e,4)+(double)11025/16384*Math.pow(e,8)+(double)43659/65536*Math.pow(e,10)+(double)693693/1048576*Math.pow(e,12)+(double)19324305/29360128*Math.pow(e,14)+(double)4927697775L/7516192768L*Math.pow(e,16);
			B=(double)3/4*Math.pow(e,2)+(double)15/16*Math.pow(e,4)+(double)525/512*Math.pow(e,6)+(double)2205/2048*Math.pow(e,8)+(double)72765/65536*Math.pow(e,10)+(double)297297/262144*Math.pow(e,12)+(double)135270135/117440512*Math.pow(e,14)+(double)547521975/469762048*Math.pow(e,16);
			C=(double)15/64*Math.pow(e,4)+(double)105/256*Math.pow(e,6)+(double)2205/4096*Math.pow(e,8)+(double)10395/16384*Math.pow(e,10)+(double)1486485/2097152*Math.pow(e,12)+(double)45090045/58720256*Math.pow(e,14)+(double)766530765/939524096*Math.pow(e,16);
			D=(double)35/512*Math.pow(e,6)+(double)315/2048*Math.pow(e,8)+(double)31185/131072*Math.pow(e,10)+(double)165165/524288*Math.pow(e,12)+(double)45090045/117440512*Math.pow(e,14)+(double)209053845/469762048*Math.pow(e,16);
			E=(double)315/16384*Math.pow(e,8)+(double)3465/65536*Math.pow(e,10)+(double)99099/1048576*Math.pow(e,12)+(double)4099095/29360128*Math.pow(e,14)+(double)348423075/1879048192*Math.pow(e,16);
			F=(double)693/131072*Math.pow(e,10)+(double)9009/524288*Math.pow(e,12)+(double)4099095/117440512*Math.pow(e,14)+(double)26801775/469762048*Math.pow(e,16);
			G=(double)3003/2097152*Math.pow(e,12)+(double)315315/58720256*Math.pow(e,14)+(double)11486475/939524096*Math.pow(e,16);
			H=(double)45045/117440512*Math.pow(e,14)+(double)765765/469762048*Math.pow(e,16);
		I=765765/7516192768L*Math.pow(e,16);		

			B1=a*(1-Math.pow(e,2))*A;
			B2=a*(1-Math.pow(e,2))*(-B/2);
			B3=a*(1-Math.pow(e,2))*(C/4);
			B4=a*(1-Math.pow(e,2))*(-D/6);
			B5=a*(1-Math.pow(e,2))*(E/8);
			B6=a*(1-Math.pow(e,2))*(-F/10);
			B7=a*(1-Math.pow(e,2))*(G/12);
			B8=a*(1-Math.pow(e,2))*(-H/14);
			B9=a*(1-Math.pow(e,2))*(I/16);

		S=(B1*phiRAD)+B2*Math.sin(2*phiRAD)+B3*Math.sin(4*phiRAD)+B4*Math.sin(6*phiRAD)+B5*Math.sin(8*phiRAD)+B6*Math.sin(10*phiRAD)+B7*Math.sin(12*phiRAD)+B8*Math.sin(14*phiRAD)+B9*Math.sin(16*phiRAD);//子午線弧長

		//赤道から座標系の原点の緯度phi0までの子午線弧長
		S0=(B1*phi0RAD)+B2*Math.sin(2*phi0RAD)+B3*Math.sin(4*phi0RAD)+B4*Math.sin(6*phi0RAD)+B5*Math.sin(8*phi0RAD)+B6*Math.sin(10*phi0RAD)+B7*Math.sin(12*phi0RAD)+B8*Math.sin(14*phi0RAD)+B9*Math.sin(16*phi0RAD);//原点の子午線弧長
		
		//縮尺係数の計算
		drmd=rmdRAD-rmd0RAD;
		nyu2=Math.pow(et,2)*Math.pow(Math.cos(phiRAD),2);
		t=Math.tan(phiRAD);

		//x座標の計算
		//パラメータ
		x0=(S-S0)+(double)1/2*N*Math.pow(Math.cos(phiRAD),2)*t*Math.pow(drmd,2);
		x1=(double)1/24*N*Math.pow(Math.cos(phiRAD),4)*t*(5-Math.pow(t,2)+9*nyu2+4*Math.pow(nyu2,4))*Math.pow(drmd,4);
		x2=-(double)1/720*N*Math.pow(Math.cos(phiRAD),5)*t*(-61+58*Math.pow(t,2)-Math.pow(t,4)-270*nyu2+330*Math.pow(t,2)*nyu2)*(Math.pow(drmd,6));
		x3=-(double)1/40320*N*Math.pow(Math.cos(phiRAD),8)*t*(-1385+3111*Math.pow(t,2)-543*Math.pow(t,4)+Math.pow(t,6))*(Math.pow(drmd,8));
		//
		XY[1]=(x0+x1+x2+x3)*m0;

		//y座標の計算
		//パラメータ
		y0=N*Math.cos(phiRAD)*drmd;
		y1=-(double)1/6*N*Math.pow(Math.cos(phiRAD),3)*(-1+Math.pow(t,2)-nyu2)*(Math.pow(drmd,3));
		y2=-(double)1/120*N*Math.pow(Math.cos(phiRAD),5)*(-5+18*Math.pow(t,2)-Math.pow(t,4)-14*nyu2+58*Math.pow(t,2)*nyu2)*(Math.pow(drmd,5));
		y3=-(double)1/5040*N*Math.pow(Math.cos(phiRAD),7)*(-61+479*Math.pow(t,2)-179*Math.pow(t,4)*Math.pow(t,6))*(Math.pow(drmd,7));
		//
		XY[0]=(y0+y1+y2+y3)*m0;
		//座標系のX軸は、原点において子午線に一致する軸とし真北に向かう値を正とし、座標系のY軸は、座標系原点において座標系のX軸に直交する軸とし真東に向かう値を正とする。
		return XY;
	}
	public double getDeltaH(double T,double hFAP,double hTHR){
		double h;
		double Tstd = T-15;
		double L0 = -0.0065;
		double T0 = 288.15;
		//System.out.println(-(Tstd/L0)*Math.log(1+L0*hFAP/(T0+L0*hTHR)));
		//System.out.println(-(Tstd/L0)*Math.log(1+L0*hFAP/(T0+L0*hTHR)));
		
		h = -(Tstd/L0)*Math.log(1+L0*hFAP/(T0+L0*hTHR));
		return h;		
	}
}
