package my.captors;

import my.verification.Printer;

public class PrinterDiagnostics {

    private Printer printer;

    public PrinterDiagnostics(Printer printer) {
        this.printer = printer;
    }

    public void diagnosticPrint(String text, Integer copies, Boolean collate) {
        StringBuilder diagnostic = new StringBuilder();
        diagnostic.append("** Diagnostic Print **\\n");
        diagnostic.append("*** Copies: ").append(copies).append(" ***\\n");
        diagnostic.append("*** Collate: ").append(collate).append(" ***\\n");
        diagnostic.append("********************\\n\\n");

        printer.print(String.valueOf(diagnostic) + text, copies, collate);
    }

    public void diagnosticAndOriginalPrint(String text, Integer copies, Boolean collate) {
        diagnosticPrint(text, copies, collate);
        printer.print(text, copies, collate);
    }
}