package com.ekros.lab2;

public class SchemaReserve extends Schema{

  private final int hours;
  private double qSystem;
  private int tSystem;
  private double qReservedSystem;
  private double pReservedSystem;
  private int tReservedSystem;
  private final int multiplicity;
  private double gQ;
  private double gP;
  private double gT;
  private String mode = "";

  public SchemaReserve(int[][] schema, double[] p, int[] input, int[] output, int hours, int multiplicity) {
    super(schema, p, input, output);
    this.hours = hours;
    this.multiplicity = multiplicity;
  }

  public void calcNotLoadedGeneral(){
    calcPSystem();
    qSystem = 1.0 - pSystem;
    tSystem = (int) (-hours / Math.log(pSystem));
    qReservedSystem = qSystem/(factorial(multiplicity+1L));
    pReservedSystem = 1.0 - qReservedSystem;
    tReservedSystem = (int) (-hours / Math.log(pReservedSystem));
    gQ = qReservedSystem/qSystem;
    gP = pReservedSystem/pSystem;
    gT = (double) (tReservedSystem)/tSystem;

    mode = "Not Loaded General Reserved";
  }

  public void calcLoadedGeneral(){
    calcPSystem();
    qSystem = 1.0 - pSystem;
    tSystem = (int) (-hours / Math.log(pSystem));
    qReservedSystem = Math.pow(qSystem, multiplicity+1L);
    pReservedSystem = 1.0 - qReservedSystem;
    tReservedSystem = (int) (-hours / Math.log(pReservedSystem));
    gQ = qReservedSystem/qSystem;
    gP = pReservedSystem/pSystem;
    gT = (double) (tReservedSystem)/tSystem;

    mode = "Loaded General Reserved";
  }

  public void calcNotLoadedSeparate(){
    calcPSystem();
    qSystem = 1.0 - pSystem;
    tSystem = (int) (-hours / Math.log(pSystem));

    double[] q = new double[p.length];
    double[] pReserved = new double[p.length];
    double[] qReserved = new double[p.length];

    long fact = factorial(multiplicity+1L);

    for(int i = 0; i < p.length; i++){
      q[i] = 1.0 - p[i];
      qReserved[i] = q[i]/fact;
      pReserved[i] = 1.0 - qReserved[i];
      System.out.println("Q" + (i+1) + "r = " + qReserved[i] + "   P" + (i+1) + "r = " + pReserved[i]);
    }

    Schema schemaReserved = new Schema(schema, pReserved, input, output);
    schemaReserved.calcPSystem();

    pReservedSystem = schemaReserved.pSystem;
    qReservedSystem = 1.0 - pReservedSystem;
    tReservedSystem = (int) (-hours / Math.log(pReservedSystem));
    gQ = qReservedSystem/qSystem;
    gP = pReservedSystem/pSystem;
    gT = (double) (tReservedSystem)/tSystem;

    mode = "Not Loaded Separate Reserved";
  }

  public void calcLoadedSeparate(){

    calcPSystem();
    qSystem = 1.0 - pSystem;
    tSystem = (int) (-hours / Math.log(pSystem));

    double[] q = new double[p.length];
    double[] pReserved = new double[p.length];
    double[] qReserved = new double[p.length];

    for(int i = 0; i < p.length; i++){
      q[i] = 1.0 - p[i];
      qReserved[i] = Math.pow(q[i], multiplicity+1.0);
      pReserved[i] = 1.0 - qReserved[i];
      System.out.println("Q" + (i+1) + "r = " + qReserved[i] + "   P" + (i+1) + "r = " + pReserved[i]);
    }

    Schema schemaReserved = new Schema(schema, pReserved, input, output);
    schemaReserved.calcPSystem();

    pReservedSystem = schemaReserved.pSystem;
    qReservedSystem = 1.0 - pReservedSystem;
    tReservedSystem = (int) (-hours / Math.log(pReservedSystem));
    gQ = qReservedSystem/qSystem;
    gP = pReservedSystem/pSystem;
    gT = (double) (tReservedSystem)/tSystem;

    mode = "Loaded Separate Reserved";
  }


  public void printData() {
    System.out.println(mode);
    System.out.println("Psystem(" + hours + ") = " + pSystem);
    System.out.println("Qsystem(" + hours + ") = " + qSystem);
    System.out.println("Tsystem = " + tSystem);
    System.out.println("PresergvedSystem(" + hours + ") = " + pReservedSystem);
    System.out.println("QresergvedSystem(" + hours + ") = " + qReservedSystem);
    System.out.println("TresergvedSystem = " + tReservedSystem);
    System.out.println("gQ = " + gQ);
    System.out.println("gP = " + gP);
    System.out.println("gT = " + gT);
    System.out.println("\n---------------------------------------\n");
  }

  private long factorial(long num){
    long factorial = 1;
    for(long i = num; i > 0; i--){
      factorial *= i;
    }
    return factorial;
  }

}
