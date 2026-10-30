# Série de TP n° 1 --- Singleton

## Exercice --- La chaudière à chocolat

### Question 1 --- Choc-O-Holic a plutôt bien réussi à éviter les problèmes. Qu'en pensez-vous ?

La classe `ChocolateBoiler` utilise deux attributs booléens :

-   `empty` indique si la chaudière est vide ;
-   `boiled` indique si le mélange a été porté à ébullition.

Les méthodes vérifient l'état de la chaudière avant d'effectuer une
opération :

-   `fill()` remplit la chaudière uniquement si elle est vide ;
-   `boil()` chauffe le mélange uniquement si la chaudière n'est pas
    vide et n'a pas encore été portée à ébullition ;
-   `drain()` vide la chaudière uniquement si elle contient un mélange
    qui a été porté à ébullition.

Ces vérifications permettent d'éviter plusieurs opérations incorrectes,
comme remplir une chaudière déjà pleine ou vider un mélange qui n'a pas
bouilli.

Cependant, la classe présente une limite importante : son constructeur
est public. Il est donc possible de créer plusieurs objets
`ChocolateBoiler`, chacun possédant son propre état. Si l'application
doit contrôler une seule chaudière industrielle, plusieurs instances
risquent de représenter de manière incohérente cette même ressource.

### Question 2 --- Que pourrait-il se passer si plusieurs instances de `ChocolateBoiler` étaient créées ?

Chaque instance possède ses propres attributs `empty` et `boiled`. Par
conséquent, deux instances peuvent avoir des états différents alors
qu'elles sont censées représenter la même chaudière physique.

Par exemple :

``` java
ChocolateBoiler c1 = new ChocolateBoiler();
ChocolateBoiler c2 = new ChocolateBoiler();

c1.fill();
c1.boil();
```

À ce stade, `c1` indique que la chaudière contient un mélange bouilli,
tandis que `c2` indique toujours que la chaudière est vide.

Une autre partie du programme pourrait alors utiliser `c2` et tenter de
remplir la chaudière, alors que le mélange est déjà présent dans la
chaudière physique. Cela pourrait provoquer des opérations
contradictoires ou dangereuses.

Il faut donc garantir que toute l'application utilise **une seule
instance** de `ChocolateBoiler`.

### Question 3 --- Transformer `ChocolateBoiler` en Singleton

Le patron Singleton garantit qu'une classe ne possède qu'une seule
instance accessible par un point d'accès commun.

Pour cela, on effectue trois modifications principales :

1.  Déclarer le constructeur `private` pour empêcher la création
    d'objets depuis l'extérieur de la classe.
2.  Déclarer un attribut statique qui contient l'unique instance.
3.  Fournir une méthode statique `getInstance()` qui retourne cette
    instance.

#### Classe `ChocolateBoiler`

``` java

public class ChocolateBoiler {

    private boolean empty;
    private boolean boiled;
    private static ChocolateBoiler instance;

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true;
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }

    public static ChocolateBoiler getInstance() {
        if (instance == null) {
            instance = new ChocolateBoiler();
        }

        return instance;
    }
}
```

**Explication :**

-   `private static ChocolateBoiler instance` représente l'unique
    instance de la classe.
-   Le constructeur `private` empêche le code extérieur d'utiliser
    `new ChocolateBoiler()`.
-   `getInstance()` est une méthode statique : elle peut être appelée
    sans créer préalablement un objet.
-   L'instance est créée lors du premier appel de la méthode `getInstance()`.

### Question 4 --- Programme principal pour tester la nouvelle classe

``` java
public class Main {
    public static void main(String[] args) {

        ChocolateBoiler c1 = ChocolateBoiler.getInstance();
        ChocolateBoiler c2 = ChocolateBoiler.getInstance();

        // Vérifier que les deux références désignent le même objet.
        System.out.println(c1 == c2);

        System.out.println("Chaudière vide : " + c1.isEmpty());

        c1.fill();
        System.out.println("Après remplissage, chaudière vide : "
                + c2.isEmpty());

        c1.boil();
        System.out.println("Mélange bouilli : " + c2.isBoiled());

        c2.drain();
        System.out.println("Après vidange, chaudière vide : "
                + c1.isEmpty());
    }
}
```

#### Résultat attendu

``` text
true
Chaudière vide : true
Après remplissage, chaudière vide : false
Mélange bouilli : true
Après vidange, chaudière vide : true
```

#### Interprétation

-   `c1 == c2` affiche `true` : les deux références désignent la même
    instance.
-   Les opérations réalisées à travers `c1` sont visibles à travers
    `c2`, et inversement.
-   Le programme utilise donc un état partagé, associé à une seule
    instance de `ChocolateBoiler`.

**Remarque :** ce Singleton garantit l'unicité de l'instance, mais ne
synchronise pas automatiquement les opérations `fill()`, `boil()` et
`drain()` entre plusieurs threads. Dans une application multithreadée,
il faudrait aussi protéger les transitions d'état si plusieurs threads
peuvent manipuler simultanément la chaudière.
