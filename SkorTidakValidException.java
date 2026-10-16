package hari5.penugasan7;

/**
 * PENUGASAN VII - TODO 1. Exception buatan sendiri, CHECKED (extends Exception).
 *
 * Lengkapi:
 *  - field nomorBaris
 *  - kedua constructor: pesan untuk super(...) harus berbentuk "Baris <nomorBaris>: <pesan>"
 *    contoh: new SkorTidakValidException(4, "nama kosong").getMessage()  ->  "Baris 4: nama kosong"
 *  - constructor kedua juga meneruskan penyebab:  super(pesanLengkap, penyebab)
 *  - getNomorBaris() mengembalikan nomorBaris
 */
public class SkorTidakValidException extends Exception {
    private int nomorBaris;
    
    public SkorTidakValidException(int nomorBaris, String pesan) {
        super("Baris " + nomorBaris + ": " + pesan);
        this.nomorBaris = nomorBaris;
    }

    /** Versi dengan penyebab asli (misalnya NumberFormatException), agar informasinya tidak hilang. */
    public SkorTidakValidException(int nomorBaris, String pesan, Throwable penyebab) {
        super("Baris " + nomorBaris + ": " + pesan, penyebab);
        this.nomorBaris = nomorBaris;
    }

    public int getNomorBaris() {
        return nomorBaris;
    }
}
