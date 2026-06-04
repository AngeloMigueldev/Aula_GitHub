import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {

    private JTextField txtNome;
    private JTextField txtMat;
    private JTextField txtIdade;
    private JTextField txtCurso;
    private JComboBox<String> comboTurno;
    private JTextArea areaResumo;
    private DefaultTableModel tabelaModelo;
    private JTextField txtAluno;
    private JTextField txtN1;
    private JTextField txtN2;
    private JTextField txtN3;

    private JTable tabela; 

    public GUI() {
        setTitle("Sistema de Cadastro de Alunos");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(10, 2, 10, 10));

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Nome:</span> </html>"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Matrícula:</span> </html>"));
        txtMat = new JTextField();
        painelFormulario.add(txtMat);

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Idade:</span> </html>"));
        txtIdade = new JTextField();
        painelFormulario.add(txtIdade);

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Curso:</span> </html>"));
        txtCurso = new JTextField();
        painelFormulario.add(txtCurso);

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Turno:</span> </html>"));
        comboTurno = new JComboBox<>(new String[]{"<html> <span style='color: #f4bb34;'>Manhã:</span> </html>", "<html> <span style='color: #f27f04;'>Tarde:</span> </html>", "<html> <span style='color: #012e71;'>Noite:</span> </html>"});
        painelFormulario.add(comboTurno);

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Nota 1:</span> </html>"));
        txtN1 = new JTextField();
        painelFormulario.add(txtN1);

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Nota 2:</span> </html>"));
        txtN2 = new JTextField();
        painelFormulario.add(txtN2);

        painelFormulario.add(new JLabel("<html> <span style='color: #044798;'>Nota 3:</span> </html>"));
        txtN3 = new JTextField();
        painelFormulario.add(txtN3);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnRemover = new JButton("Remover Aluno");
        
        painelFormulario.add(btnCadastrar);
        painelFormulario.add(btnLimpar);
        painelFormulario.add(btnRemover);

        // Área de resumo
        areaResumo = new JTextArea(5, 20);
        areaResumo.setEditable(false);

        // Tabela
        tabelaModelo = new DefaultTableModel();
        tabelaModelo.addColumn("Nome");
        tabelaModelo.addColumn("Matrícula");
        tabelaModelo.addColumn("Idade");
        tabelaModelo.addColumn("Curso");
        tabelaModelo.addColumn("Turno");
        tabelaModelo.addColumn("Media");

        tabela = new JTable(tabelaModelo);
        JScrollPane scrollTabela = new JScrollPane(tabela);

        // Eventos
        btnCadastrar.addActionListener(e -> cadastrarAluno());

        btnLimpar.addActionListener(e -> limparCampos());

        btnRemover.addActionListener(e -> removerAluno());

        painelPrincipal.add(painelFormulario, BorderLayout.NORTH);
        painelPrincipal.add(scrollTabela, BorderLayout.CENTER);
        painelPrincipal.add(new JScrollPane(areaResumo), BorderLayout.SOUTH);
         
        add(painelPrincipal);
    }

    private void cadastrarAluno() {
        String nome = txtNome.getText();
        String mat = txtMat.getText();
        String idade = txtIdade.getText();
        String curso = txtCurso.getText();
        String turno = comboTurno.getSelectedItem().toString();
        String Nota1 = txtN1.getText();
        String Nota2 = txtN2.getText();
        String Nota3 = txtN3.getText();

        String regraNum = "^[0-9]+(\\.[0-9]+)?$";

        if (Nota1.matches(regraNum) && Nota2.matches(regraNum) && Nota3.matches(regraNum)) {

        double v1 = Double.parseDouble(Nota1);
        double v2 = Double.parseDouble(Nota2);
        double v3 = Double.parseDouble(Nota3);

        double media = (v1 + v2 + v3)/3;
        String mediaFormatada = String.format("%.2f", media);
        
        tabelaModelo.addRow(new Object[]{nome, mat, idade, curso, turno, mediaFormatada});

        areaResumo.append("Aluno cadastrado: " + nome + " | Matrícula: " + mat +
                " | Curso: " + curso +
                " | Turno: " + turno + "\n");

        JOptionPane.showMessageDialog(this,
                "Cadastro realizado com sucesso!");

        limparCampos();
        } else {
            JOptionPane.showMessageDialog(this,
                    "As notas devem conter apenas números (use ponto para decimais, ex: 7.5)!",
                    "Erro de Digitação",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (nome.isEmpty() || mat.isEmpty() || idade.isEmpty() || curso.isEmpty() || Nota1.isEmpty() || Nota2.isEmpty() || Nota3.isEmpty() ) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
    }

    private void limparCampos() {
        txtNome.setText("");
        txtMat.setText("");
        txtIdade.setText("");
        txtCurso.setText("");
        txtN1.setText("");
        txtN2.setText("");
        txtN3.setText("");
        comboTurno.setSelectedIndex(0);
        txtNome.requestFocus();
    }

    private void removerAluno(){
        
        JFrame frame = new JFrame("Qual aluno? (Digite a Matrícula)");
        frame.setSize(450, 100);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        txtAluno = new JTextField();
        frame.add(txtAluno, BorderLayout.CENTER);
        
        JButton btnConfirmar = new JButton("Remover Aluno");
        frame.add(btnConfirmar, BorderLayout.SOUTH);
        
        btnConfirmar.addActionListener(e -> {
            String matriculaAlvo = txtAluno.getText().trim();
            boolean encontrado = false;
            
            if (matriculaAlvo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Digite uma matrícula para buscar!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            for (int i = 0; i < tabelaModelo.getRowCount(); i++) {
                String matriculaTabela = tabelaModelo.getValueAt(i, 1).toString();
                
                if (matriculaTabela.equals(matriculaAlvo)) {
                    String nomeAluno = tabelaModelo.getValueAt(i, 0).toString();
                    
                    
                    tabelaModelo.removeRow(i);
                    
                    
                    areaResumo.append("O Aluno " + nomeAluno + " (Matrícula: " + matriculaAlvo + ") foi removido da tabela.\n");
                    JOptionPane.showMessageDialog(frame, "Aluno removido com sucesso!");
                    
                    encontrado = true;
                    frame.dispose(); 
                    break; 
                }
            }
            
            if (!encontrado) {
                JOptionPane.showMessageDialog(frame, "Nenhum aluno encontrado com a matrícula informada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI tela = new GUI();
            tela.setVisible(true);
        });
    }
}