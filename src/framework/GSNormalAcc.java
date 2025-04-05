package framework;


public class GSNormalAcc extends NormalAcc {
    public GSNormalAcc(int accNo, String accNm, float charges, float deliveryCharge) {
        super(accNo, accNm, charges, deliveryCharge);
    }

    @Override
    public String toString() {
        return "GSNormalAcc: " + super.toString();
    }
}
