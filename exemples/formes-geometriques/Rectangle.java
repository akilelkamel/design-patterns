
class Rectangle implements FormeGeometrique {

    private double longueur;
    private double largeur;

    public Rectangle(double longueur, double largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }

    public Rectangle() {
        this(2.0, 1.0);
    }

    @Override
    public double surface() {
        return longueur * largeur;
    }

}
