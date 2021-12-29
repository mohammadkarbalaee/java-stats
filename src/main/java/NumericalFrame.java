import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NumericalFrame extends JFrame implements ActionListener {

  private JButton okButton = new JButton("");
  private int k;
  private StatisticalCalculator statisticalCalculator;
  private boolean isFinal = false;

  public void setK(int k) {
    this.k = k;
  }

  public NumericalFrame(int k) {
    this.statisticalCalculator = new StatisticalCalculator(k,k);
  }

  public void showExVarx(double Ex, double Varx) {
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
      double[] means = this.storeMeans();
      HistogramPlot meanPlot = new HistogramPlot(means,"Histogram of Means");
      try {
        meanPlot.createHistogram();
        meanPlot.showHistogramInWindow();
      } catch (IOException e) {
        e.printStackTrace();
      }
      this.setVisible(false);
      NumericalFrame numericalFrame = new NumericalFrame(this.k);
      double Exmeans = statisticalCalculator.getExMeans(means,
          statisticalCalculator.probabilityDensityForMeans(means),1);
      double Ex2means = statisticalCalculator.getExMeans(means,
          statisticalCalculator.probabilityDensityForMeans(means),2);
      numericalFrame.showExVarx(Exmeans,statisticalCalculator.getVarMean(Ex2means,Exmeans));
    }
  }

  private double[] storeMeans() {
    double[] means = new double[this.k];
    for (int i = 0; i < means.length; i++) {
      means[i] = calculateMean(statisticalCalculator.makeNewDistributedData());
    }
    return means;
  }

  private double calculateMean(double[] data) {
    double sum = 0;
    for (int i = 0; i < data.length; i++) {
      sum += data[i];
    }
    return sum / data.length;
  }
}
