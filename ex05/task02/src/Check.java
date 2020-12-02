public class Check {
    public static void main(String[] args) {
        Machine machine = new Machine();
        String[] name = {"Apple", "Mine", "Clothes", "Acid", "Watch", "Sulphur"};
        Goods [] goods = new Goods[name.length];
        for (int i = 0; i < name.length; i++) {
            goods[i] = new Goods();
            if (i % 2 == 0) {
                goods[i].setDanger(false);
                goods[i].setName(name[i]);
            }
            else {
                goods[i].setDanger(true);
                goods[i].setName(name[i]);
            }
        }
        for (Goods good : goods) {
            try {
                machine.checkDanger(good);
                System.out.println(good.getName() + "\tpassed\n");
            } catch (DangerException e) {
                e.toShow(); // TODO: e calls toShow() method
                System.out.println(good.getName() + "\tbanned\n");
            }
        }
    }
}
