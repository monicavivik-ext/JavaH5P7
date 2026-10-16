package hari5.penugasan7;

import java.util.ArrayList;
import java.util.List;

/** SUDAH JADI. Hasil membaca satu file: daftar entri valid dan daftar pesan baris yang rusak. */
public class HasilBaca {

    private final List<Entri> entri = new ArrayList<>();
    private final List<String> galat = new ArrayList<>();

    public void tambahEntri(Entri e) { entri.add(e); }
    public void tambahGalat(String pesan) { galat.add(pesan); }

    public List<Entri> getEntri() { return entri; }
    public List<String> getGalat() { return galat; }
}
