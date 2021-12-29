import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumericalFrame extends JFrame implements ActionListener {

  private final JButton okButton;

  public NumericalFrame(double Ex,double Varx) {
    this.setSize(new Dimension(550,200));
    JLabel labelEx = new JLabel("\n\n\nE(x): " + Ex);
    Dimension dimension = new Dimension(500,50);
    JLabel labelVarx = new JLabel("Var(x): " + Varx);
    labelEx.setSize(dimension);
    labelVarx.setSize(dimension);
    labelVarx.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    labelEx.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    okButton = new JButton("OK");
    okButton.addActionListener(this);
    okButton.setSize(new Dimension(250,30));
    setLocationRelativeTo(null);
    FlowLayout flowLayout = new FlowLayout();
    flowLayout.setVgap(30);
    flowLayout.setHgap(40);
    this.setLayout(flowLayout);
    this.setTitle("Var(X) and E(X)");
    this.add(labelEx);
    this.add(labelVarx);
    this.add(okButton);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == okButton) {
      this.setVisible(false);
    }
  }
}
