
package br.com.delvn5.tp_ia2;

import java.io.File;
import java.io.FileReader;
import java.util.Random;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author vini0
 */
public class BrComDelvn5TP_IA2 {

    /**
     * @param args the command line arguments
     */
    public static int ITERACOES = 1;
    public static int PARTICOES = 3;
    
    public static void main(String[] args) throws Exception{
        
        FileReader leitorArff = new FileReader("base/AtletasAdolescentes.arff");
        Instances atletas = new Instances(leitorArff);
        atletas.setClassIndex(8);
        atletas = atletas.resample(new Random());
        
        double[] real = new double[11];
        double[] vizinhoClass = new double[11];
        double[] knnClass = new double[11];
        
        for(int i=0; i<ITERACOES; i++){
            Instances atletasTreino = atletas.trainCV(PARTICOES, i);
            Instances atletasTeste = atletas.testCV(PARTICOES, i);
            
            IBk vizinho = new IBk();
            IBk knn = new IBk(3);
            
            vizinho.buildClassifier(atletasTreino);
            knn.buildClassifier(atletasTreino);
            
            for(int j = 0; j<atletasTeste.numInstances(); j++){
                Instance atletasExemplo = atletasTeste.instance(j);
                real[j] = atletasExemplo.value(8);
                
                atletasExemplo.setClassMissing();
                vizinhoClass[j] = vizinho.classifyInstance(atletasExemplo);
                knnClass[j] = knn.classifyInstance(atletasExemplo);
            }
        }
        
        //Objeto para criação da matriz onde será apresentada os dados do Vizinho
        Matriz matriz = new Matriz(real, vizinhoClass);
        
        //Retorna um array com a quantidade real de cada classe
        //onde 0 é natação, 1, vôlei e 2, pólo-aquático
        int res[] = matriz.quantidadeRealClasse();
        
        //Recebe uma matriz contendo os vps e fps de cada classe
        int[][] matrizClass = matriz.mCount();
        
        //Cria um caminho para o arquivo
        File arquivo = new File("base/matrizdeatletas.txt");
        
        //Se o arquivo não existir, cria um arquivo com esse nome.
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }
        
        //Gera uma matriz no arquivo existente
        GeradorMatriz.escreverArquivo(arquivo, res, matrizClass);
        
        //Gera uma matriz na execução do arquivo
        GeradorMatriz.gerarMatriz(res, matrizClass);
        
        int natacaoVP = matrizClass[0][0];
        int voleiVp = matrizClass[1][1];
        int poloVp = matrizClass[2][2];
        int[] totalClassificado = new int[3];
        int n = 0;
        //Traz a quantidade classificada em cada classe.
        for(int k=0; k<3; k++){
            int total = 0;
            for(int l=0; l<3; l++){
                total += matrizClass[l][k];
            }
            totalClassificado[k] = total;
            n+=res[k];
        }
        
        System.out.println("Precisão natação: "+matriz.calcularPrecisao(natacaoVP, res[0]));
        System.out.println("Precisão vôlei: "+matriz.calcularPrecisao(voleiVp, res[1]));
        System.out.println("Precisão pólo-aquático: "+matriz.calcularPrecisao(poloVp, res[2]));
        System.out.println("Revocação natação: "+matriz.calcularRevocacao(natacaoVP, totalClassificado[0]));
        System.out.println("Revocação vôlei: "+matriz.calcularRevocacao(voleiVp, totalClassificado[1]));
        System.out.println("Revocação pólo-aquático: "+matriz.calcularRevocacao(poloVp, totalClassificado[2]));
        double acuracia = matriz.calcularAcuracia((natacaoVP+voleiVp+poloVp), n);
        System.out.println("Acurácia: "+acuracia);
        System.out.println("Taxa de erro: "+matriz.taxaDeErro(n-(natacaoVP+voleiVp+poloVp), n));
        
        Matriz matriz2 = new Matriz(real, knnClass);
        
        int [][]matrizCl2 = matriz2.mCount();
        GeradorMatriz.gerarMatriz(res, matrizCl2);
        
        int natVp = matrizCl2[0][0];
        int volVp = matrizCl2[1][1];
        int polVp = matrizCl2[2][2];
        int[] totalClassificado2 = new int[3];
        for(int l=0; l<3; l++){
            int total = 0;
            for(int m=0; m<3; m++){
                total += matrizClass[m][l];
            }
            totalClassificado2[l] = total;
        }
        
        System.out.println("Precisão natação: "+matriz2.calcularPrecisao(natVp, res[0]));
        System.out.println("Precisão vôlei: "+matriz2.calcularPrecisao(volVp, res[1]));
        System.out.println("Precisão pólo-aquático: "+matriz2.calcularPrecisao(polVp, res[2]));
        System.out.println("Revocação natação: "+matriz2.calcularRevocacao(natVp, totalClassificado2[0]));
        System.out.println("Revocação vôlei: "+matriz2.calcularRevocacao(volVp, totalClassificado2[1]));
        System.out.println("Revocação pólo-aquático: "+matriz2.calcularRevocacao(polVp, totalClassificado2[2]));
        double acuracia2 = matriz2.calcularAcuracia((natVp+volVp+polVp), n);
        System.out.println("Acurácia: "+acuracia2);
        System.out.println("Taxa de erro: "+matriz2.taxaDeErro(n-(natVp+volVp+polVp), n));
    }  
    
}
