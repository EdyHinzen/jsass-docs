import io.bit3.jsass.importer.Import;
import io.bit3.jsass.importer.Importer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;

public class MyImporter implements Importer {
  @Override
  public Collection<Import> apply(String url, Import previous) {
    try {
      return Collections.singletonList(
          new Import(
              new URI("import.scss"),
              new File("public/assets/import.scss").toURI()
          )
      );
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
