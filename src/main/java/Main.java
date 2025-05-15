import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Árvore Binária");
            ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
            ArvorePanel painel = new ArvorePanel(arvore);

            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel controle = new JPanel();
            controle.setLayout(new BoxLayout(controle, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(controle);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(800, 100));

            JTextField campoValor = new JTextField(5);
            JButton botaoAdicionar = new JButton("Adicionar");
            JButton botaoGrauNo = new JButton("Grau do Nó");
            JButton botaoGrauArvore = new JButton("Grau da Árvore");
            JButton botaoProfundidadeNo = new JButton("Profundidade do Nó");
            JButton botaoProfundidadeArvore = new JButton("Profundidade da Árvore");
            JButton botaoAlturaNo = new JButton("Altura do Nó");
            JButton botaoAlturaArvore = new JButton("Altura da Árvore");
            JButton botaoNivelNo = new JButton("Nível do Nó");
            JButton botaoNivelArvore = new JButton("Nível da Árvore");
            JButton botaoNosFolhas = new JButton("Nós Folhas");
            JButton botaoPreOrdem = new JButton("Pré-Ordem");
            JButton botaoInOrdem = new JButton("In-Ordem");
            JButton botaoPosOrdem = new JButton("Pós-Ordem");
            JButton botaoBuscar = new JButton("Buscar Nó");
            JButton botaoFatorBalanceamento = new JButton("Fator de Balanceamento");
            JButton botaoBalanceada = new JButton("Está Balanceada?");


            botaoAdicionar.addActionListener(e -> {
                try {
                    int valor = Integer.parseInt(campoValor.getText());
                    arvore.inserir(valor);
                    painel.repaint();
                    campoValor.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número inteiro válido!");
                }
            });

            botaoGrauNo.addActionListener(e -> {
                try {
                    int valor = Integer.parseInt(campoValor.getText());
                    int grau = arvore.grauNo(valor);
                    JOptionPane.showMessageDialog(frame, "Grau do nó " + valor + ": " + grau);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número inteiro válido!");
                }
            });

            botaoGrauArvore.addActionListener(e -> {
                int grauArvore = arvore.grauArvore();
                JOptionPane.showMessageDialog(frame, "Grau da árvore: " + grauArvore);
            });

            botaoProfundidadeNo.addActionListener(e -> {
                try {
                    int valor = Integer.parseInt(campoValor.getText());
                    int profundidade = arvore.profundidadeNo(valor);
                    JOptionPane.showMessageDialog(frame, "Profundidade do nó " + valor + ": " + profundidade);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número inteiro válido!");
                }
            });

            botaoProfundidadeArvore.addActionListener(e -> {
                int profundidade = arvore.profundidadeArvore();
                JOptionPane.showMessageDialog(frame, "Profundidade da árvore: " + profundidade);
            });

            botaoAlturaNo.addActionListener(e -> {
                try {
                    int valor = Integer.parseInt(campoValor.getText());
                    int altura = arvore.alturaNo(valor);
                    if (altura == -1) {
                        JOptionPane.showMessageDialog(frame, "O nó " + valor + " não existe na árvore.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Altura do nó " + valor + ": " + altura);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número inteiro válido!");
                }
            });

            botaoAlturaArvore.addActionListener(e -> {
                int altura = arvore.alturaArvore();
                JOptionPane.showMessageDialog(frame, "Altura da árvore: " + altura);
            });


            botaoNivelNo.addActionListener(e -> {
                try {
                    int valor = Integer.parseInt(campoValor.getText());
                    int nivel = arvore.nivelNo(valor);
                    if (nivel != -1) {
                        JOptionPane.showMessageDialog(frame, "Nível do nó " + valor + ": " + nivel);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Nó não encontrado!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número inteiro válido!");
                }
            });

            botaoNivelArvore.addActionListener(e -> {
                int nivelArvore = arvore.nivelArvore();
                JOptionPane.showMessageDialog(frame, "Nível da árvore: " + nivelArvore);
            });

            botaoNosFolhas.addActionListener(e -> {
                List<Integer> folhas = arvore.nosFolhas();
                JOptionPane.showMessageDialog(frame, "Nós folhas: " + folhas);
            });

            botaoPreOrdem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Pré-Ordem: " + arvore.preOrdem()));

            botaoInOrdem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "In-Ordem: " + arvore.inOrdem()));

            botaoPosOrdem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Pós-Ordem: " + arvore.posOrdem()));

            botaoBuscar.addActionListener(e -> {
                String valor = JOptionPane.showInputDialog(frame, "Digite o valor do nó:");
                if (valor != null) {
                    JOptionPane.showMessageDialog(frame, arvore.buscarInformacoes(Integer.parseInt(valor)));
                }
            });

            botaoFatorBalanceamento.addActionListener(e -> {
                String fatores = arvore.mostrarFatoresBalanceamento();
                JOptionPane.showMessageDialog(frame, fatores);
            });

            botaoBalanceada.addActionListener(e -> {
                boolean balanceada = arvore.estaBalanceada();
                String mensagem = balanceada ? "A árvore está balanceada!" : "A árvore NÃO está balanceada.";
                JOptionPane.showMessageDialog(frame, mensagem);
            });



            controle.add(new JLabel("Valor:"));
            controle.add(campoValor);
            controle.add(botaoAdicionar);
            controle.add(botaoGrauNo);
            controle.add(botaoGrauArvore);
            controle.add(botaoProfundidadeNo);
            controle.add(botaoProfundidadeArvore);
            controle.add(botaoAlturaNo);
            controle.add(botaoAlturaArvore);
            controle.add(botaoNivelNo);
            controle.add(botaoNivelArvore);
            controle.add(botaoNosFolhas);
            controle.add(botaoPreOrdem);
            controle.add(botaoInOrdem);
            controle.add(botaoPosOrdem);
            controle.add(botaoBuscar);
            controle.add(botaoFatorBalanceamento);
            controle.add(botaoBalanceada);

            frame.add(painel, BorderLayout.CENTER);
            frame.add(scrollPane, BorderLayout.NORTH);


            frame.setVisible(true);
        });
    }
}
