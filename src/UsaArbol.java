
public class UsaArbol {
    public static void main(String[] args) {
        SBinaryTree tree = new SBinaryTree();
        tree.insertar(8);
        tree.insertar(2);
        tree.insertar(4);
        tree.insertar(3);
        tree.insertar(7);
        tree.insertar(12);
        tree.insertar(10);
        tree.insertar(11);


        Nodo d = tree.padreDe(10);
        System.out.println(d.dato);

        System.out.println("Numero de hijos de 8: "+tree.numHijos(8) );

        System.out.println("El balance de 8: "+tree.getBalance(8));

        System.out.println("Los nodos de la generacion 1: ");
        tree.obtenerNodosGeneracion(1);
        System.out.println();

        System.out.println("El elemento mayor del arbol es: "+tree.elementoMayor());

        System.out.println("El promedio del arbol es: "+ tree.promedio());



        tree.inorden();
        tree.remover(10);
        tree.inorden();
    }
}
