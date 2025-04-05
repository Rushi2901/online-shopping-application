package framework;

public abstract class ShopAcc {
    private final int accNo;
    private String accNm;
    protected float charges;

    public ShopAcc(int accNo, String accNm, float charges) {
        this.accNm = accNm;
        this.accNo = accNo;
        this.charges = charges;

    }

    public abstract  float bookProduct(float amount);

    public final int getAccNo(){ return accNo; }

    //getter / setter for mutabless
    public final String getAccNm(){ return accNm; }
    public  void  setAccNm(String accNm){ this.accNm=accNm; }

    @Override
    public String toString(){
        return "Account [accNo=" + accNo + ", accNm=" + accNm +", charges="+ charges+ "]" ;
    }

}

