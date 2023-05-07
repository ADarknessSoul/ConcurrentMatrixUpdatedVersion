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
    long time1;
    long time1M;
    long time2;
    long time2M;
    int rows;
    int cols;
    int startIndex;
    int endIndex;
    MainMatrix mainm;
    
    public Matrix(int rows, int cols, int[][] Matrix1a, int[][] Matrix2a, int startIndex, int endIndex, MainMatrix mainm) {
        
        this.rows = rows;
        this.cols = cols;
        this.Matrix1a = Matrix1a;
        this.Matrix2a = Matrix2a;
        this.auxMatrix = new int[Matrix1a.length][cols];
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.mainm = mainm;
//        initializeMatrix();
        
    }
    
    @Override
    public void run() {
                
                time1 = System.nanoTime(); 
                time1M = System.currentTimeMillis();
        
//                printMatrixes();
                
                for(int i = 0; i < Matrix1a.length; i++) {
                    
                    for(int j = 0; j < cols; j++) {
                        
                        auxMatrix[i][j] = multiplyMatrix(i, j);
//                        System.out.println("Valor de la matriz final en posición " + j + " : " + auxMatrix[i][j]);
                        
                    }                       
                        
                }
                
                mainm.joinArray(auxMatrix, startIndex, endIndex, cols);
                

                time2 = System.nanoTime();
                time2M = System.currentTimeMillis();

                time1 = time2 - time1;
                time1M = time2M - time1M;

//                System.out.println("Thread: " + Thread.currentThread().getName() + ", time: " + time1 + "ns");
                System.out.println("Thread: " + Thread.currentThread().getName() + ", time: " + time1M + "ms");
       
    }

    
    public int multiplyMatrix(int i, int j) {
        
        int res = 0;
        int aux = 0;
        
        while(aux < cols){
            
            res = res + (Matrix1a[i][aux] * Matrix2a[aux][j]);
            aux++;
            
        }
        
        return res;
        
    }
    
    public void printMatrixes() {
        
        System.out.println("Thread: " + Thread.currentThread().getName() + ", length: " + Matrix1a.length);
        
//        for(int i = 0; i < Matrix1a.length; i++) {
//            
//            for(int j = 0; j < cols; j++) {
//                
////                System.out.println("Thread: " + Thread.currentThread().getName() + " ,value: " + Matrix1a[i][j] + ", pos i: " + i + ", pos j: " + j + "\n");           
//                
//            }       
//            
//        }
        
    }
    

}
