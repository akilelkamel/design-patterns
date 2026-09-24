
public class Main {

    public static void main(String[] args) {
        FormeGeometrique tab[] = new FormeGeometrique[2];
        tab[0] = new Rectangle(5, 3);
        tab[1] = new Cercle(2);

        for (FormeGeometrique f : tab) {
            System.out.println(f.surface());
        }
    }
}
