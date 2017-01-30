package ops;

import javafx.geometry.Point2D;

public class Calculation {
	double dev,scale,angle;
	double lat1,lng1,DO1,HUN1,BYO1,DO2,HUN2,BYO2,a,f,l,L,u1,u2,xi,xid,eta,etad,x,y,epsilon,theta0,theta,sigma,J,K,
	gamma,GAMMA,zeta,zetad,G,H,g,h,D,E,F,alpha,alphad,alhpa1,alpha1,alphad1,alpha2,alphad21,n0,A,B,s;
	double a1,an1,ang1,angle1;
	int alpha1d,sd,size;
	double[] disdir= new double[2];
	double [] XY = new double[2];
	public Parameter para;
	public Calculation(Parameter para){
		this.para = para;
	}
	public double[] dirdisToXY(double[] disdir) {
		angle = disdir[0] + para.variation;
		XY[0] = Math.sin(Math.toRadians(angle)) * disdir[1] / para.scale;
		XY[1] = Math.cos(Math.toRadians(angle)) * disdir[1] / para.scale;
		return XY;
	}
	public Point2D dirdisToXY(double dis,double dir) {
		angle = dir + para.variation;
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
		disdir[0] = alpha1;
		disdir[1] = s;
		return disdir;
	}
}
