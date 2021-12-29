public class StatisticalCalculator {
  private int n;
  private int k;
  private double[] randomBetweenZeroOne;
  private double[] probabilityDensities;
  private double[] distributedData;
  private double Ex;
  private double Ex2;
  private double Varx;

  public StatisticalCalculator(int n,int k) {
    this.n = n;
    this.k = k;
    this.randomBetweenZeroOne = new double[this.n];
    this.probabilityDensities = new double[this.n];
    this.makeRandomBetweenZeroAndOne();
    Distributor distributor = new Distributor(this.randomBetweenZeroOne);
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
    return Math.pow(data,3) / 100;
  }

  public void Ex() {
    double mathematicalExpectation = 0;
    for (int i = 0; i < this.n; i++) {
      mathematicalExpectation += this.distributedData[i] * this.probabilityDensities[i];
    }
    this.Ex = mathematicalExpectation;
  }

  public void ExSquared() {
    double mathematicalExpectation = 0;
    for (int i = 0; i < this.n; i++) {
      mathematicalExpectation += this.distributedData[i] * this.distributedData[i] * this.probabilityDensities[i];
    }
    this.Ex2 = mathematicalExpectation;
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
}
