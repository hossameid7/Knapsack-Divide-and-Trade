package itis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BackpackSolverGUI extends JFrame {
    private final List<Item> items = new ArrayList<>();
    private final JTextField nameField = new JTextField(10);
    private final JTextField weightField = new JTextField(5);
    private final JTextField priceField = new JTextField(5);
    private final JTextField maxWeightField = new JTextField(5);
    private final JTable itemTable;
    private final JTable resultTable;

    public BackpackSolverGUI() {
        super("Решение задачи о рюкзаке от Эида Хоссама");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        Color backgroundColor = new Color(230, 230, 250);
        Color buttonColor = new Color(65, 105, 225);
        Color textColor = Color.WHITE;
        Font labelFont = new Font("Tahoma", Font.PLAIN, 16);
        Font inputFont = new Font("Tahoma", Font.PLAIN, 14);
        Font buttonFont = new Font("Tahoma", Font.BOLD, 14);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Название:"), gbc);
        gbc.gridx = 1;
        nameField.setFont(inputFont);
        inputPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Вес:"), gbc);
        gbc.gridx = 1;
        weightField.setFont(inputFont);
        inputPanel.add(weightField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Стоимость:"), gbc);
        gbc.gridx = 1;
        priceField.setFont(inputFont);
        inputPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton addButton = new JButton("Добавить элемент");
        addButton.setFont(buttonFont);
        addButton.setBackground(buttonColor);
        addButton.setForeground(textColor);
        addButton.addActionListener(this::addItem);
        inputPanel.add(addButton, gbc);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(backgroundColor);
        controlPanel.add(new JLabel("Максимальный вес:"));
        maxWeightField.setFont(inputFont);
        controlPanel.add(maxWeightField);

        JButton solveButton = new JButton("Решить");
        solveButton.setFont(buttonFont);
        solveButton.setBackground(buttonColor);
        solveButton.setForeground(textColor);
        solveButton.addActionListener(this::solveBackpack);
        controlPanel.add(solveButton);

        JButton deleteButton = new JButton("Удалить");
        deleteButton.setFont(buttonFont);
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(textColor);
        deleteButton.addActionListener(this::deleteItem);
        controlPanel.add(deleteButton);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(backgroundColor);
        listPanel.add(new JLabel("Элементы:"), BorderLayout.NORTH);
        String[] columnNames = {"Название", "Вес", "Стоимость"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(backgroundColor);
        resultPanel.add(new JLabel("Результат:"), BorderLayout.NORTH);
        resultTable = new JTable();
        resultTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JScrollPane resultScrollPane = new JScrollPane(resultTable);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(listPanel, BorderLayout.WEST);
        add(resultPanel, BorderLayout.CENTER);
    }

    private void addItem(ActionEvent e) {
        try {
            String name = nameField.getText();
            double weight = Double.parseDouble(weightField.getText());
            int price = Integer.parseInt(priceField.getText());
            Item item = new Item(name, weight, price);
            items.add(item);
            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
            model.addRow(new Object[]{name, weight, price});
            nameField.setText("");
            weightField.setText("");
            priceField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Неправильный формат числа!");
        }
    }

    private void solveBackpack(ActionEvent e) {
        try {
            double maxWeight = Double.parseDouble(maxWeightField.getText());
            Backpack backpack = new Backpack(maxWeight);
            backpack.solveWithDivideAndTrade(items);
            displayResults(backpack);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Неправильный формат числа!");
        }
    }

    private void deleteItem(ActionEvent e) {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
            model.removeRow(selectedRow);
            items.remove(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, выберите элемент, который хотите удалить!");
        }
    }

    private void displayResults(Backpack backpack) {
        DefaultTableModel resultModel = new DefaultTableModel(new String[]{"Название", "Вес", "Стоимость"}, 0);
        resultTable.setModel(resultModel);
        for (Item item : backpack.getBestItems()) {
            resultModel.addRow(new Object[]{item.name, item.weight, item.price});
        }
        JOptionPane.showMessageDialog(this, "Лучшая цена: " + backpack.getBestPrice() + "\nОбщий вес: " + backpack.getBestWeight());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BackpackSolverGUI frame = new BackpackSolverGUI();
            frame.setVisible(true);
        });
    }
}
