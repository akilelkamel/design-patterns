public abstract class Logistics {

    protected abstract Transport createTransport();

    public void planDelivery() {
        Transport t = createTransport();
        t.livrer();
    }
}
