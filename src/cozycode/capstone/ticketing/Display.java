package cozycode.capstone.ticketing;

import cozycode.capstone.parking.ParkingGarage;
import cozycode.capstone.parking.car.Car;
import cozycode.capstone.parking.car.CarType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    private JFrame frame;
    private JTextArea outputArea;
    private JTextField regField, colorField, makeField, modelField, idField;
    private JComboBox<CarType> carTypeBox;
    private JCheckBox handicapPermitCheckBox;
    private JPanel handicapPanel;
    private final ParkingGarage jumpmanJunction;

    public Display(ParkingGarage garage) {
        this.jumpmanJunction = garage;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Parking Garage Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color
        frame.setLayout(new BorderLayout());

        setupOutputArea();
        setupInputPanel();
        setupButtonPanel();

        frame.setVisible(true);
    }

    private void setupOutputArea() {
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(173, 216, 230)); // Ligh blue color
        outputArea.setForeground(Color.WHITE);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(173, 216, 230)); // Light blue color
        inputPanel.setLayout(new GridLayout(7, 2, 5, 5));

        JLabel regLabel = new JLabel("Car Registration:", SwingConstants.RIGHT);
        regLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(regLabel);
        regField = new JTextField();
        regField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(regField);

        JLabel colorLabel = new JLabel("Car Color:", SwingConstants.RIGHT);
        colorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(colorLabel);
        colorField = new JTextField();
        colorField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(colorField);

        JLabel makeLabel = new JLabel("Car Make:", SwingConstants.RIGHT);
        makeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(makeLabel);
        makeField = new JTextField();
        makeField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(makeField);

        JLabel modelLabel = new JLabel("Car Model:", SwingConstants.RIGHT);
        modelLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(modelLabel);
        modelField = new JTextField();
        modelField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(modelField);

        JLabel idLabel = new JLabel("Employee ID:", SwingConstants.RIGHT);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(idLabel);
        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(idField);

        JLabel carTypeLabel = new JLabel("Car Type:", SwingConstants.RIGHT);
        carTypeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(carTypeLabel);
        carTypeBox = new JComboBox<>(CarType.values());
        carTypeBox.setFont(new Font("Arial", Font.PLAIN, 16));
        carTypeBox.addActionListener(new CarTypeActionListener());
        inputPanel.add(carTypeBox);

        // Handicap permit panel (initially hidden)
        handicapPanel = new JPanel();
        handicapPanel.setBackground(new Color(173, 216, 230)); // Light blue color
        handicapPanel.setLayout(new GridLayout(1, 2, 5, 5));
        handicapPanel.setVisible(false);

        JLabel handicapLabel = new JLabel("Handicap Permit:", SwingConstants.RIGHT);
        handicapLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        handicapPanel.add(handicapLabel);
        handicapPermitCheckBox = new JCheckBox();
        handicapPermitCheckBox.setBackground(new Color(173, 216, 230)); // Light blue color
        handicapPanel.add(handicapPermitCheckBox);

        inputPanel.add(new JLabel()); // Placeholder for layout consistency
        inputPanel.add(handicapPanel);

        frame.add(inputPanel, BorderLayout.NORTH);
    }

    private void setupButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(173, 216, 230)); // Light blue color

        JButton findSpotButton = new JButton("Find Spot");
        findSpotButton.setFont(new Font("Arial", Font.PLAIN, 16));
        findSpotButton.addActionListener(new FindSpotActionListener());
        buttonPanel.add(findSpotButton);

        JButton leaveSpotButton = new JButton("Leave Spot");
        leaveSpotButton.setFont(new Font("Arial", Font.PLAIN, 16));
        leaveSpotButton.addActionListener(new LeaveSpotActionListener());
        buttonPanel.add(leaveSpotButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
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
            int id = Integer.parseInt(idField.getText());

            if (type == CarType.HANDICAP && !handicapPermitCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(frame, "You must have a handicap permit to select a handicap space.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Car car = new Car(reg, color, model, make, type, id);
            Ticket mySpot = jumpmanJunction.assignSpace(car);

            outputArea.append("\nParking Assignment:\n");
            outputArea.append("Car Model: " + mySpot.getCar().getMake() + " " + mySpot.getCar().getModel() + "\n");
            outputArea.append("Floor: " + mySpot.getFloor() + "\n");
            outputArea.append("Space #: " + mySpot.getNumber() + "\n");
            outputArea.append("Space Type: " + mySpot.getType() + "\n");

            if (!mySpot.getType().equals(mySpot.getCar().getType())) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "You did not get a spot of the requested type! Is this spot still acceptable?",
                        "Spot Type Mismatch",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.NO_OPTION) {
                    jumpmanJunction.leaveSpace(mySpot.getCar().getId(), false,frame);
                    outputArea.append("Your parking assignment has been successfully canceled.\n");
                } else {
                    outputArea.append("Great, we apologize for the inconvenience. You may head to your parking spot.\n");
                }
            } else {
                int choice = JOptionPane.showConfirmDialog(null,
                        "You got a spot of the requested type! Is this spot acceptable?",
                        "Spot Assignment",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.NO_OPTION) {
                    jumpmanJunction.leaveSpace(mySpot.getCar().getId(), false,frame);
                    outputArea.append("Your parking assignment has been successfully canceled.\n");
                } else {
                    outputArea.append("Great! You may head to your parking spot.\n");
                }
            }
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
        }
    }
}
