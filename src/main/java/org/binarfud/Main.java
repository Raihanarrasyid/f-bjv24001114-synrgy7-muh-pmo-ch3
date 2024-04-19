package org.binarfud;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.binarfud.model.Order;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueOrdering = true;
        Optional<Order> optionalOrder = Optional.of(new Order());

        System.out.println("Selamat datang di program pemesanan makanan online!");

        while (continueOrdering) {
            System.out.println("Silakan pilih menu makanan yang diinginkan:");
            System.out.println("1. Nasi Goreng\t\t| 15.000");
            System.out.println("2. Mie Goreng\t\t| 13.000");
            System.out.println("3. Nasi + Ayam\t\t| 18.000");
            System.out.println("4. Es Teh Manis\t\t| 3.000");
            System.out.println("5. Es Jeruk\t\t| 5.000");
            System.out.println("99. Pembayaran");
            System.out.println("0. Keluar aplikasi");
            System.out.print("=> ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    optionalOrder.ifPresent(order -> order.addItem("Nasi Goreng", 15000));
                    break;
                case 2:
                    optionalOrder.ifPresent(order -> order.addItem("Mie Goreng", 13000));
                    break;
                case 3:
                    optionalOrder.ifPresent(order -> order.addItem("Nasi + Ayam", 18000));
                    break;
                case 4:
                    optionalOrder.ifPresent(order -> order.addItem("Es Teh Manis", 3000));
                    break;
                case 5:
                    optionalOrder.ifPresent(order -> order.addItem("Es Jeruk", 5000));
                    break;
                case 99:
                    continueOrdering = false;
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    optionalOrder.ifPresent(Order::closeScanner);
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        System.out.println("=========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("=========================");
        optionalOrder.ifPresent(Order::displayOrder);

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
        System.out.print("=> ");
        int paymentChoice = scanner.nextInt();

        switch (paymentChoice) {
            case 1:
                optionalOrder.ifPresent(Order::generateReceipt);
                System.out.println("Terima kasih telah memesan di BinarFud!");
                break;
            case 2:
                break; // Kembali ke menu utama
            case 0:
                System.out.println("Terima kasih telah menggunakan layanan kami.");
                scanner.close();
                return;
            default:
                System.out.println("Pilihan tidak valid.");
        }
        scanner.close();
    }
}