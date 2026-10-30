
import java.util.HashMap;

public class BaseSingleton {

    protected BaseSingleton() {
    }
    private static HashMap<String, BaseSingleton> map = new HashMap();

    public static BaseSingleton getInstance(String className) {
        // Récupérer le Singleton depuis la HashMap
        BaseSingleton singleton = (BaseSingleton) map.get(className);
        if (singleton != null) {
            return singleton;
        } else {	// Singleton non trouvé
            if (className.equals("SubClassSingleton")) {
                singleton = new SubClassSingleton();
            } else if (className.equals("SubClassSingleton2")) {
                singleton = new SubClassSingleton2();
            }

            map.put(className, singleton);

            return singleton;
        }
    }
}
