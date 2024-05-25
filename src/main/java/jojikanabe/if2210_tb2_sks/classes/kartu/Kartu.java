package jojikanabe.if2210_tb2_sks.classes.kartu;

public abstract class Kartu {
    protected final String image;
    protected String nama;

    public Kartu(String nama, String image) {
        this.nama = nama;
        this.image = image;
    }

    public Kartu(Kartu original) {
        this.nama = original.nama;
        this.image = original.image;
    }

    public static Kartu getCopy(Kartu originalKartu) {
        Kartu kartu;
        if (originalKartu instanceof Tanaman) {
            kartu = new Tanaman((Tanaman) originalKartu);
        } else if (originalKartu instanceof Hewan) {
            if (originalKartu instanceof HewanKarnivora) {
                kartu = new HewanKarnivora((HewanKarnivora) originalKartu);
            } else if (originalKartu instanceof HewanHerbivora) {
                kartu = new HewanHerbivora((HewanHerbivora) originalKartu);
            } else {
                kartu = new HewanOmnivora((HewanOmnivora) originalKartu);
            }
        } else if (originalKartu instanceof Produk) {
            kartu = new Produk((Produk) originalKartu);
        } else {
            if (originalKartu instanceof ItemAccelerate) {
                kartu = new ItemAccelerate((ItemAccelerate) originalKartu);
            } else if (originalKartu instanceof ItemDelay) {
                kartu = new ItemDelay((ItemDelay) originalKartu);
            } else if (originalKartu instanceof ItemDestroy) {
                kartu = new ItemDestroy((ItemDestroy) originalKartu);
            } else if (originalKartu instanceof ItemInstantHarvest) {
                kartu = new ItemInstantHarvest((ItemInstantHarvest) originalKartu);
            } else if (originalKartu instanceof ItemProtect) {
                kartu = new ItemProtect((ItemProtect) originalKartu);
            } else {
                kartu = new ItemTrap((ItemTrap) originalKartu);
            }
        }
        return kartu;
    }

    public String getNama() {
        return nama;
    }

    public String getImage() {
        return image;
    }
}
