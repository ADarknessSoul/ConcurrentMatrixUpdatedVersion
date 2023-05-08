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
public final class SerialMatrix {
    
    int[][] Matrix1;
    int[][] Matrix2;
    int[][] auxMatrix;
    long time1;
    long time2;
    int rowsA;
    int colsA;
    int rowsB;
    int colsB;
    JLabel labelSecuencial;
    
    public SerialMatrix() {
        
        
        
    }
    
    public SerialMatrix(int rowsA, int colsA, int rowsB, int colsB, int[][] MatrizSecuencial, int[][] MatrizConcurrente, JLabel labelSecuencial) {
        
        this.rowsA = rowsA;
        this.colsA = colsA;
        this.rowsB = rowsB;
        this.colsB = colsB;
        
        Matrix1 = MatrizSecuencial;
        Matrix2 = MatrizConcurrente;
        auxMatrix = new int[rowsA][colsB];
        
        this.labelSecuencial = labelSecuencial;
        
    }
    
    public int[][] CalculateMatrix() {
        
        //time1 = System.nanoTime(); 
        final long time1M = System.currentTimeMillis();
        
        for(int i = 0; i < rowsA; i++) {
            
            for(int j = 0; j < colsB; j++) {
                
//                auxMatrix[i][j] = Matrix1[i][0] * Matrix2[0][j] + Matrix1[i][1] * Matrix2[1][j] + Matrix1[i][2] * Matrix2[2][j] + Matrix1[i][3] * Matrix2[3][j]; 
                  auxMatrix[i][j] = multiplyMatrix(i, j);
                
            }
            
        }
        
        //time2 = System.nanoTime();
        final long time2M = System.currentTimeMillis();
        
        //time1 = time2 - time1;
        final long resultTime = time2M - time1M;

//        System.out.println("El tiempo de ejecución fue: " + time1);
        //System.out.println("El tiempo de ejecución del secuencial fue: " + time1M + "ms");
        
        this.labelSecuencial.setText("Tiempo: " + Integer.toString((int) resultTime) + "ms");
        
        return auxMatrix;
        
    }
    
    public int multiplyMatrix(int i, int j) {
        
        int res = 0;
        int aux = 0;
        
        while(aux < colsA){
            
            res = res + (Matrix1[i][aux] * Matrix2[aux][j]);
            aux++;
            
        }
        
        return res;
        
    }
   
    
}
