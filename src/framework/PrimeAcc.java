package framework;

public abstract class PrimeAcc extends  ShopAcc {
    private final boolean isprime=true;

    public PrimeAcc(int accNo, String accNm, float charges) {
        super(accNo, accNm, charges);
    }

    @Override
    public float bookProduct(float amount) {

//        System.out.println("Prime account booked. Total: " + amount);System.out.println("Prime account booked. Total: " + amount);
        return amount;
    }

    @Override
    public String toString() {
        return super.toString() + ", isPrime="+ isprime;
    }
}
