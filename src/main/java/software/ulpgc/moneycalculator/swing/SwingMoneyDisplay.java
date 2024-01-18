package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.MoneyDisplay;
import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private final JLabel label;

    public SwingMoneyDisplay() {
        add(label = createLabel());
    }

    private JLabel createLabel() {
        return new JLabel();
    }

    @Override
    public void show(Money money) {
        label.setText(money.toString());
    }
}
