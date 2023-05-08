package Logic;

import javax.swing.JLabel;

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
    Initialize process;
    
    public Matrix(int rowsA, int colsA, int rowsB, int colsB, int[][] Matrix1Concurrent, int[][] Matrix2a, int startIndex, int endIndex, JLabel labelConcurrente, Initialize process) {
        
        this.rowsA = rowsA;
        this.colsA = colsA;
        this.rowsB = rowsB;
        this.colsB = colsB;
        
        this.Matrix1a = new int[Matrix1Concurrent.length][Matrix1Concurrent[0].length];
        this.Matrix2a = new int[rowsB][colsB];
        
        this.Matrix1a = Matrix1Concurrent;
        this.Matrix2a = Matrix2a;
        
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

        final long time1M = System.currentTimeMillis();
                
        for(int i = 0; i < MatrizNueva.length; i++) {
                    
            for(int j = 0; j < colsB; j++) {
                        
                auxMatrix[i][j] = multiplyMatrix(i, j);
//                       
                        
            }                       
                        
        }
               
        final long time2M = System.currentTimeMillis();

        final long timeResult = time2M - time1M;
        
        process.joinArray(auxMatrix, startIndex, endIndex, auxMatrix[0].length);
        
        labelConcurrente.setText("Tiempo: " + Integer.toString((int) timeResult) + "ms");
        //System.out.println("Thread: " + Thread.currentThread().getName() + ", FirstValue: " + Matrix1a[0][0] + ", FirstResult: " + auxMatrix[0][0]);
       
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

}
