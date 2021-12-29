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
  private final String imageName;
  private File outputFile;
  private final JButton okButton;
  private final JFrame frame = new JFrame("java-stats");

  public HistogramPlot(double[] data, String imageName) {
    this.data = data;
    this.imageName = imageName;
    okButton = new JButton("OK");
    okButton.addActionListener(this);
    okButton.setSize(new Dimension(350,40));
  }

  public File createHistogram() throws IOException {
    HistogramDataset dataset = new HistogramDataset();
    dataset.addSeries("X", this.data, 35);
    JFreeChart histogram = ChartFactory.createHistogram("Histogram Plot",
        "data", "frequency", dataset);
    File outputFile = new File(System.getProperty("user.dir") + this.imageName + ".png");
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

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == okButton){
      frame.setVisible(false);
    }
  }
}