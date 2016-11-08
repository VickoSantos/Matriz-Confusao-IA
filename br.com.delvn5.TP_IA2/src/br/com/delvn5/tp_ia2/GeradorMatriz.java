/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.delvn5.tp_ia2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author vini0
 */
public class GeradorMatriz{
    
    public static void escreverArquivo(File arquivo, int[] real, int[][]matriz) throws IOException{
        FileWriter escritor = new FileWriter(arquivo);
        BufferedWriter buffEscritor = new BufferedWriter(escritor);
        String linha = "";
        linha = "Natação | Vôlei | Pólo-Aquático | Total";
        buffEscritor.append(linha);
        buffEscritor.newLine();
        
        for(int i=0; i<3; i++){
            buffEscritor.append("   "+matriz[i][0]+"    |   "+matriz[i][1]+"   "
                    + "|       "+matriz[i][2]+"       |   "+real[i]+"\n");
            buffEscritor.newLine();
        }
        buffEscritor.close();
    }
    
    public static void gerarMatriz(int[] real, int[][] matriz){
        System.out.println("Natação | Vôlei | Pólo-Aquático | Total");
        for(int i=0; i<3; i++){
            System.out.println("   "+matriz[i][0]+"    |   "+matriz[i][1]+"   "
                    + "|       "+matriz[i][2]+"       |   "+real[i]);
        }
    }
}
