public interface ComputerWeight {
    public double computeWeight();
}

class Television implements ComputerWeight {
    @Override
    public double computeWeight() {
        return 10.0; // TODO: REWRITE computerWeight method
    }
}

class Computer implements ComputerWeight {
    @Override
    public double computeWeight() {
        return 20.0; // TODO: REWRITE computerWeight method
    }
}
class WashMachine implements ComputerWeight {
    @Override
    public double computeWeight() {
        return 30.0; // TODO: REWRITE computerWeight method
    }
}

class Trunk {
    ComputerWeight [] goods;
    double totalWeights = 0;
    Trunk(ComputerWeight[] goods) {
        this.goods = goods;
    }
    public void setGoods(ComputerWeight[] goods) {
        this.goods = goods;
    }
    public double getTotalWeights() {
        totalWeights = 0;
        // TODO: calculate totalWeights
        for (ComputerWeight good : goods) {
            totalWeights += good.computeWeight();
        }
        return totalWeights;
    }
}

class CheckCarWeight {
    public static void main(String[] args) {
        ComputerWeight[] goods = new ComputerWeight[650]; // 650 goods
        for (int i = 0; i < goods.length; i++) { // divide into 3 categories
            if (i % 3 == 0)
                goods[i] = new Television();
            else if (i % 3 == 1)
                goods[i] = new Computer();
            else
                goods[i] = new WashMachine();
        }
        Trunk trunk = new Trunk(goods);
        System.out.printf("\n货车装载的货物重量: %-8.5f kg\n", trunk.getTotalWeights());
        goods = new ComputerWeight[68]; //68 goods
        for (int i = 0; i < goods.length; i++) {
            if (i % 2==0)
                goods[i] = new Television();
            else
                goods[i] = new WashMachine();
        }
        trunk.setGoods(goods);
        System.out.printf("货车装载的货坜重量: %-8.5f kg\n", trunk.getTotalWeights());
    }
}