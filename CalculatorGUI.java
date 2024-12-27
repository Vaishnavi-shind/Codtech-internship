import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    // Components
    private JTextField inputField;
    private double num1, num2, result;
    private char operator;

    public CalculatorGUI() {
        // Set up the frame
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 24));
        inputField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(inputField, BorderLayout.NORTH);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            // Numbers and decimal point
            inputField.setText(inputField.getText() + command);
        } else if (command.equals("=")) {
            // Perform calculation
            num2 = Double.parseDouble(inputField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        inputField.setText("Error: Division by zero");
                        return;
                    }
                    break;
                default:
                    inputField.setText("Error: Invalid Operation");
                    return;
            }
            inputField.setText(String.valueOf(result));
        } else {
            // Operators
            operator = command.charAt(0);
            num1 = Double.parseDouble(inputField.getText());
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
});
}
}