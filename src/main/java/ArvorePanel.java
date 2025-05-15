import javax.swing.*;
import java.awt.*;
class ArvorePanel extends JPanel {
    private ArvoreBinaria<Integer> arvore;
    public ArvorePanel(ArvoreBinaria<Integer> arvore) {
        this.arvore = arvore;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharArvore(g, getWidth() / 2, 50, arvore.getRaiz(), getWidth() / 4);
    }

    private void desenharArvore(Graphics g, int x, int y, No<Integer> no, int deslocamento) {
        if (no == null) return;

        g.setColor(new Color(100, 150, 250));
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(no.getConteudo().toString(), x - 5, y + 5);

        g.setColor(Color.BLACK);
        if (no.getNoEsquerdo() != null) {
            g.drawLine(x, y, x - deslocamento, y + 50);
            desenharArvore(g, x - deslocamento, y + 50, no.getNoEsquerdo(), deslocamento / 2);
        }

        if (no.getNoDireito() != null) {
            g.drawLine(x, y, x + deslocamento, y + 50);
            desenharArvore(g, x + deslocamento, y + 50, no.getNoDireito(), deslocamento / 2);
        }
    }
}
