/**
 *
 * @author Daiza Vale 
 */
package AtividadeMapaResultadoExames;

import javax.swing.JOptionPane; 


public class AtividadeMapaResultadoExames {
    private static String nome;
    private static String tipoSanguineo;
    private static int anoNascimento;
    private static int idade;
    private static double resultadoGlicemia;
    private static boolean glicemiaFeito;
    private static boolean colesterolFeito;
    private static boolean triglicerideosFeito;
    private static double resultadoHDL;
    private static double resultadoLDL;
    private static String riscoColesterol;
    private static double resultadoTriglicerideos;
    private static String riscoTriglicerideos;
    private static String classificacaoHDL;

    public static void main(String[] args) {
        cadastrarPaciente(); //Criei o código usando a função cadastrarPaciente para que só fosse necessário preencher os dados do paciente uma única vez, independente da quantidade de exames à serem cadastrados.
        boolean mostrarResultado = false;

        while (!mostrarResultado) {
            int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Opções",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    new String[]{"Cadastrar Exame", "Mostrar Resultado"}, "Cadastrar Exame");

            if (escolha == 0) {
                escolherExames();
            } else if (escolha == 1) {
                if (glicemiaFeito || colesterolFeito || triglicerideosFeito) {
                    mostrarResultado();
                    mostrarResultado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum exame foi realizado ainda.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                break; // Sai do loop se cancelar a seleção
            }
        }
    }

    private static void cadastrarPaciente() {
        nome = JOptionPane.showInputDialog("Digite o nome do paciente:");
        tipoSanguineo = JOptionPane.showInputDialog("Digite o tipo sanguíneo do paciente:");
        anoNascimento = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de nascimento do paciente:"));
        idade = calcularIdade(anoNascimento);
    }

    private static int calcularIdade(int anoNascimento) {
        int anoAtual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return anoAtual - anoNascimento;//A idade já fica salva de acordo com o ano, ignorando o dia e mês.
    }

    private static void escolherExames() {
        String[] exames = new String[4];
        exames[0] = "Glicemia";
        exames[1] = "Colesterol";
        exames[2] = "Triglicerídeos";
        exames[3] = "Voltar"; //Opção de voltar, para caso o usuário não queira cadastrar todos os exames.

        while (!(glicemiaFeito && colesterolFeito && triglicerideosFeito)) {
            int opcoesRestantes = 3; 

            if (glicemiaFeito) {
                exames[0] = "";
                opcoesRestantes--;
            }
            if (colesterolFeito) {
                exames[1] = "";
                opcoesRestantes--;
            }
            if (triglicerideosFeito) {
                exames[2] = "";
                opcoesRestantes--;
            }

            int escolherExame = JOptionPane.showOptionDialog(null, "Escolha um exame para ser cadastrado:", "Escolha de Exame", 
                                                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, exames, exames[0]);

            if (escolherExame == 0) {
                realizarExameGlicemia();
            } else if (escolherExame == 1) {
                realizarExameColesterol();
            } else if (escolherExame == 2) {
                realizarExameTriglicerideos();
            } else {
                break; 
            }
        }
    }

    private static void realizarExameGlicemia() {
        double valorGlicemia = Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de glicose mg/dL:"));
        resultadoGlicemia = valorGlicemia;
        glicemiaFeito = true;
    }

    private static void realizarExameColesterol() {
        double valorHDL = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do HDL (Colesterol bom) mg/dL:"));
        double valorLDL = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do LDL (Colesterol ruim) mg/dL:"));
        String risco = JOptionPane.showInputDialog("Digite o risco do paciente (B - baixo, M - médio, A - alto):");

        
        
        
        if (idade >= 0 && idade <= 19) {
            if (valorHDL > 45) {
                resultadoHDL = valorHDL;
                classificacaoHDL = "Bom";
            } else {
                resultadoHDL = valorHDL;
                classificacaoHDL = "Ruim";//Apesar do enunciado solicitar o HDL BOM, decidi que exibisse caso fosse Ruim.
            }
        } else {
            if (valorHDL > 40) {
                resultadoHDL = valorHDL;
                classificacaoHDL = "Bom";
            } else {
                resultadoHDL = valorHDL;
                classificacaoHDL = "Ruim";
            }
        }

        if (risco.equals("B")) {
            if (valorLDL < 100) {
                resultadoLDL = valorLDL;
                riscoColesterol = "Baixo Risco - Bom";
            } else {
                resultadoLDL = valorLDL;
                riscoColesterol = "Baixo Risco - Ruim";
            }
        } else if (risco.equals("M")) {
            if (valorLDL < 70) {
                resultadoLDL = valorLDL;
                riscoColesterol = "Médio Risco - Bom";
            } else {
                resultadoLDL = valorLDL;
                riscoColesterol = "Médio Risco - Ruim";
            }
        } else if (risco.equals("A")) {
            if (valorLDL < 50) {
                resultadoLDL = valorLDL;
                riscoColesterol = "Alto Risco - Bom";
            } else {
                resultadoLDL = valorLDL;
                riscoColesterol = "Alto Risco - Ruim";
            }
        } else {
            resultadoLDL = valorLDL;
            riscoColesterol = "Risco Indefinido";
        }

        colesterolFeito = true;
    }

    private static void realizarExameTriglicerideos() {
        double valorTriglicerideos = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de Triglicerídeos mg/dL (Jejum):"));
//Apesar do enunciado não solicitar a classificação de desejável ou fora do desejável para o quesito Triglicerídeo, adicionei isto ao código.
        
        
        if (idade >= 0 && idade <= 9) {
            if (valorTriglicerideos < 75) {
                resultadoTriglicerideos = valorTriglicerideos;
                riscoTriglicerideos = "Desejável";
            } else {
                resultadoTriglicerideos = valorTriglicerideos;
                riscoTriglicerideos = "Fora do Desejável";
            }
        } else if (idade >= 10 && idade <= 19) {
            if (valorTriglicerideos < 90) {
                resultadoTriglicerideos = valorTriglicerideos;
                riscoTriglicerideos = "Desejável";
            } else {
                resultadoTriglicerideos = valorTriglicerideos;
                riscoTriglicerideos = "Fora do Desejável";
            }
        } else {
            if (valorTriglicerideos < 150) {
                resultadoTriglicerideos = valorTriglicerideos;
                riscoTriglicerideos = "Desejável";
            } else {
                resultadoTriglicerideos = valorTriglicerideos;
                riscoTriglicerideos = "Fora do Desejável";
            }
        }

        triglicerideosFeito = true;
    }

    private static void mostrarResultado() {
        String resultado = "Nome: " + nome + "\nIdade: " + idade + "\nTipo Sanguíneo: " + tipoSanguineo;

        if (glicemiaFeito) {
            resultado += "\nResultado Glicemia: " + resultadoGlicemia + " (" + classificarGlicemia(resultadoGlicemia) + ")";
        }
        
        if (colesterolFeito) {
            resultado += "\nResultado HDL: " + resultadoHDL + " mg/dL (" + classificacaoHDL + ")";
            resultado += "\nResultado LDL: " + resultadoLDL + " mg/dL (" + riscoColesterol + ")";
        }
        if (triglicerideosFeito) {
            resultado += "\nResultado Triglicerídeos: " + resultadoTriglicerideos + " mg/dL (" + riscoTriglicerideos + ")";
        }

        JOptionPane.showMessageDialog(null, resultado, "Resultado dos Exames", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String classificarGlicemia(double valorGlicemia) {
        if (valorGlicemia < 100) {
            return "Normoglicemia";
        } else if (valorGlicemia >= 100 && valorGlicemia < 126) {
            return "Pré-diabetes";
        } else {
            return "Diabetes estabelecido";
        }
    }
}