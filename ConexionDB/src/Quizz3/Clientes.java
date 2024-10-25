package Quizz3;

public class Clientes {

    String nombreCoCliente, emailCliente, telefonoCliente, lista;

    public Clientes(String nombreCoCliente, String emailCliente, String telefonoCliente, String lista) {
        this.nombreCoCliente = nombreCoCliente;
        this.emailCliente = emailCliente;
        this.telefonoCliente = telefonoCliente;
        this.lista = lista;
    }

    public String getNombreCoCliente() {
        return nombreCoCliente;
    }

    public void setNombreCoCliente(String nombreCoCliente) {
        this.nombreCoCliente = nombreCoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }
}
