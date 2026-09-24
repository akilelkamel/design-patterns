
public class Cercle implements FormeGeometrique {

    private double rayon;

    public Cercle(double rayon) {
        this.rayon = rayon;
    }

    public Cercle() {
        this(1.0);
    }

    @Override
    public double surface() {
        return Math.PI * rayon * rayon;
    }
}
