package com.saxomoose.webshop.services;

import com.saxomoose.webshop.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class HtmlEmailService
{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public HtmlEmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine)
    {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendHtmlEmail(String to, String from, String subject, Locale locale, Order order) throws MessagingException
    {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);

        // Create the HTML body using Thymeleaf
        var ctx = new Context(locale);
        ctx.setVariable("order", order);
        String htmlContent = templateEngine.process("mail/order", ctx);
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
}
