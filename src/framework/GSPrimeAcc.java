package framework;

public class GSPrimeAcc extends PrimeAcc{
    public GSPrimeAcc(int accNo, String accNm, float charges) {
        super(accNo, accNm, charges);
    }

    @Override
    public String toString() {
        return "GSPrimeAcc" +super.toString();
    }
}
