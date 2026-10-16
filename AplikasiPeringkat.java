package hari5.penugasan7;

import java.io.FileNotFoundException;
import java.io.IOException;

/** PENUGASAN VII - TODO 5. Program utama: baca data/skor.txt, tulis data/laporan.txt. */
public class AplikasiPeringkat {

    /**
     * TODO 5. Jalankan PembacaSkor.baca lalu PenulisLaporan.tulis (5 teratas).
     * Kembalikan pesan status, JANGAN biarkan exception keluar:
     *   berhasil              -> "Selesai: 7 entri, 4 baris rusak -> data/laporan.txt"
     *   FileNotFoundException -> "File tidak ditemukan: data/skor.txt"   (pakai nama file masuk)
     *   IOException lain      -> "Gagal: " + pesan exception
     * Ingat urutan catch: subclass (FileNotFoundException) sebelum superclass (IOException).
     */
    public static String jalankan(String masuk, String keluar) {
        try{
            HasilBaca hasil = PembacaSkor.baca(masuk);
            PenulisLaporan.tulis(keluar, hasil, 5);
            return "Selesai: " + hasil.getEntri().size() 
                    + " entri, " + hasil.getGalat().size() 
                    + "baris rusak -> " + keluar;
        } catch (FileNotFoundException e){
            return "File tidak ditemukan: " + masuk;
        } catch (IOException e){
            return "Gagal: " + e.getMessage();
        }
    }


    public static void main(String[] args) {
        System.out.println(jalankan("data/skor.txt", "data/laporan.txt"));
        System.out.println(jalankan("data/skor-kemarin.txt", "data/laporan-kemarin.txt"));
    }
}
