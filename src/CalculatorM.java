import com.sun.jdi.PathSearchingVirtualMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class CalculatorM extends JFrame implements ActionListener {

    private DecimalFormat df = new DecimalFormat("#,###.00");

    private String[] symbols = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "Adv", "="
    };;

    private int operator = 0;

    private JPanel panel = new JPanel(new BorderLayout(5, 5));
    private JPanel btnPanel = new JPanel(new GridLayout(5, 3,2 ,2));
    private JButton[] btns = new JButton[20];
    private JTextArea screen = new JTextArea(5, 40);
    private double firstNum = 0, secondNum = 0;
    private JTextField CalculatingTf = new JTextField( 40);

    public CalculatorM(){
        init();
    }

    private void init() {

        screen.setFont(new Font("Times New Roman", Font.BOLD, 18));

        setTitle("Calculator");
        screen.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);
        btnPanel.setBackground(Color.BLACK);
        screen.setForeground(Color.WHITE);

        for(int i = 0; i < btns.length; i++){
                btns[i] = new JButton(symbols[i]);

                btns[i].setOpaque(false);
                btns[i].setBorderPainted(false);
                btns[i].setBackground(Color.BLACK);
                btns[i].setForeground(Color.WHITE);
                btns[i].addActionListener(this);

            // Adiciona o efeito de hover
            final int index = i;
            btns[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btns[index].setFont(new Font("Arial", Font.BOLD, 20)); // Fonte maior ao passar o mouse
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btns[index].setFont(new Font("Arial", Font.PLAIN, 18)); // Fonte original ao sair
                }
            });
                btnPanel.add(btns[i]);

        }
        CalculatingTf.setForeground(Color.WHITE);
        CalculatingTf.setBackground(Color.BLACK);

        panel.add(CalculatingTf, BorderLayout.SOUTH);
        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(screen, BorderLayout.NORTH);

        add(panel);
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorM();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand().toString();

        switch (cmd){
            case ".":
                if ( !screen.getText().contains(".")){
                    screen.setText(screen.getText()+".");
                }
                break;

            case "0":
                screen.setText(screen.getText()+ "0");
                break;
            case "1":
                screen.setText(screen.getText()+ "1");
                break;
            case "2":
                screen.setText(screen.getText()+ "2");
                break;
            case "3":
                screen.setText(screen.getText()+ "3");
                break;
            case "4":
                screen.setText(screen.getText()+ "4");
                break;
            case "5":
                screen.setText(screen.getText()+ "5");
                break;
            case "6":
                screen.setText(screen.getText()+ "6");
                break;
            case "7":
                screen.setText(screen.getText()+ "7");
                break;
            case "8":
                screen.setText(screen.getText()+ "8");
                break;
            case "9":
                screen.setText(screen.getText()+ "9");
                break;

            case "+":
                if (!screen.getText().isEmpty()){

                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 1;
                    screen.setText("");
                }

                break;

            case "-":
                if (!screen.getText().isEmpty()){

                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 2;
                    screen.setText("");
                }

                break;

            case "x":
                if (!screen.getText().isEmpty()){

                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 3;
                    screen.setText("");
                }

                break;

            case "/":
                if (!screen.getText().isEmpty()){

                    firstNum = Double.parseDouble(screen.getText().toString());
                    operator = 4;
                    screen.setText("");
                }

                break;

            case "%":
                double num = Double.parseDouble(screen.getText().toString());
                screen.setText(String.valueOf(num / 100.0));
                break;

            case "+/-":
                double neg = Double.parseDouble(screen.getText().toString());
                neg *= -1;
                screen.setText(String.valueOf(neg));
                break;

            case "AC":
                screen.setText("");
                break;

            default:
        }

       if(cmd.equalsIgnoreCase("=")) {

           if (!screen.getText().isEmpty()){

               secondNum = Double.parseDouble(screen.getText().toString());

               switch (operator){
                   case 1: // soma
                       screen.setText(String.valueOf(firstNum +secondNum));
                       CalculatingTf.setText(String.valueOf(firstNum + " + " + secondNum + " = " + (df.format(firstNum + secondNum))));
                       break;

                   case 2: // subtração
                       screen.setText(String.valueOf(firstNum - secondNum));
                       CalculatingTf.setText(String.valueOf(firstNum + " - " + secondNum + " = " + (df.format(firstNum - secondNum))));
                       break;

                   case 3: // multiplicação
                       screen.setText(String.valueOf(firstNum * secondNum));
                       CalculatingTf.setText(String.valueOf(firstNum + " x " + secondNum + " = " + (df.format(firstNum * secondNum))));
                       break;

                   case 4: // divisão
                       screen.setText(String.valueOf(firstNum / secondNum));
                       CalculatingTf.setText(String.valueOf(firstNum + " / " + secondNum + " = " + (df.format(firstNum / secondNum))));
                       break;

                   default:
               }

           }

            }
       }
}