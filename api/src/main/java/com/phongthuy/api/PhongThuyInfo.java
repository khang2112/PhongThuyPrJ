package com.phongthuy.api;

public class PhongThuyInfo {
    private String can;
    private String chi;
    private String menh;
    private String mauSacPhuHop;
    private String huongTaiLoc;
    
    public PhongThuyInfo(String can, String chi, String menh, String mauSacPhuHop, String huongTaiLoc) {
        this.can = can;
        this.chi = chi;
        this.menh = menh;
        this.mauSacPhuHop = mauSacPhuHop;
        this.huongTaiLoc = huongTaiLoc;
    }
    
    public String getCan() { return can; }
    public void setCan(String can) { this.can = can; }
    
    public String getChi() { return chi; }
    public void setChi(String chi) { this.chi = chi; }
    
    public String getMenh() { return menh; }
    public void setMenh(String menh) { this.menh = menh; }
    
    public String getMauSacPhuHop() { return mauSacPhuHop; }
    public void setMauSacPhuHop(String mauSacPhuHop) { this.mauSacPhuHop = mauSacPhuHop; }
    
    public String getHuongTaiLoc() { return huongTaiLoc; }
    public void setHuongTaiLoc(String huongTaiLoc) { this.huongTaiLoc = huongTaiLoc; }
}
