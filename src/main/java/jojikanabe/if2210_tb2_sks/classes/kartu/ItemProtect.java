package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemProtect extends Item {
    public ItemProtect() {
        super("Protect");
    }

    @Override
    public void efek() {
        System.out.println("Efek item protect");
    }
}
