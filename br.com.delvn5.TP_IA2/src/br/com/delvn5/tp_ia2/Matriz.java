package br.com.delvn5.tp_ia2;

/**
 *
 * @author vini0
 */
public class Matriz {

    private final double[] real;
    private final double[] classificado;

    public Matriz(double[] real, double[] classificado) {
        this.real = real;
        this.classificado = classificado;
    }

    public int[] quantidadeRealClasse() {
        
        int countNatacao = 0;
        int countVolei = 0;
        int countPolo = 0;
        for (int i = 0; i < this.real.length; i++) {
            if (this.real[i] == 0.0) {
                countNatacao++;
            } else if (this.real[i] == 1.0) {
                countVolei++;
            } else {
                countPolo++;
            }
        }
        
        int[] classe = new int[]{countNatacao, countVolei, countPolo};
        return classe;
    }

    public int[][] mCount() {
        
        int[][] mClassificados;
        mClassificados = new int [][]{this.countVpNatacao(),
                    this.countVpVolei(),
                    this.countVpPolo()};
               
        return mClassificados;
    }
    
    /*
     * Este conjunto de classes (as 3 seguintes) retornam um conjunto de dados
     * que é um array conténdo primeiramente:
        * o VP daquela classe
        * o FP das demais classes
        * Estão na ordem: natação, vôlei e polo-aquático.
     */
    private int[] countVpNatacao(){
        int count = 0;
        int countVolei = 0;
        int countPolo = 0;
        for(int i=0; i<this.real.length; i++){
            if(this.classificado[i]==0.0){
                if(this.classificado[i]==this.real[i]){
                    count++;
                }else if(this.real[i]==1.0){
                    countVolei++;
                }else{
                    countPolo++;
                }
            }
        }
        
        int[] classe = new int[]{count, countVolei, countPolo};
        return classe;
    }
    
    private int[] countVpVolei(){
        int count = 0;
        int countNatacao = 0;
        int countPolo = 0;
        for(int i=0; i<this.real.length; i++){
            if(this.classificado[i]==1.0){
                if(this.classificado[i]==this.real[i]){
                    count++;
                }else if(this.real[i]==0.0){
                    countNatacao++;
                }else{
                    countPolo++;
                }
            }
        }
        
        int[] classe = new int[]{countNatacao, count, countPolo};
        return classe;
    }
    
    private int[] countVpPolo(){
        int count = 0;
        int countVolei = 0;
        int countNatacao = 0;
        for(int i=0; i<this.real.length; i++){
            if(this.classificado[i]==2.0){
                if(this.classificado[i]==this.real[i]){
                    count++;
                }else if(this.real[i]==0.0){
                    countNatacao++;
                }else{
                    countVolei++;
                }
            }
        }
        
        int[] classe = new int[]{countNatacao, countVolei, count};
        return classe;
    }
    
    public double calcularPrecisao(double vp, double total) {
        return vp / total;
    }

    public double calcularRevocacao(double vp, double totalClassificado) {
        return vp / totalClassificado;
    }

    public double calcularAcuracia(double vpvn, int n) {
        return vpvn / n;
    }

    public double taxaDeErro(double fpfn, int n) {
        return fpfn / n;
    }
    
}
