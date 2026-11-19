package se.trixon.almond.util;


public record Point2D(double x, double y) {

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(double x1, double y1) {
        double dx = x - x1;
        double dy = y - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double distance(Point2D other) {
        return distance(other.x, other.y);
    }
}
