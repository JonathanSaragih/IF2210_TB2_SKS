package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemProtect extends Item {
    public ItemProtect(String image) {
        super("Protect", image);
    }

    @Override
    public void efek() {
        System.out.println("Efek item protect");
    }
}
