import java.text.DecimalFormat;

public class SBinaryTree implements IBinaryTree{
    private Nodo raiz;
    // constructor para un arbol vacio
    public void SBinaryTree() {
        raiz = null;
    }
    public boolean buscar(int d) {
        return buscar(raiz, d);
    }
    private boolean buscar(Nodo nodo, int d) {
        if (nodo==null)
            return false;
        if (d==nodo.dato)
            return true;
        else
            if (d<nodo.dato)
                return buscar(nodo.izquierdo, d);
            else
                return buscar(nodo.derecho, d);
    }
    private Nodo getNodo(Nodo nodo, int d) {
        if (nodo==null)
            return null;
        if (d==nodo.dato)
            return nodo;
        else
        if (d<nodo.dato)
            return getNodo(nodo.izquierdo, d);
        else
            return getNodo(nodo.derecho, d);
    }
    public void insertar(int n) {
        raiz = insertar(raiz, n);
    }
    private Nodo insertar(Nodo r, int n){
        if (r == null)
            r = new Nodo(n);
        else
            if (n<=r.dato)
                r.izquierdo = insertar(r.izquierdo, n);
            else
                r.derecho = insertar(r.derecho, n);
        return r;
    }

    public int tamaño() {
        return tamaño(raiz);
    }

    private int tamaño(Nodo raiz){
        if (raiz == null)
            return 0;
        else
            return 1+ tamaño(raiz.izquierdo)+ tamaño(raiz.derecho);
    }

    public void remover(int d){
        Nodo n = getNodo(raiz,d);
        if (n != null){
            Nodo padre = padreDe(d);
            int nHijos = numHijos(d);

            if (padre == null) {
                //Es raiz
                if (esHoja(n))
                    raiz = null;
                else{
                    if (nHijos == 1){
                        if ( n.izquierdo != null )
                            raiz = n.izquierdo;
                        else
                            raiz = n.derecho;
                    }else{
                        Nodo subDer = masIzq(n.derecho);
                        subDer.izquierdo = n.izquierdo;
                        Nodo p = padreDe(subDer.dato);

                        if (esHoja(subDer))
                            p.izquierdo = null;
                        else
                            p.izquierdo = subDer.derecho;

                        subDer.derecho = n.derecho;
                        raiz.izquierdo = null;
                        raiz.derecho = null;
                        raiz = subDer;
                    }
                    return;
                }
                return;
            }

            if (esHoja(n)){
                //Es nodo hoja
                if ( d > padre.dato )
                    padre.derecho = null;
                else
                    padre.izquierdo = null;
                return;
            }

            if (nHijos == 1){
                //N tiene 1 hijo
                if (n.izquierdo != null){
                    if ( d > padre.dato )
                        padre.derecho = n.izquierdo;
                    else
                        padre.izquierdo = n.izquierdo;
                }else {
                    if ( d > padre.dato )
                        padre.derecho = n.derecho;
                    else
                        padre.izquierdo = n.derecho;
                }
                return;
            }
            else {
                Nodo subDer = masIzq(n.derecho);
                //N tiene dos hijos
                if ( d > padre.dato ){
                    padre.derecho = subDer;
                    subDer.izquierdo = n.izquierdo;
                }
                else{
                    padre.izquierdo = subDer;
                    subDer.izquierdo = n.izquierdo;
                }
                return;
            }
        }
    }

    private Nodo masIzq(Nodo nodo){
        if (nodo.izquierdo == null)
            return nodo;
        else
            return masIzq(nodo.izquierdo);
    }

    public int numHijos(int d){
        Nodo nodo = getNodo(raiz,d);
        if (nodo.izquierdo != null && nodo.derecho != null)
            return 2;
        else
            if (nodo.izquierdo != null || nodo.derecho != null)
                return 1;
            else
                return 0;
    }

    public Nodo padreDe(int nodo){
        if (buscar(nodo) && nodo != raiz.dato)
            return padreDe(raiz,nodo);
        else
            return null;
    }

    private Nodo padreDe(Nodo act,int d){
        if ((act.izquierdo != null && act.izquierdo.dato == d)
                || (act.derecho != null && act.derecho.dato == d))
            return act;
        else
            if (buscar(act.derecho,d))
                return padreDe(act.derecho,d);
            else
                return padreDe(act.izquierdo,d);
    }

    public int getBalance(int d){
        Nodo nodo = getNodo(raiz,d);
        return tamaño(nodo.izquierdo) - tamaño(nodo.derecho);
    }

    public void obtenerNodosGeneracion(int g){
        obtenerNodosGeneracion(g,0,raiz);
    }

    private void obtenerNodosGeneracion(int g,int i,Nodo act){
        if (act==null)
            return;
        if (i == g) {
            System.out.print(act.dato+" ");
        }
        obtenerNodosGeneracion(g,i+1,act.izquierdo);
        obtenerNodosGeneracion(g,i+1,act.derecho);
    }

    public int elementoMayor(){
        return elementoMayor(raiz);
    }

    private int elementoMayor(Nodo act){
        if (act.derecho == null )
            return act.dato;
        else
           return elementoMayor(act.derecho);
    }

    public double promedio() {
        double suma = suma(raiz);
        int tamaño = tamaño();

        DecimalFormat df = new DecimalFormat("#.00");
        if (tamaño > 0)
            return Double.parseDouble(df.format(suma/tamaño));
        else
            return 0;
    }

    private double suma(Nodo act){
        if (act == null)
            return 0;
        else
            return act.dato+ suma(act.izquierdo)+ suma(act.derecho);
    }

    private boolean esHoja(Nodo nodo){
        return nodo.izquierdo == null && nodo.derecho == null;
    }

    public boolean vacio(){
        return raiz == null;
    }

    // recorridos
    public void inorden(){
        inorden(raiz);
        System.out.println();
    }

    private void inorden(Nodo r){
        if (r!=null) {
            inorden(r.izquierdo);
            System.out.print (r.dato+" ");
            inorden(r.derecho);
        }
    }

    public void postorden(){
        postorden(raiz);
        System.out.println();
    }

    private void postorden(Nodo r){
        if (r!=null) {
            postorden(r. izquierdo);
            postorden(r.derecho);
            System.out.print (r.dato+" ");
        }
    }

    public void preorden(Nodo r){
        if (r!=null) {
            System.out.print (r.dato+" ");
            preorden(r.izquierdo);
            preorden(r.derecho);
        }

    }

    public void preorden(){
        preorden(raiz);
        System.out.println();
    }




}
