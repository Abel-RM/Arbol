public interface IBinaryTree {
    public boolean buscar(int d);
    public void insertar(int d);
    public int tamaño();
    public void remover(int d);
    public boolean vacio();
    // recorridos
    public void inorden();
    public void postorden();
    public void preorden();
}
