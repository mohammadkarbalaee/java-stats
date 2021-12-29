import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HistogramPlot implements ActionListener {
  private final double[] data;
  private final String plotName;
  private File outputFile;
  private final JButton okButton;
  private final JFrame frame = new JFrame("java-stats");
  private StatisticalCalculator statisticalCalculator;
  private boolean isFinal = false;

  public void setStatisticalCalculator(StatisticalCalculator statisticalCalculator) {
    this.statisticalCalculator = statisticalCalculator;
  }

  public HistogramPlot(double[] data, String plotName) {
    this.data = data;
    this.plotName = plotName;
    okButton = new JButton("OK");
    okButton.addActionListener(this);
    okButton.setSize(new Dimension(350,40));
  }

  public File createHistogram() throws IOException {
    HistogramDataset dataset = new HistogramDataset();
    dataset.addSeries("X", this.data, 35);
    JFreeChart histogram = ChartFactory.createHistogram(this.plotName,
        "data", "frequency", dataset);
    File outputFile = new File(System.getProperty("user.dir") + "plot.png");
    ChartUtils.saveChartAsPNG(outputFile, histogram, 1024, 800);
    this.outputFile = outputFile;
    return outputFile;
  }

  public void showHistogramInWindow() throws IOException {
    JPanel panel = new JPanel();
    BufferedImage image = ImageIO.read(this.outputFile);
    JLabel label = new JLabel(new ImageIcon(image));
    panel.add(label);
    JFrame.setDefaultLookAndFeelDecorated(true);
    FlowLayout flowLayout = new FlowLayout();
    flowLayout.setHgap(30);
    frame.setLayout(flowLayout);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.add(this.okButton);
    frame.pack();
    frame.setVisible(true);
  }

  public void setFinal(boolean aFinal) {
    isFinal = aFinal;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == okButton) {
      frame.setVisible(false);
      if (isFinal) {

      } else {
        NumericalFrame numericalFrame = new NumericalFrame(this.statisticalCalculator.getK());
        numericalFrame.setK(this.statisticalCalculator.getK());
        numericalFrame.showExVarx(statisticalCalculator.getEx(),statisticalCalculator.getVarx());
      }
    }
  }
}