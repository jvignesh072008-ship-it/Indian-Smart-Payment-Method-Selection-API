package com.paymentrouter;

import com.paymentrouter.dto.ReceiverDetails;
import com.paymentrouter.dto.TransferRequest;
import com.paymentrouter.dto.TransferResponse;
import com.paymentrouter.service.TransferService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Scanner;

@SpringBootApplication
public class IndiaPaymentRouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndiaPaymentRouterApplication.class, args);
    }

    @Bean
    public CommandLineRunner interactiveConsole(TransferService transferService) {
        return args -> {
            String intro = """
                    IndiaPaymentRouter is ready
                    REST API: POST http://localhost:8082/api/transfer   (unchanged)

                    You can also enter a transfer directly in this console below.
                    """;
            System.out.println(intro);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("---- Enter transfer details (or type 'exit' to skip console mode) ----");

                System.out.print("Sender name: ");
                String senderName = scanner.nextLine().trim();
                if (senderName.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Sender account number: ");
                String senderAccNo = scanner.nextLine().trim();

                System.out.print("Sender bank name: ");
                String senderBankName = scanner.nextLine().trim();

                System.out.print("Amount: ");
                String amountInput = scanner.nextLine().trim();

                System.out.print("Receiver name: ");
                String receiverName = scanner.nextLine().trim();

                System.out.print("Receiver account number: ");
                String receiverAccNo = scanner.nextLine().trim();

                System.out.print("Receiver bank name: ");
                String receiverBankName = scanner.nextLine().trim();

                try {
                    BigDecimal amount = new BigDecimal(amountInput);
                    ReceiverDetails receiver = new ReceiverDetails(receiverName, receiverAccNo, receiverBankName);
                    TransferRequest request = new TransferRequest(senderName, senderAccNo, senderBankName, amount, receiver);

                    TransferResponse response = transferService.transfer(request);

                    System.out.println();
                    System.out.println("Transfer successful:");
                    System.out.println("  Receiver name : " + response.receiverName());
                    System.out.println("  Receiver acc  : " + response.receiverAccNo());
                    System.out.println("  Amount        : " + response.amount());
                    System.out.println("  Method        : " + response.method());
                    System.out.println();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount entered. Please enter a numeric value.");
                    System.out.println();
                } catch (RuntimeException e) {
                    System.out.println("Transfer failed: " + e.getMessage());
                    System.out.println();
                }

                System.out.print("Enter another transfer? (y/n): ");
                String again = scanner.nextLine().trim();
                if (!again.equalsIgnoreCase("y")) {
                    break;
                }
            }

            System.out.println("Console mode ended. REST API is still running on port 8082.");
        };
    }
}
