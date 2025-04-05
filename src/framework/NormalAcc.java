package framework;

public abstract class NormalAcc extends ShopAcc {
    private final float deliveryCharge;

    public NormalAcc(int accNo, String accNm, float charges,float deliveryCharge) {
        super(accNo, accNm, charges);
        this.deliveryCharge=deliveryCharge;
    }


    @Override
    public float bookProduct(float amount) {
        float total = amount +deliveryCharge;
//        System.out.println(" Normal account booked total "+ total);
        return  total;
    }

    @Override
    public String toString() {
        return super.toString()+ ", deliveryCharges=" + deliveryCharge;
    }
}
