public class Machine {
    public void checkDanger (Goods goods) throws DangerException {
        if(goods.isDanger()) {
            System.out.print(goods.getName() + "\t");
            throw new DangerException(); // TODO: throw danger
        }
        else {
            System.out.println(goods.getName() + "\tNOT DANGEROUS");
        }
    }
}
