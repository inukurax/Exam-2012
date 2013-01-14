package spreadsheet.command;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import spreadsheet.Application;

public final class Save
    extends Command {

  private final String path;

  public Save(final String path) {
    this.path = path;
  }

  public void execute() {
    try {
      final PrintWriter printer = new PrintWriter(Files.newBufferedWriter(
        FileSystems.getDefault().getPath(path),
        StandardCharsets.UTF_8,
        StandardOpenOption.CREATE));

      Application.instance.save(printer);
      printer.close();
    } catch (final Exception e) {
      Application.instance.reportError(e.getMessage());
    }
  }

}
