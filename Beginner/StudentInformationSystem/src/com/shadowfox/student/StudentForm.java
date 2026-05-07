package com.shadowfox.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentForm extends JFrame {

    JTextField nameField, rollField, searchRollField;
    JComboBox<String> deptBox, searchDeptBox;

    DefaultTableModel model;
    JTable table;

    public StudentForm() {

        setTitle("Student Management System v3");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.BOLD, 12);

        // ================= NAME =================
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        nameLabel.setFont(font);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 150, 25);
        add(nameField);

        // ================= ROLL =================
        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(20, 60, 100, 25);
        rollLabel.setFont(font);
        add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(120, 60, 150, 25);
        add(rollField);

        // ================= DEPARTMENT =================
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(20, 100, 100, 25);
        deptLabel.setFont(font);
        add(deptLabel);

        String[] departments = {"CSE", "IT", "ECE", "EEE", "MECH", "CIVIL"};

        deptBox = new JComboBox<>(departments);
        deptBox.setBounds(120, 100, 150, 25);
        add(deptBox);

        // ================= BUTTONS =================
        JButton addBtn = new JButton("Add");
        addBtn.setBounds(20, 150, 80, 30);
        add(addBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(110, 150, 90, 30);
        add(updateBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(210, 150, 90, 30);
        add(deleteBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(310, 150, 80, 30);
        add(clearBtn);

        // ================= SEARCH =================
        searchRollField = new JTextField();
        searchRollField.setBounds(420, 20, 100, 25);
        add(searchRollField);

        searchDeptBox = new JComboBox<>(departments);
        searchDeptBox.setBounds(530, 20, 80, 25);
        add(searchDeptBox);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(620, 20, 100, 25);
        add(searchBtn);

        // ================= TABLE =================
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Roll No");
        model.addColumn("Department");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(420, 60, 300, 350);
        add(sp);

        // ================= ADD =================
        addBtn.addActionListener(e -> {

            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String dept = deptBox.getSelectedItem().toString();

            if (name.isEmpty() || roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
                return;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(this, "Invalid Name!");
                return;
            }

            if (!roll.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "Roll must be numbers only!");
                return;
            }

            // duplicate check (roll + dept)
            for (int i = 0; i < model.getRowCount(); i++) {

                String r = String.valueOf(model.getValueAt(i, 1));
                String d = String.valueOf(model.getValueAt(i, 2));

                if (r.equals(roll) && d.equals(dept)) {
                    JOptionPane.showMessageDialog(this,
                            "Student already exists in this department!");
                    return;
                }
            }

            model.addRow(new Object[]{name, roll, dept});

            clearFields();
        });

        // ================= DELETE =================
        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Select a row!");
            }
        });

        // ================= UPDATE =================
        updateBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Select row first!");
                return;
            }

            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String dept = deptBox.getSelectedItem().toString();

            if (name.isEmpty() || roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
                return;
            }

            model.setValueAt(name, row, 0);
            model.setValueAt(roll, row, 1);
            model.setValueAt(dept, row, 2);

            clearFields();
        });

        // ================= SEARCH (FIXED) =================
        searchBtn.addActionListener(e -> {

            String roll = searchRollField.getText().trim();
            String dept = searchDeptBox.getSelectedItem().toString();

            if (roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Roll Number!");
                return;
            }

            boolean found = false;

            for (int i = 0; i < model.getRowCount(); i++) {

                String r = String.valueOf(model.getValueAt(i, 1));
                String d = String.valueOf(model.getValueAt(i, 2));

                if (r.equals(roll) && d.equals(dept)) {
                    table.setRowSelectionInterval(i, i);
                    JOptionPane.showMessageDialog(this, "Student Found!");
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Not Found!");
            }
        });

        // ================= CLEAR =================
        clearBtn.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        searchRollField.setText("");
        deptBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}