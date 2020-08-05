package tdc.edu.vn.myapplication;

public class Song {
    private String tenbaihat;
    private int hinh;
    private int file;

    public Song() {
    }

    public Song(String tenbaihat, int hinh, int file) {
        this.tenbaihat = tenbaihat;
        this.hinh = hinh;
        this.file = file;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
