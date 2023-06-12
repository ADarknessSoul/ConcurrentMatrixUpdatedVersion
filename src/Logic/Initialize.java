/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.awt.Label;
import javax.swing.JTextArea;
import javax.swing.JLabel;

/**
 *
 * @author gerar
 */
public class Initialize {
    
    int[][] MatrizSecuencial;
    int[][] MatrizConcurrente;
    int[][] Matriz;
    Thread[] threads;
    static boolean flag = true;
    static boolean flagTime = true;
    static int resultantTime = 0;
      
    public int[][] initalizeArray(int[][] Matrix, int rows, int cols) {

        //Se crea un arreglo con números random del 1 al 1000
        
        System.out.println("rows: " + rows + ", cols: " + cols + "\n");
        
        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {

                Matrix[i][j] = (int)(Math.random() * 100);             
            
            }

        }
        
        return Matrix;
        
    }
    
    public void initializeProcess(int rowsA, int colsA, int rowsB, int colsB, int[][] MatrizA, int[][] MatrizB, JTextArea panel, JLabel labelSecuencial) {
        
        //Se inicializan las matrices
        
        MatrizSecuencial = new int[rowsA][colsB];
        
        //Se inicia el cálculo secuencial
        SerialMatrix sM = new SerialMatrix(rowsA, colsA, rowsB, colsB, MatrizA, MatrizB, labelSecuencial);       
        MatrizSecuencial = sM.CalculateMatrix();
        
        System.out.println(Integer.toString(MatrizSecuencial[0][0]));
        
        //Se imprime el arreglo secuencial
        
        printArrays(MatrizSecuencial, panel);
        
    }
    
    public void initializeConcurrentProcess(int rowsA, int colsA, int rowsB, int colsB, int[][] MatrizA, int[][] MatrizB, JTextArea panel, JLabel labelConcurrente, int batchSize, JTextArea panelHilos) throws InterruptedException {
        
        //Se inicializa la matriz
        
        panelHilos.setText("");
        
        Matriz = new int[rowsA][colsB];
        
        //Se hace la inicialización del array de threads
        
        if(rowsA <= batchSize) threads = new Thread[rowsA];
        else threads = new Thread[batchSize];
        
        //Se inicia el reparto de hilos
        
        initializeThreads(rowsA, colsA, rowsB, colsB, batchSize, MatrizA, MatrizB, panel, labelConcurrente, this, panelHilos);
        
        //Se espera a que terminen su proceso todos los hilos
        
        for(int i = 0; i < threads.length; i++) {
            
            threads[i].join();
            
        }
        
        //Se imprimen los valores del array
        
        printArrays(Matriz, panel);
        labelConcurrente.setText(Integer.toString(resultantTime) + "ms"); 
        resultantTime = 0;
        
    }
    
    public void printArrays(int[][] matriz, JTextArea panel) {
        
        int length;
        
        if(matriz.length >= 2000) length = 5;
        else if(matriz.length >= 1000) length = 10;
        else if(matriz.length >= 50) length = 50;
        else length = matriz.length;
        
        panel.setText("");
        
        for (int i = 0; i < length; i++) {
            
            for (int j = 0; j < matriz[i].length; j++) {
                
                //Se escribe el valor actual de la matriz en el txtArea, dependiendo de su número de digitos se le da más espacios
                String textInPanel = panel.getText();
                panel.setText(textInPanel + Integer.toString(matriz[i][j]) + " ");
                
            }
            
            String textInPanel = panel.getText();
            panel.setText(textInPanel + "\n");
            
        }
      
    }
        
        public void initializeThreads(int rowsA, int colsA, int rowsB, int colsB, int batchSize, int[][] Matrix1, int[][] Matrix2, JTextArea panel, JLabel labelConcurrente, Initialize process, JTextArea panelHilos) {
            int numBatches = rowsA / batchSize;
            int remainingRows = rowsA % batchSize;
            int startIndex = 0, endIndex = 0;
              
              if(rowsA <= batchSize) {
                  
                int[][] batch = new int[1][colsA];
    
                for(int i = 0; i < rowsA; i++) {
                    
                    for(int j = 0; j < colsA; j++) {
                        
                        batch[0][j] = Matrix1[i][j]; 
                        
                    }
                    
                    startIndex = i;
                    endIndex = startIndex;
                    
                    Matrix runnable = new Matrix(rowsA, colsA, rowsB, colsB, batch, Matrix2, startIndex, endIndex, labelConcurrente, process, panelHilos);
                    Thread thread = new Thread(runnable);
                    threads[i] = thread;
                    thread.start();
                    
                }
    
              } 
              else {
    
                 if(remainingRows == 0) {

                    int[][] batch = new int[numBatches][colsA];
                    int newRows = -1;
                    boolean flag = true; 
                     
                    for(int i = 0; i < batchSize; i++) {
                         
                        for(int j = 0; j < numBatches; j++) {                    
                                                         
                            newRows++;
                            if(flag) {
                                startIndex = newRows;
                                flag = false;
                            }
                            endIndex = newRows;
                                                
                            for(int k = 0; k < colsA; k++) {
                                
                                batch[j][k] = Matrix1[newRows][k];
                                
                            }

                        }
                        
                    flag = true;
                    Matrix runnable = new Matrix(rowsA, colsA, rowsB, colsB, batch, Matrix2, startIndex, endIndex, labelConcurrente, process, panelHilos);
                    Thread thread = new Thread(runnable);
                    threads[i] = thread;
                    thread.start();
                         
                    }
                                      
                }
                else {
                                         
                    int newRows = -1;
                    int j = 0;
                    boolean bandera = true;
                    boolean flag = true;
                    int numOfArrays = remainingRows;
                    int batch[][] = new int[numBatches][colsA];
                    
                    for(int i = 0; i < batchSize; i++) {    

                        bandera = true;
                        
                        for(; j < numBatches; j++) { 
                                                         
                            newRows++;
                            if(flag) {
                                startIndex = newRows;
                                flag = false;
                            }
                            endIndex = newRows;
                                                
                            for(int k = 0; k < colsA; k++) {
                                
                                if(numOfArrays != 0 && bandera != false) {
                                    
                                    batch = new int[numBatches + 1][colsA];
                                    numOfArrays--;
                                    bandera = false;
                                    
                                } 
                                else if(bandera != false) {
                                    
                                    batch = new int[numBatches][colsA];
                                    bandera = false;
                                    
                                }
                                batch[j][k] = Matrix1[newRows][k];
                                
                            }
                            
                            
                        }
                        
                    if(remainingRows != 0) {
                        
                        newRows++;
                        endIndex = newRows;
                        
                        for(int k = 0; k < colsA; k++) {
                              
                              batch[j][k] = Matrix1[newRows][k];
                              
                        }  
                        
                        remainingRows--;
                        
                    }
                    
                    flag = true;    
                    Matrix runnable = new Matrix(rowsA, colsA, rowsB, colsB, batch, Matrix2, startIndex, endIndex, labelConcurrente, process, panelHilos);
                    Thread thread = new Thread(runnable);
                    threads[i] = thread;
                    thread.start();
                    j = 0;
                    bandera = true;
                        
                    }
                   
                }
    
              }
                            
        } //InitializeThreads
        
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
        
        public synchronized void printStatusOfThreads(JTextArea panelHilos, String texto) throws InterruptedException {
            
            if(flag == false) wait();
            
            flag = false;
            
            String textoPrevio = panelHilos.getText();
            if(texto.contains("tiempo")) panelHilos.setText(textoPrevio + "                                                                  " + texto);
            else panelHilos.setText(textoPrevio + texto);
            
            flag = true;
            
            notifyAll();
            
        }
        
        public synchronized void setFinalTimeConcurrent(int time) throws InterruptedException {
            
            if(flagTime == false) wait();
            
            flagTime = false;
            
            if(time > resultantTime) {
                
                resultantTime = time;
                
            }
            
            flagTime = true;
            
            notifyAll();
            
        }
    
}
