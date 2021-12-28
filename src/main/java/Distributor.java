public class Distributor {
  private final double[] zeroToOneData;

  public Distributor(double[] zeroToOneData) {
    this.zeroToOneData = zeroToOneData;
  }

  private double distribute(double data) {
    return Math.sqrt(Math.sqrt(400 * data));
  }

  public double[] getDistributedDataset() {
    double[] distributedDataset = new double[this.zeroToOneData.length];
    for (int i = 0; i < this.zeroToOneData.length; i++) {
      distributedDataset[i] = this.distribute(this.zeroToOneData[i]);
    }
    return distributedDataset;
  }
}
