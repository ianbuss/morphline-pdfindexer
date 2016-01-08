package com.cloudera.fce;

import com.google.common.io.Files;
import org.junit.Test;
import org.kitesdk.morphline.api.AbstractMorphlineTest;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Fields;

import java.io.File;

public class PdfIndexerTest extends AbstractMorphlineTest {

  @Test
  public void testIndexPDF() throws Exception {
    morphline = createMorphline("test-morphlines/testIndexPdf");
    Record record = new Record();
    byte[] bytes = Files.toByteArray(new File(RESOURCES_DIR + "/test-documents/sample.pdf"));
    record.put(Fields.ATTACHMENT_BODY, bytes);
    assertTrue(morphline.process(record));
  }

}
