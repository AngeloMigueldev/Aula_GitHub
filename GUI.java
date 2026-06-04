import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CadastroAlunosGUI extends JFrame {

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtCurso;
    private JComboBox<String> comboTurno;
    private JTextArea areaResumo;
    private DefaultTableModel tabelaModelo;

    public CadastroAlunosGUI() {
        setTitle("Sistema de Cadastro de Alunos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelFormulario.add(txtIdade);

        painelFormulario.add(new JLabel("Curso:"));
        txtCurso = new JTextField();
        painelFormulario.add(txtCurso);

        painelFormulario.add(new JLabel("Turno:"));
        comboTurno = new JComboBox<>(new String[]{"Manhã", "Tarde", "Noite"});
        painelFormulario.add(comboTurno);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLimpar = new JButton("Limpar");

        painelFormulario.add(btnCadastrar);
        painelFormulario.add(btnLimpar);

        // Área de resumo
        areaResumo = new JTextArea(5, 20);
        areaResumo.setEditable(false);

        // Tabela
        tabelaModelo = new DefaultTableModel();
        tabelaModelo.addColumn("Nome");
        tabelaModelo.addColumn("Idade");
        tabelaModelo.addColumn("Curso");
        tabelaModelo.addColumn("Turno");

        JTable tabela = new JTable(tabelaModelo);
        JScrollPane scrollTabela = new JScrollPane(tabela);

        // Eventos
        btnCadastrar.addActionListener(e -> cadastrarAluno());

        btnLimpar.addActionListener(e -> limparCampos());

        painelPrincipal.add(painelFormulario, BorderLayout.NORTH);
        painelPrincipal.add(scrollTabela, BorderLayout.CENTER);
        painelPrincipal.add(new JScrollPane(areaResumo), BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void cadastrarAluno() {
        String nome = txtNome.getText();
        String idade = txtIdade.getText();
        String curso = txtCurso.getText();
        String turno = comboTurno.getSelectedItem().toString();

        if (nome.isEmpty() || idade.isEmpty() || curso.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        tabelaModelo.addRow(new Object[]{nome, idade, curso, turno});

        areaResumo.append("Aluno cadastrado: " + nome +
                " | Curso: " + curso +
                " | Turno: " + turno + "\n");

        JOptionPane.showMessageDialog(this,
                "Cadastro realizado com sucesso!");

        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtCurso.setText("");
        comboTurno.setSelectedIndex(0);
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroAlunosGUI tela = new CadastroAlunosGUI();
            tela.setVisible(true);
        });
    }
}
