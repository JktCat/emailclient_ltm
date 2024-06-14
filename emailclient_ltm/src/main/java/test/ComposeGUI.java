/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author ThaiNTD
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ComposeGUI {

    private static JEditorPane htmlPane;
    private static String toAddress = "";
    private static String filePath = "";
     private static String fullNameStudent = "";
    private static String scoreStudent = "";

//    private static void createComposeGUI(String service, String username, String password) {
//        JFrame composeFrame = new JFrame("Soạn tin mới - " + service);
//        composeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        composeFrame.setSize(600, 400);
//
//        JPanel composePanel = new JPanel(new BorderLayout());
//
//        JPanel formPanel = new JPanel(new GridLayout(3, 2));
//        JLabel toLabel = new JLabel("Gửi đến:");
//        JTextField toField = new JTextField();
//        JLabel subjectLabel = new JLabel("Chủ đề:");
//        JTextField subjectField = new JTextField();
//        JLabel messageLabel = new JLabel("Nội dung:");
//        JTextArea messageTextArea = new JTextArea();
//
//        formPanel.add(toLabel);
//        formPanel.add(toField);
//        formPanel.add(subjectLabel);
//        formPanel.add(subjectField);
//        formPanel.add(messageLabel);
//        formPanel.add(new JScrollPane(messageTextArea));
//
//        JPanel attachmentPanel = new JPanel(new GridLayout(2, 2));
//        JButton attachFileButton = new JButton("Chọn tệp đính kèm");
//        JButton attachHTMLButton = new JButton("Chọn tệp HTML");
//        JLabel attachedFileNameLabel = new JLabel("");
//        JLabel attachedHTMLLabel = new JLabel("");
//
//        attachmentPanel.add(attachFileButton);
//        attachmentPanel.add(attachedFileNameLabel);
//        attachmentPanel.add(attachHTMLButton);
//        attachmentPanel.add(attachedHTMLLabel);
//
//        JPanel sendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        JButton sendButton = new JButton("Gửi");
//        sendPanel.add(sendButton);
//
//         // Thêm DocumentListener để theo dõi sự thay đổi trong 'toField'
//        toField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                updateToAddress();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                updateToAddress();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                updateToAddress();
//            }
//
//            private void updateToAddress() {
//                // Lấy đoạn văn bản từ 'toField'
//                toAddress = toField.getText();
//
//                // Bây giờ 'toAddress' chứa địa chỉ email mà người dùng đã nhập
//                // Bạn có thể sử dụng giá trị này theo cách cần thiết, ví dụ: để gửi email
//                // Thực hiện các hành động khác liên quan đến việc gửi email ở đây
//                System.out.println("Địa chỉ email: " + toAddress);
//            }
//        });
//
//        
//        attachHTMLButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setFileFilter(new FileNameExtensionFilter("HTML File", "html"));
//
//                int result = fileChooser.showOpenDialog(composeFrame);
//
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    String htmlFilePath = selectedFile.getAbsolutePath();
//                    attachedHTMLLabel.setText("Tệp HTML: " + selectedFile.getName());
//
//                    try {
//                        URL url = selectedFile.toURI().toURL();
//
//                        if (htmlPane != null) {
//                            composePanel.remove(htmlPane);
//                        }
//
//                        htmlPane = new JEditorPane();
//                        htmlPane.setEditable(false);
//                        htmlPane.setPage(url);
//
//                        JScrollPane scrollPane = new JScrollPane(htmlPane);
//                        composePanel.add(scrollPane, BorderLayout.CENTER);
//                        composeFrame.revalidate();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        sendButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String to = toField.getText();
//                String subject = subjectField.getText();
//                String message = messageTextArea.getText();
//
//                // Perform email sending operation
//                // sendEmail(username, password, to, subject, message);
//
//                JOptionPane.showMessageDialog(composeFrame, "Email đã được gửi thành công.");
//                composeFrame.dispose();
//            }
//        });
//
//        composePanel.add(formPanel, BorderLayout.NORTH);
//        composePanel.add(attachmentPanel, BorderLayout.CENTER);
//        composePanel.add(sendPanel, BorderLayout.SOUTH);
//
//        composeFrame.add(composePanel);
//        composeFrame.setVisible(true);
//    }
    private static void createComposeGUI(String service, String username, String password) {
        JFrame composeFrame = new JFrame("Soạn tin mới - " + service);
        composeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        composeFrame.setSize(600, 400);

        JPanel composePanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JLabel toLabel = new JLabel("Gửi đến:");
        JTextField toField = new JTextField();
        JLabel subjectLabel = new JLabel("Chủ đề:");
        JTextField subjectField = new JTextField();
        JLabel messageLabel = new JLabel("Nội dung:");
        JTextArea messageTextArea = new JTextArea();

        formPanel.add(toLabel);
        formPanel.add(toField);
        formPanel.add(subjectLabel);
        formPanel.add(subjectField);
        formPanel.add(messageLabel);
        formPanel.add(new JScrollPane(messageTextArea));

        JPanel attachmentPanel = new JPanel(new GridLayout(2, 2));
        JButton attachFileButton = new JButton("Chọn tệp đính kèm");
        JButton attachHTMLButton = new JButton("Chọn tệp HTML");
        JLabel attachedFileNameLabel = new JLabel("");
        JLabel attachedHTMLLabel = new JLabel("");

        attachmentPanel.add(attachFileButton);
        attachmentPanel.add(attachedFileNameLabel);
        attachmentPanel.add(attachHTMLButton);
        attachmentPanel.add(attachedHTMLLabel);

        JPanel sendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton sendButton = new JButton("Gửi");
        sendPanel.add(sendButton);

        attachFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(composeFrame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();
                    attachedFileNameLabel.setText("Tệp đính kèm: " + selectedFile.getName());
                    // Xử lý file Excel
                    System.out.println(selectedFile.getName());
                    processExcelFile(selectedFile.getName());
                    System.out.println(fullNameStudent + " " + scoreStudent);
                }
            }
        });

        attachHTMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(composeFrame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String htmlFilePath = selectedFile.getAbsolutePath();
                    attachedHTMLLabel.setText("Tệp HTML: " + selectedFile.getName());

                    try {
                        URL url = selectedFile.toURI().toURL();

                        JEditorPane htmlPane = new JEditorPane();
                        htmlPane.setEditable(false);
                        htmlPane.setContentType("text/html; charset=UTF-8");
                        htmlPane.setPage(url);

                        JFrame htmlFrame = new JFrame("HTML Preview");
                        htmlFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        htmlFrame.setSize(600, 400);
                        htmlFrame.getContentPane().add(new JScrollPane(htmlPane));
                        htmlFrame.setVisible(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String to = toField.getText();
                String subject = subjectField.getText();
                String message = messageTextArea.getText();

                boolean sendResult = sendEmail(username, password, to, subject, message, filePath);

                if (sendResult) {
                    JOptionPane.showMessageDialog(composeFrame, "Email đã được gửi thành công.");
                    composeFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(composeFrame, "Gửi email không thành công. Vui lòng kiểm tra lại.");
                }
            }
        });

        composePanel.add(formPanel, BorderLayout.NORTH);
        composePanel.add(attachmentPanel, BorderLayout.CENTER);
        composePanel.add(sendPanel, BorderLayout.SOUTH);

        composeFrame.add(composePanel);
        composeFrame.setVisible(true);
    }

    private static void processExcelFile(String filePath) {

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = (Sheet) workbook.getSheetAt(0); // Lấy sheet đầu tiên

            int rowCount = sheet.getLastRowNum(); // Số dòng trong sheet

            for (int i = 1; i <= rowCount; i++) { // Bắt đầu từ dòng thứ 2 (bỏ qua header)
                Row row = sheet.getRow(i);

                // Đọc giá trị từ từng cột
                Cell emailCell = row.getCell(0);
                Cell nameCell = row.getCell(1);
                Cell scoreCell = row.getCell(2);

                String email = emailCell.getStringCellValue();
                String name = nameCell.getStringCellValue();
                double score = scoreCell.getNumericCellValue();
                if (email.compareToIgnoreCase(toAddress) == 0) {
                    fullNameStudent = name;
                    scoreStudent = String.format("%.2f", score);
                }
                // Xử lý dữ liệu tùy ý
                System.out.println("Email: " + email);
                System.out.println("Họ và tên: " + name);
                System.out.println("Điểm: " + score);
                System.out.println("----------------------");
            }

            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean sendEmail(String username, String password, String to, String subject, String message, String filePath) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            emailMessage.setSubject(subject);

            // Tạo một MimeMultipart để chứa nội dung và tệp đính kèm
            MimeMultipart multipart = new MimeMultipart();

            // Nội dung email có định dạng HTML
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(message, "text/html; charset=utf-8");
            multipart.addBodyPart(textPart);

            // Đính kèm tệp (nếu có)
            if (filePath != null && !filePath.isEmpty()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(new File(filePath).getName());
                multipart.addBodyPart(attachmentPart);
            }

            // Đặt nội dung của email là MimeMultipart
            emailMessage.setContent(multipart);

            Transport.send(emailMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createComposeGUI("Service", "username", "password");
            }
        });
    }
}
