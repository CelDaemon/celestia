package net.voidgroup.celestia.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class AnsiFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        var zdt = ZonedDateTime.ofInstant(
                record.getInstant(), ZoneId.systemDefault());
        var message = formatMessage(record);
        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }
        var levelColor = switch (record.getLevel().toString()) {
            case "INFO" -> AnsiColor.CYAN;
            case "WARNING" -> AnsiColor.YELLOW;
            case "SEVERE" -> AnsiColor.RED;
            case null, default -> AnsiColor.LIGHT_GREY;
        };
        return STR."\{AnsiColor.GREY}[\{AnsiColor.LIGHT_GREY}\{String.format("%1$TT", zdt)}\{AnsiColor.GREY}|\{levelColor}\{record.getLevel().getLocalizedName()}\{AnsiColor.GREY}] [\{AnsiColor.CYAN}\{record.getLoggerName()}\{AnsiColor.GREY}] \{AnsiColor.WHITE}\{message}\{AnsiColor.RESET}\{AnsiColor.RED}\n\{throwable}\{AnsiColor.RESET}";
    }
}
