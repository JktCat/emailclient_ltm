/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emailclient_ltm;

/**
 *
 * @author ThaiNTD
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EmailLoginApp {

    //public static String username, password;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static String toAddress = "";
    private static String fullNameStudent = "";
    private static String scoreStudent = "";
    private static String filePath = "";
    private static String htmlFilePath = "";
    private static String htmlContent = "";

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

//    private static void createComposeGUI(String service, String username, String password) {
//    JFrame composeFrame = new JFrame("Soạn tin mới - " + service);
//    composeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    composeFrame.setSize(600, 400);
//
//    JPanel composePanel = new JPanel(new BorderLayout()); // Sử dụng BorderLayout để quản lý giao diện
//
//    JPanel formPanel = new JPanel(new GridLayout(3, 2)); // Sử dụng GridLayout cho form
//    JLabel toLabel = new JLabel("Gửi đến:");
//    JTextField toField = new JTextField();
//    JLabel subjectLabel = new JLabel("Chủ đề:");
//    JTextField subjectField = new JTextField();
//    JLabel messageLabel = new JLabel("Nội dung:");
//    JTextArea messageTextArea = new JTextArea();
//
//    formPanel.add(toLabel);
//    formPanel.add(toField);
//    formPanel.add(subjectLabel);
//    formPanel.add(subjectField);
//    formPanel.add(messageLabel);
//    formPanel.add(new JScrollPane(messageTextArea));
//
//    JPanel attachmentPanel = new JPanel(new GridLayout(2, 2)); // GridLayout cho phần attachment
//    JButton attachFileButton = new JButton("Chọn tệp đính kèm");
//    JButton attachHTMLButton = new JButton("Chọn tệp HTML");
//    JLabel attachedFileNameLabel = new JLabel("");
//    JLabel attachedHTMLLabel = new JLabel("");
//
//    attachmentPanel.add(attachFileButton);
//    attachmentPanel.add(attachedFileNameLabel);
//    attachmentPanel.add(attachHTMLButton);
//    attachmentPanel.add(attachedHTMLLabel);
//
//    JPanel sendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // FlowLayout cho nút gửi
//    JButton sendButton = new JButton("Gửi");
//    sendPanel.add(sendButton);
//
//    composePanel.add(formPanel, BorderLayout.NORTH);
//    composePanel.add(attachmentPanel, BorderLayout.CENTER);
//    composePanel.add(sendPanel, BorderLayout.SOUTH);
//
//    // Thêm DocumentListener để theo dõi sự thay đổi trong 'toField'
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
//    attachFileButton.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JFileChooser fileChooser = new JFileChooser();
//            int result = fileChooser.showOpenDialog(composeFrame);
//
//            if (result == JFileChooser.APPROVE_OPTION) {
//                File selectedFile = fileChooser.getSelectedFile();
//                String filePath = selectedFile.getAbsolutePath();
//                attachedFileNameLabel.setText("Tệp đính kèm: " + selectedFile.getName());
//                // Xử lý file Excel
//                System.out.println(selectedFile.getName());
//                processExcelFile(selectedFile.getName());
//                System.out.println(fullNameStudent + " " + scoreStudent);
//            }
//        }
//    });
//
//    attachHTMLButton.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JFileChooser fileChooser = new JFileChooser();
//            int result = fileChooser.showOpenDialog(composeFrame);
//
//            if (result == JFileChooser.APPROVE_OPTION) {
//                File selectedFile = fileChooser.getSelectedFile();
//                String htmlFilePath = selectedFile.getAbsolutePath();
//                attachedHTMLLabel.setText("Tệp HTML: " + selectedFile.getName());
//
//                try {
//                    URL url = selectedFile.toURI().toURL();
////                    htmlPane.setPage(url);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    });
//
//    sendButton.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String to = toField.getText();
//            String subject = subjectField.getText();
//            String message = messageTextArea.getText();
//
//            boolean sendResult = sendEmail(username, password, to, subject, message, filePath);
//
//            if (sendResult) {
//                JOptionPane.showMessageDialog(composeFrame, "Email đã được gửi thành công.");
//                composeFrame.dispose();
//            } else {
//                JOptionPane.showMessageDialog(composeFrame, "Gửi email không thành công. Vui lòng kiểm tra lại.");
//            }
//        }
//    });
//
//    composeFrame.add(composePanel);
//    composeFrame.setVisible(true);
//}
    private static void createComposeGUI(String service, String username, String password) {
        JFrame composeFrame = new JFrame("Soạn tin mới - " + service);
        composeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        composeFrame.setSize(600, 400);

        JPanel composePanel = new JPanel(new GridLayout(7, 2)); // Tăng số hàng để có chỗ để đính kèm tệp

        JLabel toLabel = new JLabel("Gửi đến:");
        JTextField toField = new JTextField();
        JLabel subjectLabel = new JLabel("Chủ đề:");
        JTextField subjectField = new JTextField();
        JLabel messageLabel = new JLabel("Nội dung:");
        JTextArea messageTextArea = new JTextArea();
        JButton attachFileButton = new JButton("Chọn file excel");

        JButton sendButton = new JButton("Gửi");
        JLabel attachedFileNameLabel = new JLabel("");

        composePanel.add(toLabel);
        composePanel.add(toField);
        composePanel.add(subjectLabel);
        composePanel.add(subjectField);
        composePanel.add(messageLabel);
        composePanel.add(new JScrollPane(messageTextArea));
        composePanel.add(attachFileButton);
        composePanel.add(attachedFileNameLabel);

        composePanel.add(sendButton);

        toField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateToAddress();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateToAddress();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateToAddress();
            }

            private void updateToAddress() {
                // Lấy đoạn văn bản từ 'toField'
                toAddress = toField.getText();

                // Bây giờ 'toAddress' chứa địa chỉ email mà người dùng đã nhập
                // Bạn có thể sử dụng giá trị này theo cách cần thiết, ví dụ: để gửi email
                // Thực hiện các hành động khác liên quan đến việc gửi email ở đây
                System.out.println("Địa chỉ email: " + toAddress);
            }
        });

        String filePath = ""; // Biến để lưu đường dẫn đến tệp đính kèm

        attachFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(composeFrame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    final File selectedFile = fileChooser.getSelectedFile();
                    final String filePath = selectedFile.getAbsolutePath();

                    // Hiển thị tên tệp đính kèm
                    attachedFileNameLabel.setText("         " + selectedFile.getName());
                    // Xử lý file Excel và cập nhật thông tin sinh viên
                    processExcelFile(filePath);

                    // Hiển thị thông tin trong JEditorPane
                    htmlContent = messageTextArea.getText();

                    // Thay thế placeholder trong HTML với thông tin sinh viên
                    htmlContent = htmlContent.replace("<p>Họ và tên: </p>", "<p>Họ và tên: " + fullNameStudent + "</p>");
                    htmlContent = htmlContent.replace("<p>Điểm: </p>", "<p>Điểm: " + scoreStudent + "</p>");

                    // Hiển thị nội dung trong JEditorPane
                    JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
                    editorPane.setEditable(false);

                    // Hiển thị trước nội dung trong một cửa sổ mới (ví dụ: JOptionPane)
                    JOptionPane.showMessageDialog(composeFrame, editorPane, "Xem trước thông tin sinh viên", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

//        attachFileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                int result = fileChooser.showOpenDialog(composeFrame);
//
//                if (result == JFileChooser.APPROVE_OPTION) {
//                    final File selectedFile = fileChooser.getSelectedFile();
//                    final String filePath = selectedFile.getAbsolutePath();
//
//                    // Hiển thị tên tệp đính kèm
//                    attachedFileNameLabel.setText("Tệp đính kèm: " + selectedFile.getName());
//
//                    // Xử lý file Excel
//                    System.out.println(selectedFile.getName());
//                    processExcelFile(selectedFile.getName());
//                    System.out.println(fullNameStudent + " " + scoreStudent);
//                }
//            }
//        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String to = toField.getText();
                String subject = subjectField.getText();
                String message = messageTextArea.getText();

                // Gửi email với đính kèm tệp (nếu có)
                boolean sendResult = sendEmail(username, password, to, subject, message, filePath);

                if (sendResult) {
                    JOptionPane.showMessageDialog(composeFrame, "Email đã được gửi thành công.");
                    composeFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(composeFrame, "Gửi email không thành công. Vui lòng kiểm tra lại.");
                }
            }
        });

        composeFrame.add(composePanel);

        composeFrame.setVisible(true);
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
            message = htmlContent;
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

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Đăng nhập Email");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel serviceLabel = new JLabel("Dịch vụ Email:");
        JComboBox<String> serviceComboBox = new JComboBox<>(new String[]{"Gmail", "Yahoo", "Outlook"});
        JLabel usernameLabel = new JLabel("Tên người dùng:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Đăng nhập");

        frame.add(serviceLabel);
        frame.add(serviceComboBox);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(new JLabel()); // Dòng trống
        frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String service = serviceComboBox.getSelectedItem().toString();
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // Xác thực và gửi email tại đây
                boolean loginResult = login(service, username, new String(password));

                if (loginResult) {
                    // Hiển thị giao diện danh sách tin nhắn và nút soạn tin
                    createInboxGUI(service, username, new String(password));
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Đăng nhập không thành công. Vui lòng kiểm tra thông tin đăng nhập.");
                }

//                if (loginResult) {
//                    JOptionPane.showMessageDialog(frame, "Đăng nhập thành công vào " + service);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Đăng nhập không thành công. Vui lòng kiểm tra thông tin đăng nhập.");
//                }
            }
        });

        frame.setVisible(true);
    }

    private static boolean login(String service, String username, String password) {
        Properties properties = new Properties();

        if ("Gmail".equals(service)) {
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.checkserveridentity", "false"); // Vô hiệu hóa xác minh chứng chỉ

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Transport transport = session.getTransport("smtp");
                transport.connect();
                transport.close();
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else if ("Yahoo".equals(service)) {
            // Điều chỉnh cấu hình để đăng nhập vào Yahoo
            // Cấu hình cho Yahoo 
            //rxaawrweptkibegp

            // Sử dụng Yahoo POP3 
            properties.put("mail.store.protocol", "pop3s");
            properties.put("mail.pop3s.host", "pop.mail.yahoo.com");
            properties.put("mail.pop3s.port", "995");
            properties.put("mail.pop3s.starttls.enable", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Store store = session.getStore("pop3s");
                store.connect();
                store.close();
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        } else if ("Outlook".equals(service)) {
            // Điều chỉnh cấu hình để đăng nhập vào Outlook
            // Sử dụng Outlook POP3

            //Properties properties = new Properties();
            properties.put("mail.store.protocol", "pop3s");
            properties.put("mail.pop3s.host", "outlook.office365.com"); // Địa chỉ POP3 server của Outlook
            properties.put("mail.pop3s.sport", "995"); // Cổng POP3 của Outlook
            properties.put("mail.pop3s.starttls.enable", "true");

            // Tạo phiên làm việc
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Store store = session.getStore("pop3s");
                store.connect();
                store.close();
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    // Hàm để lấy danh sách email từ tài khoản Gmail
    private static String getContent(Message message) throws Exception {
        String content = "";

        Object messageContent = message.getContent();
        if (messageContent instanceof String) {
            content = (String) messageContent;
        } else if (messageContent instanceof Multipart) {
            Multipart multipart = (Multipart) messageContent;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.getContentType().startsWith("text/plain")) {
                    content = bodyPart.getContent().toString();
                    break;
                }
            }
        }

        return content;
    }

    private static Vector<Email> getEmailsFromGmail(String username, String password) {
        Vector<Email> emails = new Vector<>();
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");

        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", username, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                String sender = InternetAddress.toString(message.getFrom());
                String subject = message.getSubject();
                String date = message.getSentDate().toString();

                String sender2 = "";

                try {
                    InternetAddress parsedSender = new InternetAddress(sender);
                    String decodedSender = MimeUtility.decodeText(parsedSender.getPersonal());
                    System.out.println("Người gửi: " + decodedSender);
                    sender2 = decodedSender;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Lấy nội dung email
                String content = getContent(message);

                //String[] emailData = {sender2, subject, content};
                emails.add(new Email(sender2, subject, content));
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emails;
    }

//    private static Vector<String[]> getEmailsFromGmail(String username, String password) {
//        Vector<String[]> emails = new Vector<>();
//        Properties props = new Properties();
//        props.put("mail.store.protocol", "imaps");
//        props.put("mail.imap.host", "imap.gmail.com");
//        props.put("mail.imap.port", "993");
//
//        try {
//            Session session = Session.getInstance(props, null);
//            Store store = session.getStore();
//            store.connect("imap.gmail.com", username, password);
//            Folder inbox = store.getFolder("INBOX");
//            inbox.open(Folder.READ_ONLY);
//
//            Message[] messages = inbox.getMessages();
//            for (Message message : messages) {
//                String sender = InternetAddress.toString(message.getFrom());
//                String subject = message.getSubject();
//                String date = message.getSentDate().toString();
//                String content = message.
//                    
//                String sender2 = "";
//
//                try {
//                    InternetAddress parsedSender = new InternetAddress(sender);
//                    String decodedSender = MimeUtility.decodeText(parsedSender.getPersonal());
//                    System.out.println("Người gửi: " + decodedSender);
//                    sender2 = decodedSender;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                String[] emailData = {sender2, subject, date};
//                emails.add(emailData);
//            }
//
//            inbox.close(false);
//            store.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return emails;
//    }
// ...
// Khi đăng nhập thành công, gọi hàm này để hiển thị danh sách email
    private static void createInboxGUI(String service, String username, String password) {
        JFrame inboxFrame = new JFrame("Hộp thư đến - " + service);
        inboxFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inboxFrame.setSize(800, 600);

        JPanel inboxPanel = new JPanel();
        inboxFrame.add(inboxPanel);

        // Thêm nút "Cập nhật" để cập nhật danh sách email
        JButton refreshButton = new JButton("Cập nhật");
        inboxPanel.add(refreshButton);

        Vector<Email> emails = getEmailsFromGmail(username, password);

        String[] columnNames = {"Người gửi", "Chủ đề", "Nội dung"};
        Object[][] data = new Object[emails.size()][3];
        for (int i = 0; i < emails.size(); i++) {
            Email email = emails.get(i);
            data[i][0] = email.getSender();
            data[i][1] = email.getSubject();
            data[i][2] = email.getMessage();
        }

//        String[] columnNames = {"Người gửi", "Chủ đề", "Nội dung"};
//        Object[][] data = new Object[emails.size()][3];
//        for (int i = 0; i < emails.size(); i++) {
//            data[i] = emails.get(i);
//        }
        JTable inboxTable = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(inboxTable);
        inboxPanel.add(scrollPane);

        // Sự kiện khi nhấn nút "Cập nhật"
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật danh sách email bằng cách gọi lại hàm getEmailsFromGmail
                emails.clear();
                Vector<Email> updatedEmails = getEmailsFromGmail(username, password);
                emails.addAll(updatedEmails);

                // Cập nhật dữ liệu trên JTable
                //DefaultTableModel model = (DefaultTableModel) inboxTable.getModel();
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                inboxTable.setModel(model);

                model.setRowCount(0);
                for (Email email : updatedEmails) {
                    Object[] rowData = {email.getSender(), email.getSubject(), email.getMessage()};
                    model.addRow(rowData);
                }

//                for (Email email : updatedEmails) {
//                    model.addRow(email);
//                }
            }
        });

        // Thêm sự kiện khi người dùng chọn một email từ danh sách
        inboxTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = inboxTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String sender = (String) inboxTable.getValueAt(selectedRow, 0);
                    String subject = (String) inboxTable.getValueAt(selectedRow, 1);
                    String content = (String) inboxTable.getValueAt(selectedRow, 2);

                    // Lấy nội dung email dựa trên sender và subject (cần triển khai hàm tương ứng)
                    String message = getEmailContent(sender, subject, content);

                    // Hiển thị chi tiết email trong một cửa sổ mới
                    showEmailDetail(sender, subject, message);
                }
            }
        });

        JButton composeButton = new JButton("Soạn tin mới");
        composeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createComposeGUI(service, username, password);
            }
        });

        inboxFrame.add(composeButton, BorderLayout.SOUTH);

        inboxFrame.setVisible(true);
    }

    // Hàm để lấy nội dung email dựa trên người gửi và chủ đề (cần triển khai)
    private static String getEmailContent(String sender, String subject, String content) {
        System.out.println("Người gửi: " + sender);
        System.out.println("Chủ đề: " + subject);
        System.out.println("Nội dung: " + content);

        String s = "Người gửi " + sender + "\n" + "Chủ đề: " + subject + "\nNội dung:\n" + content;
        return s;
    }

    // Hiển thị cửa sổ chi tiết email
    private static void showEmailDetail(String sender, String subject, String message) {
        JFrame detailFrame = new JFrame("Chi tiết email");
        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailFrame.setSize(600, 400);

        // Tạo các thành phần giao diện để hiển thị chi tiết email ở đây
        JTextArea messageTextArea = new JTextArea(message);
        messageTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        detailFrame.add(scrollPane);

        detailFrame.setVisible(true);
    }

}
