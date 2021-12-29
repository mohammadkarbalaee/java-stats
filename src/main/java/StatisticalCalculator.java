import java.util.Arrays;

public class StatisticalCalculator {
  private int n;
  private int k;
  private double[] randomBetweenZeroOne;
  private double[] probabilityDensities;
  private double[] distributedData;
  private double Ex;
  private double Ex2;
  private double Varx;
  private Distributor distributor;

  public int getN() {
    return n;
  }

  public int getK() {
    return k;
  }

  public StatisticalCalculator(int n, int k) {
    this.n = n;
    this.k = k;
    this.randomBetweenZeroOne = new double[this.n];
    this.probabilityDensities = new double[this.n];
    this.makeRandomBetweenZeroAndOne();
    Distributor distributor = new Distributor(this.randomBetweenZeroOne);
    this.distributor = distributor;
    this.distributedData = distributor.getDistributedDataset();
    this.makeProbabilityDensities();
    this.Varx();
  }

  private void makeRandomBetweenZeroAndOne() {
    for (int i = 0; i < this.n; i++) {
      this.randomBetweenZeroOne[i] = Math.random();
    }
  }

  private void makeProbabilityDensities() {
    for (int i = 0; i < this.n; i++) {
      this.probabilityDensities[i] = fx(this.distributedData[i]);
    }
  }

  private double fx(double data) {
    if (data >= 0 && data <= Math.sqrt(2)) {
      return data;
    } else {
      return 0;
    }
  }

  public void Ex() {
    double mathematicalExpectation = 0;
    for (int i = 0; i < this.n; i++) {
      mathematicalExpectation += this.distributedData[i] * this.probabilityDensities[i];
    }

    this.Ex = mathematicalExpectation / this.n;
  }

  public void ExSquared() {
    double mathematicalExpectation = 0;
    for (int i = 0; i < this.n; i++) {
      mathematicalExpectation += Math.pow(this.distributedData[i],2) * this.probabilityDensities[i];
    }
    this.Ex2 = mathematicalExpectation / this.n;
  }

  private void Varx() {
    this.ExSquared();
    this.Ex();
    this.Varx = this.Ex2 - Math.pow(this.Ex,2);
  }

  public double getEx() {
    return this.Ex;
  }

  public double getVarx() {
    return this.Varx;
  }

  public double[] getDistributedData() {
    return distributedData;
  }

  public double[] makeNewDistributedData() {
    double[] randomData = new double[this.n];
    for (int i = 0; i < this.n; i++) {
      randomData[i] = Math.random();
    }
    this.distributor.setZeroToOneData(randomData);
    return this.distributor.getDistributedDataset();
  }

  public double[] probabilityDensityForMeans(double[] means) {
    double[] result = new double[this.n];
    for (int i = 0; i < this.n; i++) {
      result[i] = fx(means[i]);
    }
    return result;
  }

  public double getExMeans(double[] means,double[] dataDensity,double powerX) {
    double mathematicalExpectation = 0;
    for (int i = 0; i < this.n; i++) {
      mathematicalExpectation += Math.pow(means[i],powerX) * dataDensity[i];
    }
    return mathematicalExpectation / this.n;
  }

  public double getVarMean(double Ex2,double Ex){
    return Ex2 - Math.pow(Ex,2);
  }
}
