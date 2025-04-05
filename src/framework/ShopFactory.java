package framework;

public abstract  class ShopFactory {
    public abstract  PrimeAcc  getNewprimeAccount(int accNo,String accNm);
    public abstract  NormalAcc getNewNormalAccount(int accNo,String accNm, float deliveryCharge);

}
