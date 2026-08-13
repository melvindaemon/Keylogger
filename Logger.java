import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Logger extends JFrame implements KeyListener {
    private JLabel l1;
    private char key_char;
    private String out_f;

    public Logger() {
        l1 = new JLabel("Press any key...", JLabel.CENTER);
        out_f = "";

        setTitle("Daemon Keylogger");
        setSize(400, 100);
        add(l1);
        addKeyListener(this);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        saveLog();
    }

    public void saveLog() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Files.writeString(Path.of("log.txt"), out_f);

                    System.out.println("File saved...");
                } catch(IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
                dispose();
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
        key_char = e.getKeyChar();
        out_f = out_f + key_char;

        l1.setText(String.valueOf(key_char));
    }
}