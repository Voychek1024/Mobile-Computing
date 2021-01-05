import java.util.*;

public class LayMines {

    public void layMinesForBlock(Block[][] block, int mineCount) {
        int row = block.length;
        int column = block[0].length;
        LinkedList<Block> list = new LinkedList<Block>(); //TODO: create empty list
        for (Block[] blocks : block) {
            list.addAll(Arrays.asList(blocks).subList(0, column));
        }
        while (mineCount > 0) {
            int size = list.size(); //TODO: return list size
            int randomIndex = (int) (Math.random() * size);
            Block b = list.get(randomIndex); //TODO: return randomIndex of list
            b.setName("M");
            b.setIsMine(true);
            list.remove(randomIndex);
            mineCount--;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (!block[i][j].isMine()) {
                    int mineNumber = 0;
                    for (int k = Math.max(i - 1, 0); k <= Math.min(i + 1, row - 1); k++) {
                        for (int t = Math.max(j - 1, 0); t <= Math.min(j + 1, column - 1); t++) {
                            if (block[k][t].isMine())
                                mineNumber++;
                        }
                    }
                    if (mineNumber > 0) {
                        block[i][j].setName("" + mineNumber);
                    } else {
                        block[i][j].setName(" ");
                    }
                    block[i][j].setNumber(mineNumber);
                }
            }
        }
    }
}
