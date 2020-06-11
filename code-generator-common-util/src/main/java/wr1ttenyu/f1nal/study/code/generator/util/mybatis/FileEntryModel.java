package wr1ttenyu.f1nal.study.code.generator.util.mybatis;

public class FileEntryModel {

    private byte[] source;    // 源文件（带压缩的文件或文件夹）

    private String targetPackage;

    private String fileName;

    public FileEntryModel(byte[] source, String targetPackage, String fileName) {
        this.source = source;
        this.targetPackage = targetPackage;
        this.fileName = fileName;
    }

    public byte[] getSource() {
        return source;
    }

    public void setSource(byte[] source) {
        this.source = source;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileEntryModel{" +
                "source=" + source +
                ", targetPackage='" + targetPackage + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
