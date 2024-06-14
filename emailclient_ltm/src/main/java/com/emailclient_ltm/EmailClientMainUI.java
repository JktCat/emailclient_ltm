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

public class EmailClientMainUI {
    public static void createAndShowEmailClientUI() {
        JFrame emailClientFrame = new JFrame("Email Client");
        emailClientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emailClientFrame.setSize(800, 600); // Tuỳ chỉnh kích thước theo mong muốn
        emailClientFrame.setLayout(new BorderLayout());

        // Tạo các thành phần giao diện cho giao diện mới ở đây

        // Ví dụ: Tạo một nút để soạn email
        JButton composeButton = new JButton("Soạn Email");
        emailClientFrame.add(composeButton, BorderLayout.NORTH);

        // Ví dụ: Tạo một danh sách để hiển thị email
        JList<String> emailList = new JList<>();
        DefaultListModel<String> emailListModel = new DefaultListModel<>();
        emailList.setModel(emailListModel);
        JScrollPane emailScrollPane = new JScrollPane(emailList);
        emailClientFrame.add(emailScrollPane, BorderLayout.CENTER);

        // Đăng ký sự kiện cho nút "Soạn Email" để mở giao diện soạn email
        composeButton.addActionListener(e -> openComposeEmailWindow());

        emailClientFrame.setVisible(true);
    }

    public static void openComposeEmailWindow() {
        // Tạo một JFrame hoặc JDialog mới cho giao diện soạn email và thêm các thành phần cần thiết.
        JFrame composeEmailFrame = new JFrame("Soạn Email");
        composeEmailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        composeEmailFrame.setSize(600, 400);

        // Thêm các thành phần cho việc soạn email ở đây

        // Ví dụ: Tạo một TextArea cho nội dung email
        JTextArea emailContentTextArea = new JTextArea();
        composeEmailFrame.add(emailContentTextArea, BorderLayout.CENTER);

        composeEmailFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmailClientMainUI::createAndShowEmailClientUI);
    }
}

