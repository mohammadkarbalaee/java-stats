import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InputFrame extends JFrame implements ActionListener {

  private final JTextField nTextField;
  private final JButton submitButton;

  public InputFrame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());
    nTextField = new JTextField();
    setTitle("input form");
    JLabel labeln = new JLabel("n:  ");
    labeln.setFont(new Font("TimesRoman", Font.PLAIN, 25));
    submitButton = new JButton("submit");
    nTextField.setPreferredSize(new Dimension(250,30));
    submitButton.addActionListener(this);
    setLocationRelativeTo(null);
    this.add(labeln);
    this.add(nTextField);
    this.add(submitButton);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == submitButton) {
      int n = Integer.parseInt(this.nTextField.getText());
      StatisticalCalculator statisticalCalculator = new StatisticalCalculator(n,n);
      HistogramPlot histogramPlot = new HistogramPlot(statisticalCalculator.getDistributedData(),"test");
      try {
        histogramPlot.createHistogram();
        histogramPlot.showHistogramInWindow();
      } catch (IOException e) {
        e.printStackTrace();
      }
      this.setVisible(false);
      NumericalFrame numericalFrame = new NumericalFrame(statisticalCalculator.getEx(),statisticalCalculator.getVarx());
    }
  }
}
