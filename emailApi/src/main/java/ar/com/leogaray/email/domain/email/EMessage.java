package ar.com.leogaray.email.domain.email;

public enum EMessage {
    NOTSUBJET("NOT SUBJECT");

    private String name;
    private EMessage(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
