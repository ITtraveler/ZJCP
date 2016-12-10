package com.hgs.zjcp.utils.net;

import java.io.File;

/**
 * <input type="file" name="mFile"/>
 * Created by Administrator on 2016/10/2.
 * 表单post提交数据封装
 */
public class PostFormFile {
    private String name;
    private String fileName;
    private File file;

    public PostFormFile() {
    }

    public PostFormFile(String name, String fileName, File file) {
        this.name = name;
        this.fileName = fileName;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
