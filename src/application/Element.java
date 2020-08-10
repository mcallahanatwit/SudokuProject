package application;

public class Element extends Grid {

	private String id;
	private int [] unassigned;
	private int [] counting = {1,2,3,4,5,6,7,8,9};
	private int[] xRange;
	private int[] yRange;
	
	/*
	 * Constructs element square element
	 * X corresponding to horizontal, beginning in left at 0 increasing to 8 at furthest right
	 * Y corresponding to vertical, beginning in top at 0 increasing to 8 at furthest bottom
	 */
	public Element(String iden, int [] xRange, int [] yRange) {
	
		this.id = iden;
		this.unassigned = counting;
		this.xRange=xRange;
		this.yRange=yRange;
	}
	/*
	 * Constructs row or column element depending on boolean
	 * true constructs row, false constructs column
	 * range corresponds to the element number-1 i.e
	 * R4 uses 4, C2 uses 2 as range
	 */
	public Element(String iden, int range, boolean isRow) {
		int[] rangeLimits = {0,8};
		int[] hold = {range,range};
		this.id=iden;
		this.unassigned = counting;
		
		if (isRow) {
			this.xRange = rangeLimits;
			this.yRange = hold;
		}
		else {
			this.yRange = rangeLimits;
			this.xRange = hold;
		}
		
	}
	
	public String getId() {
		return this.id;
	}
	public int[] getUnassigned() {
		return this.unassigned;
	}
	public int[] getxRange() {
		return this.xRange;
	}
	public int[] getyRange() {
		return this.yRange;
	}
	
	public static Element[] genColumns() {
		Element[] columns = new Element[9];
		columns[0] = new Element("C1",0,false);
		columns[1] = new Element("C2",1,false);
		columns[2] = new Element("C3",2,false);
		columns[3] = new Element("C4",3,false);
		columns[4] = new Element("C5",4,false);
		columns[5] = new Element("C6",5,false);
		columns[6] = new Element("C7",6,false);
		columns[7] = new Element("C8",7,false);
		columns[8] = new Element("C9",8,false);
		return columns;
	}
	
	public static Element[] genRows() {
		Element[] rows = new Element[9];
		rows[0] = new Element("R1",0,true);
		rows[1] = new Element("R2",1,true);
		rows[2] = new Element("R3",2,true);
		rows[3] = new Element("R4",3,true);
		rows[4] = new Element("R5",4,true);
		rows[5] = new Element("R6",5,true);
		rows[6] = new Element("R7",6,true);
		rows[7] = new Element("R8",7,true);
		rows[8] = new Element("R9",8,true);
		return rows;
	}
	
	public static Element[] genGrids() {
		Element[] grids = new Element[9];
		grids[0] = new Element("G1", new int []{0,2},new int [] {0,2});
		grids[1] = new Element("G2", new int []{3,5},new int [] {0,2});
		grids[2] = new Element("G3", new int []{6,8},new int [] {0,2});
		grids[3] = new Element("G4", new int []{0,2},new int [] {3,5});
		grids[4] = new Element("G5", new int []{3,5},new int [] {3,5});
		grids[5] = new Element("G6", new int []{6,8},new int [] {3,5});
		grids[6] = new Element("G7", new int []{0,2},new int [] {6,8});
		grids[7] = new Element("G8", new int []{3,5},new int [] {6,8});
		grids[8] = new Element("G9", new int []{6,8},new int [] {6,8});
		return grids;
	}
	
	/*
	 * Corresponds coordinate point to correct Grid Row and Column elements
	 * enter xyCoord returns ID tags
	 */
	public static Element[] asscElements(int [] xyCoord, Element[] columns, Element[] rows, Element[] grids) {
		int x = xyCoord[0];
		int y = xyCoord[1];
		Element[] crgEle = new Element[3];		
		//find column element
		for (Element s : columns) {
			if(s.getxRange()[0] == x) {
				crgEle[0] = s;
			}
		}
		//find row
		for (Element d: rows) {
			if(d.getyRange()[1] == y) {
				crgEle[1] = d;
			}
		}
		//find grid
		for(Element f: grids) {
			if(f.getxRange()[0]<= x && x <=f.getxRange()[1] && f.getyRange()[0]<= y && y <=f.getyRange()[1]) {
				crgEle[2] = f;
			}
		}
		return crgEle;
		
		
	}
	
	public static boolean inUnassigned(Element ele, int value) {
		for(int s : ele.unassigned) {
			if (value == s) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean validGrid(int value, Element[] crgEle) {				
		
		if (inUnassigned(crgEle[0],value) && inUnassigned(crgEle[1],value) && inUnassigned(crgEle[2],value)) {
			return true;
		}
		else {
			return false;
		}			
	}
	
	public static void updateUnassigned(Element[] crgEle, int value) {
		int[] count = {1,2,3,4,5,6,7,8,9};
		int index=-1;
		for (int d : count) {
			if (d == value) {
				index = d-1;
			}
		}
		Element column = crgEle[0];		
		Element row = crgEle[1];
		Element grid = crgEle[2];
		
		for (Element s : crgEle) {
			s.unassigned[index] = 0;
		}
	}
	
	public static void addNumber(int[] xyPoint, int value, Element[] columns, Element[] rows, Element[] grids) {
		Element[] crgEle = asscElements(xyPoint, columns,rows,grids);
		 
		 if(!validGrid(value,crgEle)){
			 System.out.println("Invalid Entry");
			 System.exit(0);
		 }
		 updateUnassigned(crgEle,value);
		 System.out.printf("(%d,%d) value %d successfully added%n",xyPoint[0],xyPoint[1],value);
	}
	
	public static void test() {
		
		 Element[] columns = genColumns();
		 Element[] rows = genRows();
		 Element[] grids = genGrids();
		 
		 addNumber(new int[] {2,0},3,columns,rows,grids);
		 
		 addNumber(new int[] {0,0},3,columns,rows,grids);
		 
		 addNumber(new int[] {1,0},2,columns,rows,grids);
		 
		 addNumber(new int[] {0,1},1,columns,rows,grids);
	
		 addNumber(new int[] {8,1},9,columns,rows,grids);
		 
		 //columns give rows and rows give columns
		 for (int s : columns[0].unassigned) {
			 System.out.println(s);
		 }
		 
		 
		 
		
	
	
	}
}
