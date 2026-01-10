import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

class Flashcard {
    String question;
    String answer;

    Flashcard(String q, String a) {
        question = q;
        answer = a;
    }
}

public class FlashcardApp extends JFrame {

    ArrayList<Flashcard> cards = new ArrayList<>();
    int index = 0;

    JLabel questionLabel;
    JTextArea answerArea;

    public FlashcardApp() {

        setTitle("Flashcard Quiz App");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main background
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Card panel (Flashcard look)
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(180, 180, 180), 2),
                new EmptyBorder(20, 20, 20, 20)
        ));

        questionLabel = new JLabel("No Flashcards Available", JLabel.CENTER);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        questionLabel.setForeground(new Color(33, 37, 41));

        answerArea = new JTextArea();
        answerArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setEditable(false);
        answerArea.setBackground(new Color(248, 249, 250));
        answerArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        cardPanel.add(questionLabel, BorderLayout.NORTH);
        cardPanel.add(answerArea, BorderLayout.CENTER);

        // Buttons
        JButton prevBtn = createButton("Previous");
        JButton showBtn = createButton("Show Answer");
        JButton nextBtn = createButton("Next");
        JButton addBtn = createButton("Add Flashcard");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(new Color(245, 247, 250));
        btnPanel.add(prevBtn);
        btnPanel.add(showBtn);
        btnPanel.add(nextBtn);
        btnPanel.add(addBtn);

        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Button actions
        addBtn.addActionListener(e -> addCard());
        showBtn.addActionListener(e -> showAnswer());
        nextBtn.addActionListener(e -> nextCard());
        prevBtn.addActionListener(e -> prevCard());
    }

    JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(new Color(13, 110, 253));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        return btn;
    }

    void addCard() {
        String q = JOptionPane.showInputDialog(this, "Enter Question:");
        String a = JOptionPane.showInputDialog(this, "Enter Answer:");

        if (q != null && a != null && !q.isEmpty() && !a.isEmpty()) {
            cards.add(new Flashcard(q, a));
            index = cards.size() - 1;
            questionLabel.setText(q);
            answerArea.setText("");
        }
    }

    void showAnswer() {
        if (!cards.isEmpty()) {
            answerArea.setText(cards.get(index).answer);
        }
    }

    void nextCard() {
        if (!cards.isEmpty()) {
            index = (index + 1) % cards.size();
            questionLabel.setText(cards.get(index).question);
            answerArea.setText("");
        }
    }

    void prevCard() {
        if (!cards.isEmpty()) {
            index = (index - 1 + cards.size()) % cards.size();
            questionLabel.setText(cards.get(index).question);
            answerArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashcardApp().setVisible(true));
    }
}
