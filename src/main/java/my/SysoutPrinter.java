package my;

public class SysoutPrinter implements Printer {
    @Override
    public void printTestPage() {
        System.out.println("This is test page");
    }
}
