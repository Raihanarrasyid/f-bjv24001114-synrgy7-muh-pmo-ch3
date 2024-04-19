package org.binarfud.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

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
    void testPositiveOrderFlow() {
        // Prepare
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Execute
        Order order = new Order();
        order.addItem("Nasi Goreng", 15000);
        order.displayOrder();
        order.generateReceipt();

        // Verify
        String expectedOutput = "=========================\n" +
                "Berapa pesanan anda\n" +
                "=========================\n" +
                "Nasi Goreng\t| 15000\n" +
                "(input 0 untuk kembali)\n" +
                "qty => ------------------------------------------+\n" +
                "Total\t\t30000\n" +
                "\n" +
                "Struk pembayaran telah disimpan sebagai file 'receipt.txt'\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testNegativeOrderFlowInvalidInput() {
        // Prepare
        String simulatedInput = "abc\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Execute
        Order order = new Order();
        order.addItem("Nasi Goreng", 15000);
        order.displayOrder();

        // Verify
        String expectedOutput = "=========================\n" +
                "Berapa pesanan anda\n" +
                "=========================\n" +
                "Nasi Goreng\t| 15000\n" +
                "(input 0 untuk kembali)\n" +
                "qty => Pilihan tidak valid.\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testNegativeOrderFlowEmptyReceipt() {
        // Prepare
        String simulatedInput = "n\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Execute
        Order order = new Order();
        order.displayOrder();

        // Verify
        String expectedOutput = "====================\n" +
                "Mohon masukkan input\n" +
                "Minimal 1 Jumlah pesanan!\n" +
                "pilihan anda\n" +
                "====================\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    void testNegativeOrderFlowInvalidInputAfterEmptyReceipt() {
        // Prepare
        String simulatedInput = "abc\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Execute
        Order order = new Order();
        order.displayOrder();

        // Verify
        String expectedOutput = "====================\n" +
                "Mohon masukkan input\n" +
                "Minimal 1 Jumlah pesanan!\n" +
                "pilihan anda\n" +
                "====================\n" +
                "Pilihan tidak valid.\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
