package Module_1.shortanswerquiz;

public class Point2D {
    double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point2D that) {
        double a, b, d, x2, x1, y2, y1;
        x2 = that.x();
        x1 = x();
        y2 = that.y();
        y1 = y();
        a = x2 - x1;
        b = y2 - y1;
        d = Math.sqrt((a * a) + (b * b));
        return d;
    }

    public boolean equals(Object other) {
        Point2D newObject = (Point2D) other;
        if (x == newObject.x && y == newObject.y) {
            return true;
        }
        return false;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public static void displayShortDistance(Point2D[] points) {
        for (int i = 0; i < points.length; i++) { // for current point, check everything else
            for (int j = 0; j < points.length; j++) {
                if(!(points[i].equals(points[j]))){ // if not equals find its distance
                    double distance = points[i].distanceTo(points[j]);
                    if(distance < 10){ // if distance less than 10, output it
                        System.out.printf("The distance between (%f,%f) and (%f,%f) is %f\n",points[i].x(),points[i].y(),points[j].x(),points[j].y(), distance);
                    } 

                }
            }

        }

    }

    public static void main(String[] args) {
        // testcases
        Point2D first = new Point2D(0, 0);
        Point2D second = new Point2D(3, 3);
        Point2D third = new Point2D(6, 0);
        Point2D fourth = new Point2D(4, 4);
        
        Point2D test = new Point2D(3, 5);
        Point2D test2 = new Point2D(8, 9);
        Point2D[] stuff = { test, test2 }; // array containing object points
        displayShortDistance(stuff);
        // test my method
    }
}
