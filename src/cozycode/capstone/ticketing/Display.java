package cozycode.capstone.ticketing;

import cozycode.capstone.parking.ParkingGarage;
import cozycode.capstone.parking.car.Car;
import cozycode.capstone.parking.car.CarType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    private JFrame frame;
    private JTextArea outputArea;
    private JTextField regField, colorField, makeField, modelField, idField;
    private JComboBox<CarType> carTypeBox;
    private JPanel handicapPanel;
    private final ParkingGarage jumpmanJunction;

    public Display(ParkingGarage garage) {
        this.jumpmanJunction = garage;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Parking Garage Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.getContentPane().setBackground(Color.WHITE); // White background
        frame.setLayout(new BorderLayout());

        setupOutputArea();
        setupInputPanel();
        setupButtonPanel();

        frame.setVisible(true);
    }

    private void setupOutputArea() {
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.WHITE); // White background
        outputArea.setForeground(Color.BLACK);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        addInputField(inputPanel, "Car Registration:", gbc, 0);
        addInputField(inputPanel, "Car Color:", gbc, 1);
        addInputField(inputPanel, "Car Make:", gbc, 2);
        addInputField(inputPanel, "Car Model:", gbc, 3);
        addInputField(inputPanel, "Employee ID:", gbc, 4);

        JLabel carTypeLabel = new JLabel("Car Type:", SwingConstants.RIGHT);
        carTypeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(carTypeLabel, gbc);

        carTypeBox = new JComboBox<>(CarType.values());
        carTypeBox.setFont(new Font("Arial", Font.PLAIN, 16));
        carTypeBox.addActionListener(new CarTypeActionListener());
        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(carTypeBox, gbc);

        // Handicap permit panel (initially hidden)
        handicapPanel = new JPanel();
        handicapPanel.setBackground(Color.WHITE);
        handicapPanel.setLayout(new GridLayout(1, 2, 5, 5));
        handicapPanel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        inputPanel.add(handicapPanel, gbc);

        frame.add(inputPanel, BorderLayout.NORTH);
    }

    private void addInputField(JPanel panel, String labelText, GridBagConstraints gbc, int gridy) {
        JLabel label = new JLabel(labelText, SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = gridy;
        panel.add(label, gbc);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = gridy;
        panel.add(textField, gbc);

        switch (labelText) {
            case "Car Registration:" -> regField = textField;
            case "Car Color:" -> colorField = textField;
            case "Car Make:" -> makeField = textField;
            case "Car Model:" -> modelField = textField;
            case "Employee ID:" -> idField = textField;
        }
    }

    private void setupButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); // White background
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton findSpotButton = createModernButton("Find Spot");
        findSpotButton.addActionListener(new FindSpotActionListener());
        buttonPanel.add(findSpotButton);

        JButton leaveSpotButton = createModernButton("Leave Spot");
        leaveSpotButton.addActionListener(new LeaveSpotActionListener());
        buttonPanel.add(leaveSpotButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.GRAY); // Darker grey on press
                } else if (getModel().isRollover()) {
                    g.setColor(Color.LIGHT_GRAY); // Lighter grey on hover
                } else {
                    g.setColor(Color.BLACK); // Normal black
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }

            @Override
            public void paintBorder(Graphics g) {
                g.setColor(Color.DARK_GRAY);
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }

            @Override
            public boolean isContentAreaFilled() {
                return false;
            }
        };
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(120, 40));
        return button;
    }

    private class CarTypeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CarType selectedType = (CarType) carTypeBox.getSelectedItem();
            handicapPanel.setVisible(selectedType == CarType.HANDICAP);
            frame.revalidate();
            frame.repaint();
        }
    }

    private class FindSpotActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String reg = regField.getText();
            String color = colorField.getText();
            String make = makeField.getText();
            String model = modelField.getText();
            CarType type = (CarType) carTypeBox.getSelectedItem();
            int id;

            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid input for ID. Please enter a valid ID.\n");
                return;
            }

            Car car = new Car(reg, color, model, make, type, id);
            Ticket mySpot = jumpmanJunction.assignSpace(car);

            outputArea.append("\nParking Assignment:\n");
            outputArea.append("Car Model: " + mySpot.getCar().getMake() + " " + mySpot.getCar().getModel() + "\n");
            outputArea.append("Floor: " + mySpot.getFloor() + "\n");
            outputArea.append("Space #: " + mySpot.getNumber() + "\n");
            outputArea.append("Space Type: " + mySpot.getType() + "\n");

            handleSpotAssignment(mySpot);
        }

        private void handleSpotAssignment(Ticket mySpot) {
            boolean isCorrectType = mySpot.getType().equals(mySpot.getCar().getType());
            String message = isCorrectType ?
                    "You got a spot of the requested type! Is this spot acceptable?" :
                    "You did not get a spot of the requested type! Is this spot still acceptable?";
            String title = isCorrectType ? "Spot Assignment" : "Spot Type Mismatch";

            int choice = JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.NO_OPTION) {
                jumpmanJunction.leaveSpace(mySpot.getCar().getId(), false, frame);
                outputArea.append("Your parking assignment has been successfully canceled.\n");
            } else {
                outputArea.append("Great! You may head to your parking spot.\n");
            }

            pause(4);
            clearFieldsAndOutput();
        }
    }

    private class LeaveSpotActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid input for ID. Please enter a valid ID.\n");
                return;
            }

            int result = jumpmanJunction.leaveSpace(id, true, frame);

            if (result == 0) {
                outputArea.append("Thank you for visiting Jumpman Junction! Your parking space has been successfully unassigned.\n");
            } else if (result == -1) {
                outputArea.append("There was an error freeing your parking space. Please make sure you entered your ID correctly.\n");
            } else if (result == -2) {
                outputArea.append("Successfully canceled operation. Make sure you inputted your ID correctly.\n");
            }
            pause(4);
            clearFieldsAndOutput();
        }
    }

    private void clearFieldsAndOutput() {
        regField.setText("");
        colorField.setText("");
        makeField.setText("");
        modelField.setText("");
        idField.setText("");
        carTypeBox.setSelectedIndex(0);
        outputArea.setText("");
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Pause was interrupted");
        }
    }
}
