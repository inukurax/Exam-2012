class Test {

  public static void main(String[] _) {

// Here you can add a comma-separated list of JUnit test classes if you'd like
// to run the tests specified in them. For instance, if you have the classes
// FirstTest and SecondTest you'd like run, add the following:

    org.junit.runner.JUnitCore.main(
        gui.PlotTest.class.getName()
    );
  }

}
