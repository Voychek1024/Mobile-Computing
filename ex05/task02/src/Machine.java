public class Machine {
    public void checkDanger (Goods goods) throws DangerException {
        if(goods.isDanger()) {
            DangerException danger = new DangerException();
            // TODO: throw danger
        }
        else {
            System.out.print(goods.getName() + "NOT DANGEROUS");
        }
    }
}
