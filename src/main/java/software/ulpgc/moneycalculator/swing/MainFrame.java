package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.CurrencyDialog;
import software.ulpgc.moneycalculator.MoneyDialog;
import software.ulpgc.moneycalculator.MoneyDisplay;
import software.ulpgc.moneycalculator.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;
    private MoneyDisplay moneyDisplay;
    private final Map<String,Command> commands = new HashMap<>();

    public MainFrame() throws HeadlessException {
        this.setTitle("Money Calculator");
        this.setSize(600,270);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(createTitle());
        this.add(createMoneyDialog());
        this.add(createCurrencyDialog());
        this.add(createMoneyDisplay());
        this.add(createCalculateButton());
    }

    private Component createTitle() {
        JLabel label = new JLabel("$ MONEY CALCULATOR $");
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private Component createCalculateButton() {
        JButton button = new JButton("Exchange");
        button.addActionListener(e -> commands.get("exchange").execute());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }
}
