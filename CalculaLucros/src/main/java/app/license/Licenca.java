package app.license;

public class Licenca{
    private final String hwid;
    private final String cliente;

    public Licenca(String hwid, String cliente){
        this.hwid = hwid;
        this.cliente = cliente;
    }
    public String getHwid(){
        return hwid;
    }
    public String getCliente(){
        return cliente;
    }
}