import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import java.io.File;
import java.io.IOException;


public class HistogramPlot {
  private final double[] data;
  private final String imageName;

  public HistogramPlot(double[] data, String imageName) {
    this.data = data;
    this.imageName = imageName;
  }

  public File createHistogram() throws IOException {
    HistogramDataset dataset = new HistogramDataset();
    dataset.addSeries("X", this.data, 35);
    JFreeChart histogram = ChartFactory.createHistogram("Histogram Plot",
        "data", "frequency", dataset);
    File outputFile = new File(System.getProperty("user.dir") + "/histograms/" + this.imageName + ".png");
    ChartUtils.saveChartAsPNG(outputFile, histogram, 1280, 720);
    return outputFile;
  }
}