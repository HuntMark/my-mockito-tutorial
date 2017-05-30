package my;

public class SysoutPrinter implements Printer {
    @Override
    public void printTestPage() throws PrinterNotConnectedException {
        System.out.println("This is test page");
    }
}
