package sample;

public class Nodo {
    private Object Valor;
    private Nodo Siguiente;

    public  Nodo(Object valor){
        this.Valor = valor;
        this.Siguiente = null;
    }
    public void Unir(Nodo n) {
        Siguiente = n;
    }
    public Object ObtenerValor(){
        return Valor;
    }
    public Nodo ObtenerSiuiente(){
        return Siguiente;
    }
}
