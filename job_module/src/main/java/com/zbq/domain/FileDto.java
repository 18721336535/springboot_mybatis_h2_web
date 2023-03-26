package com.zbq.domain;

import java.util.Objects;

/**
 * @Author: zengbingqing2
 * @Description:
**/
public class FileDto {

    private String id;
    private String fileName;
    private String filePath;
    private String fileSize;
    private String lastUpdateTime;
    private String status;
    private String owner;
    private int pageSize = 3;
    private int offset =0;

    public FileDto(String id, String fileName, String filePath, String fileSize, String lastUpdateTime, String status, String owner) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.lastUpdateTime = lastUpdateTime;
        this.status = status;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public int hashCode(){
        int result = 1;
        int prime = 3;
        return result*prime+ (id == null ? 0:id.hashCode());
    }
    @Override
    public boolean equals(Object obj){
        FileDto other = (FileDto)obj;
        boolean a =  Objects.equals(fileName,other.fileName);
        boolean b= Objects.equals(id,other.id);
        return a && b;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath +
                '}';
    }
}
