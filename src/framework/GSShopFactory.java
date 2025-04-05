package framework;

public class GSShopFactory extends  ShopFactory{
    @Override
    public PrimeAcc getNewprimeAccount(int accNo, String accNm) {
        return new GSPrimeAcc(accNo,accNm,0) ;
    }

    @Override
    public NormalAcc getNewNormalAccount(int accNo, String accNm, float deliveryCharge) {
        return new GSNormalAcc(accNo,accNm,0,deliveryCharge);
    }
}
