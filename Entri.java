package hari5.penugasan7;

/** SUDAH JADI. Satu baris skor yang valid: nama pemain dan skornya. */
public class Entri {

    private final String nama;
    private final int skor;

    public Entri(String nama, int skor) {
        this.nama = nama;
        this.skor = skor;
    }

    public String getNama() { return nama; }
    public int getSkor() { return skor; }

    @Override
    public String toString() {
        return nama + " " + skor;
    }
}
