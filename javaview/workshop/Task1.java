package workshop;

import jv.geom.PgElementSet;
import jv.geom.PgEdgeStar;
import jv.object.PsDebug;
import jv.project.PgGeometry;
import jv.vecmath.PdVector;
import jv.vecmath.PiVector;
import jvx.project.PjWorkshop;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Set;

public class Task1 extends PjWorkshop {

	PgElementSet m_geom;
	PgElementSet m_geomSave;

	public Task1() {
		super("Task1");
		init();
	}

	@Override
	public void setGeometry(PgGeometry geom) {
		super.setGeometry(geom);
		m_geom = (PgElementSet) super.m_geom;
		m_geomSave = (PgElementSet) super.m_geomSave;
	}

	public void init() {
		super.init();
	}

	public int cal_genus() {
		int V = m_geom.getNumVertices();
		int E = m_geom.getNumEdges();
		int F = m_geom.getNumElements();
		// V-E+F = 2 ( 1 - g)
		int g = (int)(2 - V + E - F) / 2;
		return g;
	}

	public double cal_volume() {
		// if (m_geo.getMaxDimOfElements() == 3 ) {
		double total_vol = 0;
		for (int i = 0; i < m_geom.getNumElements(); i++) {
			PiVector element = m_geom.getElement(i);
			 PdVector A = m_geom.getVertex(element.getEntry(0));
			 PdVector B = m_geom.getVertex(element.getEntry(1));
			 PdVector C = m_geom.getVertex(element.getEntry(2));
			 double vol = PdVector.crossNew(B,C).dot(A);
			 PdVector normal = m_geom.getElementNormal(i);
			 PdVector faceMid = new PdVector((A.getEntry(0)+B.getEntry(0)+C.getEntry(0))/3, (A.getEntry(1)+B.getEntry(1)+C.getEntry(1))/3,(A.getEntry(2)+B.getEntry(2)+C.getEntry(2))/3);

			 faceMid.normalize();
				double angle = normal.dot(faceMid);
				
				// If facing away, it is a positive volume, else it is a negative volume
				if (angle > 0) {
					total_vol += vol;
				} else {
					total_vol -= vol;
				}
			




			//double vol = element.det();
			//total_vol += vol;

		}
		// }
		return total_vol / 6;
	}

	public int cal_components() {
		int com_num = 0;
		PiVector[] neighbours = m_geom.getNeighbours();
		int current = -1;
		boolean[] have_reached = new boolean[neighbours.length];
		boolean finish_search = false;
		while (!finish_search) {

			current = reachable(have_reached);
			if(current == -1){
			boolean finish_searh = true;
			}
			if (finish_search) {
				return com_num;
			}
			com_num++;
			Stack<Integer> unconnected = new Stack<Integer>();
			unconnected.push(current);
			while (!unconnected.isEmpty()) {
				int x = unconnected.pop();
				if (!have_reached[x]) {
					have_reached[x] = true;
					for (int index : neighbours[x].getEntries()) {
						if (index >= 0)
							unconnected.push(index);
					}
				}
			}
		}
		return com_num;

	}

public int	reachable(boolean[] parameter) {
	for(int i =0;i<parameter.length;i++) {
		if(!parameter[i]) {
			return i;
		}
	}
	//finish_search = true;
	return -1;
}
}
/*
 * 
 * public void makeRandomElementColors() { //assure that the color array is
 * allocated m_geom.assureElementColors();
 * 
 * Random rand = new Random(); Color randomColor;
 * 
 * int noe = m_geom.getNumElements(); for(int i=0; i<noe; i++){ randomColor =
 * Color.getHSBColor(rand.nextFloat(), 1.0f, 1.0f);//new Color(rand.nextFloat(),
 * rand.nextFloat(), rand.nextFloat()); m_geom.setElementColor(i, randomColor);
 * } m_geom.showElementColorFromVertices(false); m_geom.showElementColors(true);
 * m_geom.showSmoothElementColors(false); }
 * 
 * public void makeRandomVertexColors() { //assure that the color array is
 * allocated m_geom.assureVertexColors();
 * 
 * Random rand = new Random(); Color randomColor;
 * 
 * int nov = m_geom.getNumVertices(); for(int i=0; i<nov; i++){ randomColor =
 * Color.getHSBColor(rand.nextFloat(), 1.0f, 1.0f); m_geom.setVertexColor(i,
 * randomColor); }
 * 
 * m_geom.showElementColors(true); m_geom.showVertexColors(true);
 * m_geom.showElementColorFromVertices(true);
 * m_geom.showSmoothElementColors(true); }
 * 
 * 
 * public void setXOff(double xOff) { int nov = m_geom.getNumVertices();
 * PdVector v = new PdVector(3); // the double array is v.m_data for (int i=0;
 * i<nov; i++) { v.copyArray(m_geomSave.getVertex(i)); v.setEntry(0,
 * v.getEntry(0)+xOff); m_geom.setVertex(i, v); } } }
 */