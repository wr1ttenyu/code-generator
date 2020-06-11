package wr1ttenyu.f1nal.study.code.generator.util.mybatis;


import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompress {

    public static byte[] zip(List<FileEntryModel> fileEntryModels) throws Exception {
        System.out.println("压缩中...");

        //创建zip输出流
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteOutputStream);

        for (FileEntryModel fileEntry : fileEntryModels) {
            //调用函数
            compress(zipOutputStream, fileEntry);
        }

        zipOutputStream.finish();
        byte[] bytes = byteOutputStream.getBytes();

        zipOutputStream.close();
        byteOutputStream.close();

        return bytes;
    }

    public static void compress(ZipOutputStream out, FileEntryModel fileEntry) throws Exception {
        String entry = fileEntry.getTargetPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        entry = entry + File.separator + fileEntry.getFileName();
        out.putNextEntry(new ZipEntry(entry));
        ByteArrayInputStream bis = new ByteArrayInputStream(fileEntry.getSource());
        int tag;
        while ((tag = bis.read()) != -1) {
            out.write(tag);
        }

        out.closeEntry();
        out.flush();
    }
}