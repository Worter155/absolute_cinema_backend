package com.team.cinema_app.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendReservationEmail(
            String to,
            byte[] qrCode,
            String text
    ) {

        try {

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true
                    );

            helper.setTo(to);

            helper.setSubject("Бронирование билета");

            helper.setText("""
                    Ваш билет успешно забронирован.
                    
                    %s
                    
                    QR-код прикреплен к письму.
                    """.formatted(text));

            helper.addAttachment(
                    "ticket-qr.png",
                    new ByteArrayResource(qrCode)
            );

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email send failed");
        }
    }
}