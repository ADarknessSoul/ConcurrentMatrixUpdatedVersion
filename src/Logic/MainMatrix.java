package Logic;


import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gerar
 */
public class MainMatrix {
    
    int[][] Matrix1;
    int[][] Matrix2;
    int[] Matrix1a;
    int[][] Matrix2a;
    int[][] Matriz;
    Thread[] threads;
    
    int rows;
    int cols;
    int batchSize;
    
    public static void main(String[] args) throws InterruptedException {
        
//        MainMatrix mn = new MainMatrix();
//        mn.ConcurrentMatrix();
        
    }
    
    public MainMatrix() {
        
        
        
    }
    
    public void ConcurrentMatrix() throws InterruptedException {
        
//        batchSize = 8;
//        
//        Scanner entrada = new Scanner(System.in);
//        
//        System.out.println("Ingresa el número de filas: ");
//        rows = entrada.nextInt();
//        
//        System.out.println("Ingresa el número de columnas: ");
//        cols = entrada.nextInt();
//        
//        if(rows <= batchSize) threads = new Thread[rows];
//        else threads = new Thread[batchSize];
//        
//        initializeMatrix();
//        
//        Matriz = new int[rows][cols];
//        
//        initializeThreads();
////        
//        for(int i = 0; i < threads.length; i++) {
//            
//            threads[i].join();
//            
//        }
        

//////        
//        SerialMatrix sM = new SerialMatrix(rows, cols);
////        
//        Matriz = sM.CalculateMatrix();
////        
//        for(int i = 0; i < rows; i++) {
//            
//            for(int j = 0; j < cols; j++) {
//                
//                System.out.println("Posición: " + " " + i + " " + j + " : " + Matriz[i][j]);
//                
//            }
//            
//        }

        
    }
    
        public void initializeMatrix(int newRows, int newCols){
               
        Matrix1 = new int[rows][cols];
        Matrix2 = new int[rows][cols];

//        int offset = 0;
        
        for(int i = 0; i < rows; i++) {
            
            for(int j = 0; j < cols; j++) {
                
                Matrix1[i][j] = (j + 1);
                Matrix2[i][j] = (j + 1);
//                System.out.println("Valor de matriz1 en posición: " + i + " " + j + " : " + Matrix1[i][j]);
//                System.out.println("Valor de matriz2 en posición: " + i + " " + j + " : " + Matrix2[i][j]);
                
//                if(j + 1 == cols) offset = offset + (j + 1);
                
            }
            
        }
        
    }
        
//        public void initializeThreads() {
//            int numBatches = rows / batchSize;
//            int remainingRows = rows % batchSize;
//            int startIndex = 0, endIndex = 0;
//              
//              if(rows <= batchSize) {
//                  
//                int[][] batch = new int[1][cols];
//    
//                for(int i = 0; i < rows; i++) {
//                    
//                    for(int j = 0; j < cols; j++) {
//                        
//                        batch[0][j] = Matrix1[i][j]; 
//                        
//                    }
//                    
//                    startIndex = i;
//                    endIndex = startIndex;
//                    
//                    Matrix runnable = new Matrix(rows, cols, batch, Matrix2, startIndex, endIndex, this);
//                    Thread thread = new Thread(runnable);
//                    threads[i] = thread;
//                    thread.start();
//                    
//                }
//    
//              } 
//              else {
//    
//                 if(remainingRows == 0) {
//
//                    int[][] batch = new int[numBatches][cols];
//                    int newRows = -1;
//                    boolean flag = true; 
//                     
//                    for(int i = 0; i < batchSize; i++) {
//                         
//                        for(int j = 0; j < numBatches; j++) {                    
//                                                         
//                            newRows++;
//                            if(flag) {
//                                startIndex = newRows;
//                                flag = false;
//                            }
//                            endIndex = newRows;
//                                                
//                            for(int k = 0; k < cols; k++) {
//                                
//                                batch[j][k] = Matrix1[newRows][k];
//                                
//                            }
//
//                        }
//                         
//                    flag = true;
//                    Matrix runnable = new Matrix(rows, cols, batch, Matrix2, startIndex, endIndex, this);
//                    Thread thread = new Thread(runnable);
//                    threads[i] = thread;
//                    thread.start();
//                         
//                    }
//                                      
//                }
//                else {
//                                         
//                    int newRows = -1;
//                    int j = 0;
//                    boolean bandera = true;
//                    boolean flag = true;
//                    int numOfArrays = remainingRows;
//                    int batch[][] = new int[numBatches][cols];
//                    
//                    for(int i = 0; i < batchSize; i++) {    
//
//                        bandera = true;
//                        
//                        for(; j < numBatches; j++) { 
//                                                         
//                            newRows++;
//                            if(flag) {
//                                startIndex = newRows;
//                                flag = false;
//                            }
//                            endIndex = newRows;
//                                                
//                            for(int k = 0; k < cols; k++) {
//                                
//                                if(numOfArrays != 0 && bandera != false) {
//                                    
//                                    batch = new int[numBatches + 1][cols];
//                                    numOfArrays--;
//                                    bandera = false;
//                                    
//                                } 
//                                else if(bandera != false) {
//                                    
//                                    batch = new int[numBatches][cols];
//                                    bandera = false;
//                                    
//                                }
//                                batch[j][k] = Matrix1[newRows][k];
//                                
//                            }
//                            
//                            
//                        }
//                        
//                    if(remainingRows != 0) {
//                        
//                        newRows++;
//                        endIndex = newRows;
//                        
//                        for(int k = 0; k < cols; k++) {
//                              
//                              batch[j][k] = Matrix1[newRows][k];
//                              
//                        }  
//                        
//                        remainingRows--;
//                        
//                    }
//                    
//                    flag = true;    
//                    Matrix runnable = new Matrix(rows, cols, batch, Matrix2, startIndex, endIndex, this);
//                    Thread thread = new Thread(runnable);
//                    threads[i] = thread;
//                    thread.start();
//                    j = 0;
//                    bandera = true;
//                        
//                    }
//                   
//                }
//    
//              }
//                            
//        } //InitializeThreads
        
        public void joinArray(int[][] auxMatrix, int startIndex, int endIndex, int columns) {
            
            int i = 0;
            
//            System.out.println("Start: " + startIndex + ", end: " + endIndex);
            
            for(; startIndex <= endIndex; startIndex++) {
                
                
                for(int j = 0; j < columns; j++) {
                    
                    this.Matriz[startIndex][j] = auxMatrix[i][j];
//                    System.out.println("AuxMatrix: " + auxMatrix[i][j]);
                    
                }
                
                i++;
                
            }
            
        }    
}
