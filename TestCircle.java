import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Circle {
	private double centerX, centerY;
    private double radius;
    
    private static int decPlaces = 2;
    
    public Circle(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }
    
    public Circle(double x1, double y1, double x2, double y2) {
        this.centerX = (x1+x2)/2;
        this.centerY = (y1+y2)/2;
        this.radius = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }

    public Circle(double x1, double y1, double x2, double y2, double x3, double y3) {

        double x12 = x1 - x2;
        double x13 = x1 - x3;

        double y12 = y1 - y2;
        double y13 = y1 - y3;

        double y31 = y3 - y1;
        double y21 = y2 - y1;

        double x31 = x3 - x1;
        double x21 = x2 - x1;

        double sx13 = (Math.pow(x1, 2) - Math.pow(x3, 2));

        double sy13 = (Math.pow(y1, 2) - Math.pow(y3, 2));

        double sx21 = (Math.pow(x2, 2) - Math.pow(x1, 2));

        double sy21 = (Math.pow(y2, 2) - Math.pow(y1, 2));

        double f = ((sx13) * (x12) + (sy13) * (x12) + (sx21) * (x13) + (sy21) * (x13))
            / (2 * ((y31) * (x12) - (y21) * (x13)));
        double g = ((sx13) * (y12) + (sy13) * (y12) + (sx21) * (y13) + (sy21) * (y13))
            / (2 * ((x31) * (y12) - (x21) * (y13)));

        double c = -Math.pow(x1, 2) - Math.pow(y1, 2) - 2 * g * x1 - 2 * f * y1;

        double h = -g;
        double k = -f;

        double sqr_radius = h * h + k * k - c;


        this.centerX = h;
        this.centerY = k;
        this.radius = Math.sqrt(sqr_radius);
    }
	public String toString(String circleName) {
		return String.format(circleName+" = Circle(%."+decPlaces+"f, %."+decPlaces+"f, %."+decPlaces+"f)", centerX,centerY,radius);
    }

    public String showRadius(String circleName){
        return "radius("+circleName+") = "+String.format("%."+decPlaces+"f",radius);
    }

    public String showArea(String circleName){
        return "area("+circleName+") = "+String.format("%."+decPlaces+"f",Math.PI*radius*radius);
    }

    public String showPerimeter(String circleName){
        return "perimeter("+circleName+") = "+String.format("%."+decPlaces+"f",Math.PI*radius*2);
    }


    public String showCenter(String circleName){
        return "center("+circleName+") = ("+String.format("%."+decPlaces+"f",centerX)+ ", " +
            String.format("%."+decPlaces+"f",centerY)+ ')';
    }

    public void deltaMove(double deltaX,double deltaY){
        this.centerX=this.centerX+deltaX;
        this.centerY=this.centerY+deltaY;
    }

    public void scale(double scaleFactor){
        this.radius=this.radius*scaleFactor;
    }

    public void decimal(int decPlaces) {
        Circle.decPlaces=decPlaces;
    }
}
public class TestCircle{
    public static void main(String[] args){
    	Map<String, Circle> n = new HashMap<String, Circle>();
        Scanner scanner = new Scanner(System.in);
        int dec = 2;
        
        while (scanner.hasNextLine()){
            List<String> tokens = new ArrayList<String>();
            Scanner lineScanner = new Scanner(scanner.nextLine());
 
            while (lineScanner.hasNext()) {
                tokens.add(lineScanner.next());
            }
            if(tokens.isEmpty()) {
            	break;
            }
            else if(tokens.get(0).equals("createCircle")&&tokens.size()==5) {
            	n.put(tokens.get(1), new Circle(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3)),Double.parseDouble(tokens.get(4))));
            	n.get(tokens.get(1)).decimal(dec);
            }
            else if(tokens.get(0).equals("createCircle")&&tokens.size()==6) {
            	n.put(tokens.get(1), new Circle(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3)),Double.parseDouble(tokens.get(4)),Double.parseDouble(tokens.get(5))));
            	n.get(tokens.get(1)).decimal(dec);
            }
            else if(tokens.get(0).equals("createCircle")&&tokens.size()==8) {
            	n.put(tokens.get(1), new Circle(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3)),Double.parseDouble(tokens.get(4)),Double.parseDouble(tokens.get(5)),Double.parseDouble(tokens.get(6)),Double.parseDouble(tokens.get(7))));
            	n.get(tokens.get(1)).decimal(dec);
            }
            else if(tokens.get(0).equals("show")) {
            	System.out.println(n.get(tokens.get(1)).toString(tokens.get(1)));
            }
            else if(tokens.get(0).equals("showRadius")) {
            	System.out.println(n.get(tokens.get(1)).showRadius(tokens.get(1)));
            }
            else if(tokens.get(0).equals("showArea")) {
            	System.out.println(n.get(tokens.get(1)).showArea(tokens.get(1)));
            }
            else if(tokens.get(0).equals("showPerimeter")) {
            	System.out.println(n.get(tokens.get(1)).showPerimeter(tokens.get(1)));
            }
            else if(tokens.get(0).equals("showCenter")) {
            	System.out.println(n.get(tokens.get(1)).showCenter(tokens.get(1)));
            }
            else if(tokens.get(0).equals("deltaMove")) {
            	n.get(tokens.get(1)).deltaMove(Double.parseDouble(tokens.get(2)),Double.parseDouble(tokens.get(3)));
            }
            else if(tokens.get(0).equals("scale")) {
            	n.get(tokens.get(1)).scale(Double.parseDouble(tokens.get(2)));
            }
            else if(tokens.get(0).equals("decimal")) {
            	for (Map.Entry<String,Circle> entry : n.entrySet())
            		entry.getValue().decimal(Integer.parseInt(tokens.get(1)));
            	dec = Integer.parseInt(tokens.get(1));
            }
            lineScanner.close();
            
        }
        scanner.close();
    }
}
