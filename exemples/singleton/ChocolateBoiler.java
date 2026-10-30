
public class ChocolateBoiler {

    private boolean empty;
    private boolean boiled;

    public ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
        }else {
            System.out.println("Probleme dans fill");
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true;
        } else {
            System.out.println("Probleme dans drain");
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
        }else {
            System.out.println("Probleme dans boil");
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
