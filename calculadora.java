import java.awt.*;
import javax.swing.*;

public class calculadora extends JFrame {
    private final JTextField num1Field;
    private final JTextField num2Field;
    private final JButton addButton;
    private final JButton subButton;
    private final JButton mulButton;
    private final JButton divButton;
    private final JButton calculateButton;
    private String currentOperation;

    @SuppressWarnings("unused")
    public calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Primeiro número:"));
        num1Field = new JTextField(10);
        inputPanel.add(num1Field);
        inputPanel.add(new JLabel("Segundo número:"));
        num2Field = new JTextField(10);
        inputPanel.add(num2Field);

        JPanel opPanel = new JPanel(new GridLayout(2, 2));
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        opPanel.add(addButton);
        opPanel.add(subButton);
        opPanel.add(mulButton);
        opPanel.add(divButton);

        calculateButton = new JButton("Calcular");
        JPanel calcPanel = new JPanel();
        calcPanel.add(calculateButton);

        add(inputPanel, BorderLayout.NORTH);
        add(opPanel, BorderLayout.CENTER);
        add(calcPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> currentOperation = "+");
        subButton.addActionListener(_ -> currentOperation = "-");
        mulButton.addActionListener(e -> currentOperation = "*");
        divButton.addActionListener(e -> currentOperation = "/");

        calculateButton.addActionListener(e -> calcular());

        pack();
        setLocationRelativeTo(null);
        setVisible(true); 
    }

    public calculadora(JButton addButton, JButton calculateButton, JButton divButton, JButton mulButton, JTextField num1Field, JTextField num2Field, JButton subButton) throws HeadlessException {
        this.addButton = addButton;
        this.calculateButton = calculateButton;
        this.divButton = divButton;
        this.mulButton = mulButton;
        this.num1Field = num1Field;
        this.num2Field = num2Field;
        this.subButton = subButton;
    }

    private void calcular() {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double resultado = 0;

            if (currentOperation == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma operação antes de calcular.");
                return;
            }

            switch (currentOperation) {
                case "+" -> resultado = num1 + num2;
                case "-" -> resultado = num1 - num2;
                case "*" -> resultado = num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(this, "Erro: Divisão por zero.");
                        return;
                    }
                    resultado = num1 / num2;
                }
                default -> {
                    JOptionPane.showMessageDialog(this, "Operação inválida.");
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Resultado: " + resultado);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new calculadora());
    }

    public JButton getSubButton() {
        return subButton;
    }
}
