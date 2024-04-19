package org.binarfud;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    void testMainFlow() {
        // Prepare
        String simulatedInput = "1\n99\n1\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Execute
        Main.main(new String[]{});

        // Verify
        String expectedOutput = "Selamat datang di program pemesanan makanan online!\n" +
                "Silakan pilih menu makanan yang diinginkan:\n" +
                "1. Nasi Goreng\t\t| 15.000\n" +
                "2. Mie Goreng\t\t| 13.000\n" +
                "3. Nasi + Ayam\t\t| 18.000\n" +
                "4. Es Teh Manis\t\t| 3.000\n" +
                "5. Es Jeruk\t\t| 5.000\n" +
                "99. Pembayaran\n" +
                "0. Keluar aplikasi\n" +
                "=========================\n" +
                "Konfirmasi & Pembayaran\n" +
                "=========================\n" +
                "Total Pembelian: 15000\n" +
                "\n" +
                "1. Konfirmasi dan Bayar\n" +
                "2. Kembali ke menu utama\n" +
                "0. Keluar aplikasi\n" +
                "Terima kasih telah memesan di BinarFud!\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testPositiveFlow() {
        // Prepare
        String simulatedInput = "1\n99\n1\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Execute
        Main.main(new String[]{});

        // Verify
        String expectedOutput = "Selamat datang di program pemesanan makanan online!\n" +
            "Silakan pilih menu makanan yang diinginkan:\n" +
            "1. Nasi Goreng\t\t| 15.000\n" +
            "2. Mie Goreng\t\t| 13.000\n" +
            "3. Nasi + Ayam\t\t| 18.000\n" +
            "4. Es Teh Manis\t\t| 3.000\n" +
            "5. Es Jeruk\t\t| 5.000\n" +
            "99. Pembayaran\n" +
            "0. Keluar aplikasi\n" +
            "=========================\n" +
            "Konfirmasi & Pembayaran\n" +
            "=========================\n" +
            "Total Pembelian: 15000\n" +
            "\n" +
            "1. Konfirmasi dan Bayar\n" +
            "2. Kembali ke menu utama\n" +
            "0. Keluar aplikasi\n" +
            "Terima kasih telah memesan di BinarFud!\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
void testNegativeFlow() {
    // Prepare
    String simulatedInput = "6\n0\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    // Execute
    Main.main(new String[]{});

    // Verify
    String expectedOutput = "Selamat datang di program pemesanan makanan online!\n" +
            "Silakan pilih menu makanan yang diinginkan:\n" +
            "1. Nasi Goreng\t\t| 15.000\n" +
            "2. Mie Goreng\t\t| 13.000\n" +
            "3. Nasi + Ayam\t\t| 18.000\n" +
            "4. Es Teh Manis\t\t| 3.000\n" +
            "5. Es Jeruk\t\t| 5.000\n" +
            "99. Pembayaran\n" +
            "0. Keluar aplikasi\n" +
            "=> Pilihan tidak valid.\n" +
            "Selamat datang di program pemesanan makanan online!\n" +
            "Silakan pilih menu makanan yang diinginkan:\n" +
            "1. Nasi Goreng\t\t| 15.000\n" +
            "2. Mie Goreng\t\t| 13.000\n" +
            "3. Nasi + Ayam\t\t| 18.000\n" +
            "4. Es Teh Manis\t\t| 3.000\n" +
            "5. Es Jeruk\t\t| 5.000\n" +
            "99. Pembayaran\n" +
            "0. Keluar aplikasi\n";
    assertEquals(expectedOutput, outputStreamCaptor.toString());
}

}
