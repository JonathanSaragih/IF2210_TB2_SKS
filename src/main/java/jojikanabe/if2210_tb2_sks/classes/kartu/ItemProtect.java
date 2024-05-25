package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemProtect extends Item {
    public ItemProtect(String image) {
        super("Protect", image);
    }

    public ItemProtect(ItemProtect original) {
        super(original);
    }

    @Override
    public void efek(int row, int col, int nomorPemain) throws Exception {

    }
}
