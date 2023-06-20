package gui.forms;

import javax.swing.*;

import gui.components.RegisterMenu;
import modules.cargoType.CargoTypeController;
import shared.errors.BaseRunTimeException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CargoTypeRegisterForm extends JFrame {

    // Componentes
    private JTextField campoNumero;
    private JTextField campoDescricao;
    private JTextField campoOrigem;
    private JTextField campoValidadeMaxima;
    private JButton botaoCadastrar;
    private JButton botaoLimpar;
    private JButton botaoVoltar;
    private JLabel mensagem;

    private int numero;
    private String descricao;
    private String origem;
    private int validadeMaxima;
    private CargoTypeController cargoTypeController;

    public CargoTypeRegisterForm() {
        super();
        cargoTypeController = new CargoTypeController();

        // Título do formulário
        JLabel tituloFormulario = new JLabel("Cadastro de Tipo de Carga");
        tituloFormulario.setFont(new Font("Tahoma", Font.PLAIN, 20));

        // Painel de campos de entrada
        GridLayout gridCampos = new GridLayout(4, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel rotuloNumero = new JLabel("Número:");
        campoNumero = new JTextField();
        JLabel rotuloDescricao = new JLabel("Descrição:");
        campoDescricao = new JTextField();
        JLabel rotuloOrigem = new JLabel("Origem:");
        campoOrigem = new JTextField();
        JLabel rotuloValidadeMaxima = new JLabel("Validade Máxima:");
        campoValidadeMaxima = new JTextField();

        painelCampos.add(rotuloNumero);
        painelCampos.add(campoNumero);
        painelCampos.add(rotuloDescricao);
        painelCampos.add(campoDescricao);
        painelCampos.add(rotuloOrigem);
        painelCampos.add(campoOrigem);
        painelCampos.add(rotuloValidadeMaxima);
        painelCampos.add(campoValidadeMaxima);

        // Botões
        botaoCadastrar = new JButton("Cadastrar");
        botaoLimpar = new JButton("Limpar Campos");
        botaoVoltar = new JButton("Voltar");
        mensagem = new JLabel();

        // Tratamento de evento do botão cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numero = Integer.parseInt(campoNumero.getText());
                descricao = campoDescricao.getText();
                origem = campoOrigem.getText();
                validadeMaxima = Integer.parseInt(campoValidadeMaxima.getText());

            }
        });

        // Tratamento de evento do botão limpar
        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNumero.setText("");
                campoDescricao.setText("");
                campoOrigem.setText("");
                campoValidadeMaxima.setText("");
            }
        });

        // Tratamento de evento do botão voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterMenu();
                setVisible(false);
            }
        });

        // Painel principal
        JPanel painelPrincipal = new JPanel(new GridLayout(4, 1));
        painelPrincipal.add(tituloFormulario);
        painelPrincipal.add(painelCampos);
        painelPrincipal.add(mensagem);

        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoVoltar);

        // Adicionar os painéis ao JFrame
        this.setTitle("Cadastro de Tipo de Carga");
        this.setLayout(new BorderLayout());
        this.add(painelPrincipal, BorderLayout.NORTH);
        this.add(painelBotoes, BorderLayout.SOUTH);
        this.setSize(800, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        CargoTypeRegisterForm janela = new CargoTypeRegisterForm();
    }

    // private void cargoTypeRegister() {
    // try {
    // cargoTypeController.onCreateCargoType(numero, descricao, origem,
    // validadeMaxima);
    // } catch (BaseRunTimeException e) {
    // message.setText(e.getTranslation());
    // return;
    // } catch (Exception e) {
    // message.setText("Erro ao ler o arquivo.");
    // return;
    // }
    // new RegisterMenu();
    // setVisible(false);
    // }
}