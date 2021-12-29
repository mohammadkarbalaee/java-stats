import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InputFrame extends JFrame implements ActionListener {

  private JTextField nTextField;
  private JButton submitButton;

  public InputFrame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());
    nTextField = new JTextField();
    submitButton = new JButton("submit");
    nTextField.setPreferredSize(new Dimension(200,40));
    submitButton.addActionListener(this);
    setLocationRelativeTo(null);
    this.add(nTextField);
    this.add(submitButton);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == submitButton) {
      Integer n = Integer.parseInt(this.nTextField.getText());
      StatisticalCalculator statisticalCalculator = new StatisticalCalculator(n,n);
      HistogramPlot histogramPlot = new HistogramPlot(statisticalCalculator.getDistributedData(),"test");
      try {
        histogramPlot.createHistogram();
        histogramPlot.showHistogramInWindow();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
