
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            
            ChocolateBoiler cb = new ChocolateBoiler();
            TacheFill t1 = new TacheFill(cb);
            TacheBoil t2 = new TacheBoil(cb);
            TacheDrain t3 = new TacheDrain(cb);

            t1.start();
            t2.start();
            t3.start();
        }
    }
}

class TacheFill extends Thread {

    private ChocolateBoiler cb;

    public TacheFill(ChocolateBoiler cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        cb.fill();
    }
}

class TacheBoil extends Thread {

    private ChocolateBoiler cb;

    public TacheBoil(ChocolateBoiler cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        cb.boil();        
    }
}

class TacheDrain extends Thread {

    private ChocolateBoiler cb;

    public TacheDrain(ChocolateBoiler cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        cb.drain();        
    }
}