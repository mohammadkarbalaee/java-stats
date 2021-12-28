import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import java.io.File;
import java.io.IOException;


public class HistogramPlot {

  private String yAxisLabel;
  private String xAxisLabel;
  private double[] data;
  private String imageName;
  private int barLength;
  private String randomVariableName;
  private String plotDescription;
  private int imageWidth;
  private int imageHeight;

  public HistogramPlot(String yAxisLabel,
                       String xAxisLabel,
                       double[] data,
                       String imageName,
                       int barLength,
                       String randomVariableName,
                       String plotDescription,
                       int imageWidth,
                       int imageHeight)
  {
    this.yAxisLabel = yAxisLabel;
    this.xAxisLabel = xAxisLabel;
    this.data = data;
    this.imageName = imageName;
    this.barLength = barLength;
    this.randomVariableName = randomVariableName;
    this.plotDescription = plotDescription;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
  }

  public String getyAxisLabel() {
    return yAxisLabel;
  }

  public void setyAxisLabel(String yAxisLabel) {
    this.yAxisLabel = yAxisLabel;
  }

  public String getxAxisLabel() {
    return xAxisLabel;
  }

  public void setxAxisLabel(String xAxisLabel) {
    this.xAxisLabel = xAxisLabel;
  }

  public double[] getData() {
    return data;
  }

  public void setData(double[] data) {
    this.data = data;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public int getBarLength() {
    return barLength;
  }

  public void setBarLength(int barLength) {
    this.barLength = barLength;
  }

  public String getRandomVariableName() {
    return randomVariableName;
  }

  public void setRandomVariableName(String randomVariableName) {
    this.randomVariableName = randomVariableName;
  }

  public String getPlotDescription() {
    return plotDescription;
  }

  public void setPlotDescription(String plotDescription) {
    this.plotDescription = plotDescription;
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public void setImageWidth(int imageWidth) {
    this.imageWidth = imageWidth;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public void setImageHeight(int imageHeight) {
    this.imageHeight = imageHeight;
  }

  public void createHistogram() throws IOException {
    HistogramDataset dataset = new HistogramDataset();
    dataset.addSeries(this.randomVariableName, this.data, this.barLength);
    JFreeChart histogram = ChartFactory.createHistogram("JFreeChart Histogram",
        this.xAxisLabel, this.yAxisLabel, dataset);
    File outputFile = new File(System.getProperty("user.dir") + "/histograms/" + this.imageName + ".png");
    ChartUtils.saveChartAsPNG(outputFile, histogram, this.imageWidth, this.imageHeight);
  }
}