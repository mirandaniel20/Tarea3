/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btree;

import java.util.Arrays;

class BTree {
    private int[] d;
    private BTree[] child_ptr;
    private boolean l;
    private int n;
    private int grado; // Nuevo atributo para almacenar el grado del árbol

    public BTree(int grado, int cantidadNumeros) {
        this.grado = grado;
        d = new int[grado - 1]; // Inicializar arreglo d con tamaño grado - 1
        child_ptr = new BTree[grado]; // Inicializar arreglo child_ptr con tamaño grado
        l = true;
        n = 0;
        Arrays.fill(child_ptr, null);

        // Agregar código para ingresar los números
        System.out.println("Ingrese " + cantidadNumeros + " números para el árbol B:");
        for (int i = 0; i < cantidadNumeros; i++) {
            insert(entrada.nextInt());
        }
    }

    public void traverse() {
        System.out.println("B tree: ");
        for (int i = 0; i < n; i++) {
            if (!l) {
                child_ptr[i].traverse();
            }
            System.out.print(d[i] + " ");
        }
        if (!l) {
            child_ptr[n].traverse();
        }
        System.out.println();
    }

    public void sort() {
        Arrays.sort(d, 0, n);
    }

    public int splitChild(int i) {
        int j, mid;
        BTree np1, np3, y;
        np3 = new BTree(grado, 0); // Corregido: inicializar np3 con grado y 0 números
        np3.l = true;
        if (i == -1) {
            mid = d[grado / 2];
            d[grado / 2] = 0;
            n--;
            np1 = new BTree(grado, 0); // Corregido: inicializar np1 con grado y 0 números
            np1.l = false;
            l = true;
            for (j = grado / 2 + 1; j < grado - 1; j++) {
                np3.d[j - (grado / 2 + 1)] = d[j];
                np3.n++;
                d[j] = 0;
                n--;
            }
            for (j = 0; j < grado; j++) {
                np3.child_ptr[j] = child_ptr[j + (grado / 2 + 1)];
                child_ptr[j + (grado / 2 + 1)] = null;
            }
            np1.d[0] = mid;
            np1.child_ptr[0] = this;
            np1.child_ptr[1] = np3;
            np1.n++;
            return mid;
        } else {
            y = child_ptr[i];
            mid = y.d[grado / 2];
            y.d[grado / 2] = 0;
            y.n--;
            for (j = grado / 2 + 1; j < grado - 1; j++) {
                np3.d[j - (grado / 2 + 1)] = y.d[j];
                np3.n++;
                y.d[j] = 0;
                y.n--;
            }
            child_ptr[i + 1] = y;
            child_ptr[i + 2] = np3;
            return mid;
        }
    }

    public void insert(int a) {
        int i, t;
        BTree x = this;
        if (x.l && x.n == grado - 1) {
            t = x.splitChild(-1);
            x = this;
            for (i = 0; i < x.n; i++) {
                if (a > x.d[i] && a < x.d[i + 1]) {
                    i++;
                    break;
                } else if (a < x.d[0]) {
                    break;
                }
            }
            x = x.child_ptr[i];
        } else {
            while (!x.l) {
                for (i = 0; i < x.n; i++) {
                    if (a > x.d[i] && a < x.d[i + 1]) {
                        i++;
                        break;
                    } else if (a < x.d[0]) {
                        break;
                    }
                }
                if (x.child_ptr[i].n == grado - 1) {
                    t = x.splitChild(i);
                    x.d[x.n] = t;
                    x.n++;
                    continue;
                }
                x = x.child_ptr[i];
            }
        }
        x.d[x.n] = a;
        x.sort();
        x.n++;
    }

    public boolean eliminar(int valor) {
        boolean encontrado = false;
        int i = 0;
        while (i < n && !encontrado) {
            if (d[i] == valor) {
                encontrado = true;
            } else {
                i++;
            }
        }
        if (encontrado) {
            if (l) {
                for (int j = i; j < n - 1; j++) {
                    d[j] = d[j + 1];
                }
                n--;
            } else {
                // Implementar eliminación en nodo interno si es necesario
            }
        }
        return encontrado;
    }
}