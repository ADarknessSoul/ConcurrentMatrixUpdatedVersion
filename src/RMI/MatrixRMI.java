package RMI;

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
public final class MatrixRMI implements Runnable{

    int[][] Matrix1a;
    int[][] Matrix2a;
    public int[][] auxMatrix;
    
    int[][] MatrizNueva;
    int rowsA;
    int colsA;
    int rowsB;
    int colsB;
    int startIndex;
    int endIndex;
    int actualClient;
    int threadNumber;
    int clientStartIndex;
    MiClaseRemota process;
    
    public MatrixRMI(int rowsA, int colsA, int rowsB, int colsB, int[][] Matrix1Concurrent, int[][] Matrix2a, int startIndex, int endIndex, int actualClient, MiClaseRemota process, int threadNumber, int clientStartIndex) {
   
        this.rowsA = rowsA;
        this.colsA = colsA;
        this.rowsB = rowsB;
        this.colsB = colsB;
        
        this.Matrix2a = new int[rowsB][colsB];
        this.Matrix2a = Matrix2a;
        
        this.MatrizNueva = new int[Matrix1Concurrent.length][Matrix1Concurrent[0].length];
        
        for(int i = 0; i < Matrix1Concurrent.length; i++) {
            
            for(int j = 0; j < Matrix1Concurrent[i].length; j++) {
                
                MatrizNueva[i][j] = Matrix1Concurrent[i][j];
                
            }
            
        }
        
        this.auxMatrix = new int[MatrizNueva.length][colsB];
        this.startIndex = startIndex;
        this.clientStartIndex = clientStartIndex;
        this.endIndex = endIndex;
        this.actualClient = actualClient;
        this.process = process;
        this.threadNumber = threadNumber;
        
    }
    
    @Override
    public void run() {
        
        String texto;
            
//        try {
//            process.printStatusOfThreads(panelHilos, texto);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
//        }

        final long time1M = System.currentTimeMillis();
                
        for(int i = 0; i < MatrizNueva.length; i++) {
                    
            for(int j = 0; j < colsB; j++) {
                        
                auxMatrix[i][j] = multiplyMatrix(i, j);
                
//                       
                        
            }                       
                        
        }
        
        //if("Thread-0".equals(Thread.currentThread().getName())) System.out.println("Rows: " + rowsA + ", cols: " + colsA + ", auxMatrix length" + auxMatrix.length + ", Matriz nueva length: " + MatrizNueva.length);

//        process.joinArray(auxMatrix, startIndex, endIndex, auxMatrix[0].length);
        
        process.joinArray(auxMatrix, startIndex , endIndex , clientStartIndex, actualClient, threadNumber, auxMatrix[0].length);

        final long time2M = System.currentTimeMillis();
        
        final long timeResult = time2M - time1M;   
               
        //Se imprime el estado finalizado del hilo
            
        texto = "Thread: " + Thread.currentThread().getName() + ", tiempo: " + Integer.toString((int) timeResult) + "ms\n" + ", desde el cliente: " + actualClient;
        
        
        try {
            process.setFinalTimeRMI((int) timeResult);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        
        //System.out.println(texto);
        
        
        
//        try {
//            process.printStatusOfThreads(panelHilos, texto);
//            //System.out.println("Thread: " + Thread.currentThread().getName() + ", tiempo: " + timeResult);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        try {
//            process.setFinalTimeConcurrent((int) timeResult);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
       
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
    
    public int[][] getAuxMatrix() {
        
        return this.auxMatrix;
        
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
