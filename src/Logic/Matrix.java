package Logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gerar
 */
public final class Matrix implements Runnable{

    int[][] Matrix1a;
    int[][] Matrix2a;
    int[][] auxMatrix;
    
    int[][] MatrizNueva;
    int rowsA;
    int colsA;
    int rowsB;
    int colsB;
    int startIndex;
    int endIndex;
    JLabel labelConcurrente;
    JTextArea panelHilos;
    Initialize process;
    
    public Matrix(int rowsA, int colsA, int rowsB, int colsB, int[][] Matrix1Concurrent, int[][] Matrix2a, int startIndex, int endIndex, JLabel labelConcurrente, Initialize process, JTextArea panelHilos) {
        
        this.rowsA = rowsA;
        this.colsA = colsA;
        this.rowsB = rowsB;
        this.colsB = colsB;
        
        this.Matrix2a = new int[rowsB][colsB];
        this.Matrix2a = Matrix2a;
        
        this.panelHilos = panelHilos;
        
        this.MatrizNueva = new int[Matrix1Concurrent.length][Matrix1Concurrent[0].length];
        
        for(int i = 0; i < Matrix1Concurrent.length; i++) {
            
            for(int j = 0; j < Matrix1Concurrent[i].length; j++) {
                
                MatrizNueva[i][j] = Matrix1Concurrent[i][j];
                
            }
            
        }
        
        this.auxMatrix = new int[rowsA][colsB];
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.labelConcurrente = labelConcurrente;
        this.process = process;
        
    }
    
    @Override
    public void run() {
        
        String texto = "Thread: " + Thread.currentThread().getName() + " en ejecucion\n";
            
        try {
            process.printStatusOfThreads(panelHilos, texto);
        } catch (InterruptedException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        }

        final long time1M = System.currentTimeMillis();
                
        for(int i = 0; i < MatrizNueva.length; i++) {
                    
            for(int j = 0; j < colsB; j++) {
                        
                auxMatrix[i][j] = multiplyMatrix(i, j);
//                       
                        
            }                       
                        
        }

        process.joinArray(auxMatrix, startIndex, endIndex, auxMatrix[0].length);
        
        final long time2M = System.currentTimeMillis();
        
        final long timeResult = time2M - time1M;   
               
        //Se imprime el estado finalizado del hilo
            
        texto = "Thread: " + Thread.currentThread().getName() + ", tiempo: " + Integer.toString((int) timeResult) + "ms\n";
        
        System.out.println(texto);
        
        try {
            process.printStatusOfThreads(panelHilos, texto);
            //System.out.println("Thread: " + Thread.currentThread().getName() + ", tiempo: " + timeResult);
        } catch (InterruptedException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            process.setFinalTimeConcurrent((int) timeResult);
        } catch (InterruptedException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

    
    public int multiplyMatrix(int i, int j) {
        
        int res = 0;
        int aux = 0;
        
        while(aux < colsA){
            
            res = res + (MatrizNueva[i][aux] * Matrix2a[aux][j]);
            aux++;
            
        }
        
        return res;
        
    } 
    
//    public synchronized void printStatus(int estado, JTextArea panelHilos, String previous) {
//         
//        
////        System.out.println("Thread: " + Thread.currentThread().getName() + ",   " + text);
//        
//        if(estado == -1) panelHilos.setText(previous + text);
//        else panelHilos.setText(previous + text2);
//        
//    }

}
