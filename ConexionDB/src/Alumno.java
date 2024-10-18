public class Alumno {

    int nAlum;
    String nombre, apellido, direccion, telefono;


    public Alumno(int nAlum, String nombre, String apellido, String direccion, String telefono) {
        this.nAlum = nAlum;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getnAlum() {

        return nAlum;
    }

    public void setnAlum(int nAlum) {
        this.nAlum = nAlum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
