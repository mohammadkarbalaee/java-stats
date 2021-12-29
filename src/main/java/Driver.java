import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Driver {

  private static int N = 5000;
  public static void main(String[] args) throws IOException {
    Distributor distributor = new Distributor(makeRandomBetweenZeroAndOne(N));
    double[] distributedRandomData = distributor.getDistributedDataset();
    double[] probabilityDensity = getProbabilityDensities(distributedRandomData);

    //making histogram
    HistogramPlot histogramPlot = new HistogramPlot(distributedRandomData,"test");
    File histogramImage = histogramPlot.createHistogram();

    //showing histogram
    showHistogramInWindow(histogramImage);

    //printing mathematical expectation
    double mathematicalExpectationOfX = Ex(distributedRandomData, probabilityDensity);
    System.out.println(mathematicalExpectationOfX);

    //printing variance
    System.out.println(Varx(distributedRandomData,probabilityDensity,mathematicalExpectationOfX));
  }

  private static double[] makeRandomBetweenZeroAndOne(int quantityOfRandomNumbers) {
    double[] randomNumbers = new double[quantityOfRandomNumbers];
    for (int i = 0; i < quantityOfRandomNumbers; i++) {
      randomNumbers[i] = Math.random();
    }
    return randomNumbers;
  }

  private static double[] getProbabilityDensities(double[] dataset) {
    double[] result = new double[N];
    for (int i = 0; i < N; i++) {
      result[i] = fx(dataset[i]);
    }
    return result;
  }

  private static double fx(double data) {
    return Math.pow(data,3) / 100;
  }

  private static double Ex(double[] dataset,double[] probabilities) {
    double mathematicalExpectation = 0;
    for (int i = 0; i < N; i++) {
      mathematicalExpectation += dataset[i] * probabilities[i];
    }
    return mathematicalExpectation;
  }

  private static double Varx(double[] dataset,double[] probabilities,double Ex) {
    for (int i = 0; i < N; i++) {
      dataset[i] = Math.pow(dataset[i],2);
    }
    double ExSquared = Ex(dataset,probabilities);
    return ExSquared - Math.pow(Ex,2);
  }

  private static void showHistogramInWindow(File histogramImage) throws IOException {
    JPanel panel = new JPanel();
    BufferedImage image = ImageIO.read(histogramImage);
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
