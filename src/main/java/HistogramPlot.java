import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HistogramPlot {
  private final double[] data;
  private final String imageName;
  private File outputFile;

  public HistogramPlot(double[] data, String imageName) {
    this.data = data;
    this.imageName = imageName;
  }

  public File createHistogram() throws IOException {
    HistogramDataset dataset = new HistogramDataset();
    dataset.addSeries("X", this.data, 35);
    JFreeChart histogram = ChartFactory.createHistogram("Histogram Plot",
        "data", "frequency", dataset);
    File outputFile = new File(System.getProperty("user.dir") + this.imageName + ".png");
    ChartUtils.saveChartAsPNG(outputFile, histogram, 1280, 720);
    this.outputFile = outputFile;
    return outputFile;
  }

  public void showHistogramInWindow() throws IOException {
    JPanel panel = new JPanel();
    BufferedImage image = ImageIO.read(this.outputFile);
    JLabel label = new JLabel(new ImageIcon(image));
    panel.add(label);
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("java-stats");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}