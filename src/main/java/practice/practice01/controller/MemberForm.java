package practice.practice01.controller;

 //등록 화면에서 데이터를 전달 받을 폼 객체
public class MemberForm {
    private String id;
    private String pw;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }


}

