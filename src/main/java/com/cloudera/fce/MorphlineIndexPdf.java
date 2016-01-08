package com.cloudera.fce;

import org.kitesdk.morphline.api.Command;
import org.kitesdk.morphline.api.MorphlineContext;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Compiler;
import org.kitesdk.morphline.base.Fields;
import org.kitesdk.morphline.base.Notifications;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MorphlineIndexPdf {

  private static String MORPHLINE_CONFIG = "morphlines/indexPdf.conf";

  public static void main(String[] args) throws Exception {
    File f = new File(MorphlineIndexPdf.class.getClassLoader().getResource(MORPHLINE_CONFIG).getFile());
    MorphlineContext context = new MorphlineContext.Builder().build();
    Command morphline = new Compiler().compile(f, null, context, null);

    Notifications.notifyBeginTransaction(morphline);
    for (String pdf : args) {
      InputStream is = new FileInputStream(new File(pdf));
      Record record = new Record();
      record.put(Fields.ATTACHMENT_BODY, is);
      morphline.process(record);
      is.close();
    }
    Notifications.notifyCommitTransaction(morphline);
  }

}
