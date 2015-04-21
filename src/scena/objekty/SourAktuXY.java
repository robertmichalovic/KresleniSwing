package scena.objekty;
public final class SourAktuXY {			//	pouze staticka trida - zajistuje souradnice - navrhovy vzor utility servant 
	private SourAktuXY() {}									//	privatni konstruktor - nelze vytvorit objektu
	private static int X,Y;									//	souradnice X,Y .... z polohy	
	public static int getX() {	return X;	}				//	getter X
	public static void setX( int x) { X=x;	}				//	setter X
	public static int getY() {	return Y;	}				//	getter Y
	public static void setY(int y) {	Y = y;	}			//	setter Y
}
