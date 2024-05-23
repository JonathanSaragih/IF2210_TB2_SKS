package jojikanabe.if2210_tb2_sks.classes.kartu;

public class ItemInstantHarvest extends Item {
    public ItemInstantHarvest(String image) {
        super("Instant Harvest", image);
    }

    @Override
    public void efek() {
        System.out.println("Efek item instant harvest");
    }
}
